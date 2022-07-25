package com.neathorium.thorium.framework.selenium.namespaces.driver.execute;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.FunctionRepository;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ExecuteCoreValidators;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteCoreData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteCoreFunctionData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorParametersFieldData;
import com.neathorium.thorium.framework.selenium.records.scripter.ParametersFieldDefaultsData;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.ExecuteCommonData;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverExecuteFunctions {
    private static <HandlerType, ReturnType> Data<ReturnType> executeCore(WebDriver driver, ExecutorData<HandlerType, String, Boolean, ReturnType> data, HandlerType handler, String script) {
        final var nameof = "executeCore";
        final var castData = data.CAST_DATA;
        final var executor = data.GETTER.apply(driver);
        final var defaultValue = castData.DEFAULT_VALUE.OBJECT();
        if (DataPredicates.isInvalidOrFalse(executor)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, "Executor" + CoreFormatterConstants.WAS_NULL);
        }

        final var parameters = new ExecuteCommonData<>(script, StringUtils::isNotBlank);
        final var exData = data.CONSTRUCTOR.apply(parameters, handler);
        final var function = castData.CASTER.compose(exData.apply(executor.OBJECT()));
        final var resultFunctions = data.RESULT_HANDLERS;
        final var result = resultFunctions.castHandler.apply(new HandleResultData<>(function, script, defaultValue));
        final var status = result.STATUS();
        var message = result.MESSAGE().MESSAGE();
        if (status) {
            message = resultFunctions.messageHandler.apply(status);
        }
        return DataFactoryFunctions.getWith(result.OBJECT(), status, nameof, message, result.EXCEPTION());
    }

    private static <HandlerType, ReturnType> DriverFunction<ReturnType> executeCore(
        ExecuteCoreFunctionData<HandlerType> functionData,
        DriverFunction<ReturnType> negative,
        ExecutorData<HandlerType, String, Boolean, ReturnType> data,
        String script
    ) {
        final var isFunctionDataNotNull = NullablePredicates.isNotNull(functionData);
        final var name = isFunctionDataNotNull ? functionData.nameof : CoreFormatterConstants.EMPTY;
        final var nameof = isNotBlank(name) ? name : "executeCore";
        return ExecutionCore.ifDriver(
            nameof,
            isNotBlank(script) && isFunctionDataNotNull && ScriptExecutions.isValidConstructorData(data),
            driver -> executeCore(driver, data, functionData.handler, script),
            negative
        );
    }

    static <HandlerType, ReturnType> DriverFunction<ReturnType> execute(ExecuteCoreFunctionData<HandlerType> functionData, ExecuteCoreData<HandlerType, ReturnType> data, String script) {
        return executeCore(functionData, FunctionRepository.get(data.FUNCTION_MAP(), data.NEGATIVE_KEY_DATA()), data.DATA(), script);
    }

    static <ReturnType> DriverFunction<ReturnType> executeParameters(
        ExecuteCoreFunctionData<ParametersFieldDefaultsData> functionData,
        ExecuteCoreData<ExecutorParametersFieldData, ReturnType> data,
        String script,
        Object[] parameters
    ) {
        final var negative = FunctionRepository.get(data.FUNCTION_MAP(), data.NEGATIVE_KEY_DATA());
        final var handlerData = functionData.handler;
        final var errorMessage = (
            ExecuteCoreValidators.isInvalidExecuteCoreFunctionData(functionData) +
            CoreFormatter.isNullMessageWithName(parameters, "Parameters") +
            CoreFormatter.isFalseMessageWithName(handlerData.validator.test(parameters), "Parameter validation")
        );
        if (isNotBlank(errorMessage)) {
            return DriverFunctionFactory.replaceMessage(negative, errorMessage);
        }

        final var fnData = new ExecuteCoreFunctionData<>(functionData.nameof, new ExecutorParametersFieldData(parameters, handlerData));
        return executeCore(fnData, negative, data.DATA(), script);
    }
}
