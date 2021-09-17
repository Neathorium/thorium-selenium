package com.neathorium.thorium.framework.selenium.namespaces.driver.execute;

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
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.core.records.ExecuteCommonData;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverExecuteFunctions {
    private static <HandlerType, ReturnType> Data<ReturnType> executeCore(WebDriver driver, ExecutorData<HandlerType, String, Boolean, ReturnType> data, HandlerType handler, String script) {
        final var nameof = "executeCore";
        final var castData = data.castData;
        final var executor = data.getter.apply(driver);
        final var defaultValue = castData.DEFAULT_VALUE.object;
        if (DataPredicates.isInvalidOrFalse(executor)) {
            return DataFactoryFunctions.getWith(defaultValue, false, nameof, "Executor" + CoreFormatterConstants.WAS_NULL);
        }

        final var parameters = new ExecuteCommonData<>(script, StringUtils::isNotBlank);
        final var exData = data.constructor.apply(parameters, handler);
        final var function = castData.CASTER.compose(exData.apply(executor.object));
        final var resultFunctions = data.resultHandlers;
        final var result = resultFunctions.castHandler.apply(new HandleResultData<>(function, script, defaultValue));
        final var status = result.status;
        var message = result.message.message;
        if (status) {
            message = resultFunctions.messageHandler.apply(status);
        }
        return DataFactoryFunctions.getWith(result.object, status, nameof, message, result.exception);
    }

    private static <HandlerType, ReturnType> DriverFunction<ReturnType> executeCore(
        ExecuteCoreFunctionData<HandlerType> functionData,
        DriverFunction<ReturnType> negative,
        ExecutorData<HandlerType, String, Boolean, ReturnType> data,
        String script
    ) {
        final var isFunctionDataNotNull = NullableFunctions.isNotNull(functionData);
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
        return executeCore(functionData, FunctionRepository.get(data.functionMap, data.negativeKeyData), data.data, script);
    }

    static <ReturnType> DriverFunction<ReturnType> executeParameters(
            ExecuteCoreFunctionData<ParametersFieldDefaultsData> functionData,
            ExecuteCoreData<ExecutorParametersFieldData, ReturnType> data,
            String script,
            Object[] parameters
    ) {
        final var negative = FunctionRepository.get(data.functionMap, data.negativeKeyData);
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
        return executeCore(fnData, negative, data.data, script);
    }
}
