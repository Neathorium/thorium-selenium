package com.neathorium.thorium.framework.selenium.namespaces.driver.execute;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.interfaces.BaseFunctionalData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.FunctionRepository;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.thorium.framework.selenium.records.scripter.*;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public interface DriverExecuteFunctions {
    static Object applyParameterized(JavascriptExecutor executor, ExecuteParameterizedData handlerData, String script) {
        final var handler = handlerData.HANDLER();
        final var parameters = handlerData.PARAMETERS();
        final var parameterArray = parameters.toArray(new Object[0]);
        return handler.apply(executor).apply(script, parameterArray);
    }

    static Object applyRegular(JavascriptExecutor executor, ScriptFunction<Function<String, Object>> handler, String script) {
        return handler.apply(executor).apply(script);
    }

    private static <HandlerType> Object handleData(
        JavascriptExecutor executor,
        ScriptApplierData<HandlerType> applierData,
        String script
    ) {
        if (NullablePredicates.isNull(executor)) {
            throw new IllegalArgumentException("Executor parameter" + CoreFormatterConstants.WAS_NULL);
        }

        final var data = applierData.DATA();
        final var applier = applierData.APPLIER();
        return applier.apply(executor, data, script);
    }

    static <HandlerType> Function<String, Object> handleData(
        JavascriptExecutor executor,
        ScriptApplierData<HandlerType> applierData
    ) {
        return script -> DriverExecuteFunctions.handleData(executor, applierData, script);
    }

    private static <HandlerType, ReturnType> Data<ReturnType> executeCore(
        WebDriver driver,
        BaseFunctionalData<JavascriptExecutor, HandlerType, String, Boolean, ReturnType> data,
        ScriptApplierData<HandlerType> handler,
        String script
    ) {
        final var nameof = "executeCore";
        final var castData = data.CAST_DATA();
        final var executor = data.GETTER().apply(driver);
        final var defaultValue = castData.DEFAULT_VALUE().OBJECT();
        if (DataPredicates.isInvalidOrFalse(executor)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, "Executor" + CoreFormatterConstants.WAS_NULL);
        }


        final var function = castData.CASTER().compose(DriverExecuteFunctions.handleData(executor.OBJECT(), handler));
        final var resultFunctions = data.RESULT_HANDLER();
        final var result = resultFunctions.CAST_HANDLER().apply(new HandleResultData<>(function, script, defaultValue));
        final var status = result.STATUS();
        var message = result.MESSAGE().MESSAGE();
        if (status) {
            message = resultFunctions.MESSAGE_HANDLER().apply(status);
        }
        return DataFactoryFunctions.getWith(result.OBJECT(), status, nameof, message, result.EXCEPTION());
    }


    static <HandlerType, ReturnType> DriverFunction<ReturnType> execute(
        String nameof,
        ScriptApplierData<HandlerType> handler,
        ExecuteCoreData<HandlerType, ReturnType> data,
        String script
    ) {
        final var localNameof = StringUtils.isNotBlank(nameof) ? nameof : "DriverExecuteFunctions.executeCore";
        final var negative = FunctionRepository.get(data.FUNCTION_MAP(), data.NEGATIVE_KEY_DATA());
        final var errorMessage = "";
        if (StringUtils.isNotBlank(errorMessage)) {
            return DriverFunctionFactory.replaceMessage(negative, errorMessage);
        }
        return ExecutionCore.ifDriver(
            localNameof,
            StringUtils.isNotBlank(script) && ScriptExecutions.isValidConstructorData(data.DATA()),
            driver -> executeCore(driver, data.DATA(), handler, script),
            negative
        );
    }
}
