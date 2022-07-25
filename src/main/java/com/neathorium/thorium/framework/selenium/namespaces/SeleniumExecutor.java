package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.executor.SeleniumExecutorUtilities;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.core.constants.CoreConstants;
import com.neathorium.thorium.core.constants.ExecutorConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.executor.ExecutionParametersDataFactory;
import com.neathorium.thorium.core.namespaces.executor.ExecutionResultDataFactory;
import com.neathorium.thorium.core.namespaces.executor.ExecutionStateDataFactory;
import com.neathorium.thorium.core.namespaces.executor.ExecutionStepsDataFactory;
import com.neathorium.thorium.core.namespaces.executor.Executor;
import com.neathorium.thorium.core.namespaces.executor.ExecutorFunctionDataFactory;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.executor.ExecutionParametersData;
import com.neathorium.thorium.core.records.executor.ExecutionResultData;
import com.neathorium.thorium.core.records.executor.ExecutionStateData;
import com.neathorium.thorium.core.records.executor.ExecutionStepsData;
import com.neathorium.thorium.java.extensions.interfaces.functional.QuadFunction;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriPredicate;
import com.neathorium.thorium.java.extensions.interfaces.functional.boilers.IGetMessage;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface SeleniumExecutor {
    private static <ReturnType, ParameterReturnType> DriverFunction<ReturnType> executeGuardCore(
        ExecutionParametersData<Function<WebDriver, Data<?>>, DriverFunction<ParameterReturnType>> execution,
        DriverFunction<ReturnType> executionChain,
        Data<ReturnType> negative,
        int stepLength
    ) {
        return ifDriver("executeGuardCore", CoreFormatter.getCommandAmountRangeErrorMessage(stepLength, execution.range), executionChain, negative);
    }

    @SafeVarargs
    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> execute(
        ExecutionParametersData<Function<WebDriver, Data<?>>, DriverFunction<ExecutionResultData<ReturnType>>> execution,
        ExecutionStateData stateData,
        Function<WebDriver, Data<?>>... steps
    ) {
        final var negative = DataFactoryFunctions.getWith(ExecutionResultDataFactory.getWithDefaultState((ReturnType) CoreConstants.STOCK_OBJECT), false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, DriverFunctionFactory.getFunction(execution.executor.apply(execution.functionData, stateData, steps)), negative, steps.length);
    }

    private static <ReturnType> Data<ReturnType> executeData(
        ExecutionStepsData<WebDriver> stepsData,
        ExecutionParametersData<Function<WebDriver, Data<?>>, DriverFunction<ExecutionResultData<ReturnType>>> execution
    ) {
        final var result = execute(execution, ExecutionStateDataFactory.getWithDefaults(), stepsData.steps).apply(stepsData.dependency);
        return DataFactoryFunctions.replaceObject(result, result.OBJECT().result);
    }

    private static <ReturnType> DriverFunction<ReturnType> executeData(
        ExecutionParametersData<Function<WebDriver, Data<?>>, DriverFunction<ExecutionResultData<ReturnType>>> execution,
        DriverFunction<?>... steps
    ) {
        return DriverFunctionFactory.getFunction(driver -> executeData(ExecutionStepsDataFactory.getWithStepsAndDependency(steps, driver), execution));
    }

    static <ReturnType> DriverFunction<ReturnType> execute(ExecutionParametersData<Function<WebDriver, Data<?>>, DriverFunction<ExecutionResultData<ReturnType>>> execution, DriverFunction<?>... steps) {
        final var negative = DataFactoryFunctions.getWith((ReturnType) CoreConstants.STOCK_OBJECT, false, CoreFormatterConstants.EMPTY);
        return executeGuardCore(execution, executeData(execution, steps), negative, steps.length);
    }

    static <ReturnType> DriverFunction<ReturnType> execute(IGetMessage stepMessage, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                Executor::execute
            ),
            steps
        ));
    }

    static <Any> DriverFunction<Any> execute(String message, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                Executor::execute
            ),
            steps
        ));
    }

    static <Any> DriverFunction<Any> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                Executor::execute
            ),
            steps
        ));
    }

    static <Any> DriverFunction<Any> execute(DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), steps));
    }

    static <ReturnType> DriverFunction<ReturnType> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, DriverFunction<?> before, DriverFunction<?> after) {
        final var steps = SeleniumExecutorUtilities.getStepArray(before, after);
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithSpecificMessageAndBreakCondition(CoreFormatterConstants.EXECUTION_ENDED, guard),
                Executor::execute
            ),
            steps
        ));
    }

    static <T, U, Any> DriverFunction<Any> conditionalSequence(DriverFunction<T> before, DriverFunction<U> after, Class<Any> clazz) {
        final var steps = SeleniumExecutorUtilities.getStepArray(before, after);
        return DriverFunctionFactory.getFunction(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute), steps));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> execute(IGetMessage stepMessage, ExecutionStateData stateData, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithExecuteParametersDataAndDefaultExitCondition(stepMessage, ExecutorConstants.DEFAULT_EXECUTION_DATA),
                Executor::execute
            ),
            stateData,
            steps
        ));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> execute(String message, ExecutionStateData stateData, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithDefaultRange(
                ExecutorFunctionDataFactory.getWithSpecificMessage(message),
                Executor::execute
            ),
            stateData,
            steps
        ));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> execute(QuadFunction<ExecutionStateData, String, Integer, Integer, String> messageHandler, ExecutionStateData stateData, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithDefaultExitConditionAndMessageData(messageHandler),
                Executor::execute
            ),
            stateData,
            steps
        ));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> execute(ExecutionStateData stateData, DriverFunction<?>... steps) {
        return DriverFunctionFactory.getFunction(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndDefaultRange(Executor::execute), stateData, steps));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> conditionalSequence(TriPredicate<Data<?>, Integer, Integer> guard, ExecutionStateData stateData, DriverFunction<?> before, DriverFunction<?> after) {
        final var steps = SeleniumExecutorUtilities.getStepArray(before, after);
        return DriverFunctionFactory.getFunction(Executor.execute(
            ExecutionParametersDataFactory.getWithTwoCommandsRange(
                ExecutorFunctionDataFactory.getWithSpecificMessageAndBreakCondition(CoreFormatterConstants.EXECUTION_ENDED, guard),
                Executor::execute
            ),
            stateData,
            steps
        ));
    }

    static <T, U, ReturnType> DriverFunction<ExecutionResultData<ReturnType>> conditionalSequence(ExecutionStateData stateData, DriverFunction<T> before, DriverFunction<U> after, Class<ReturnType> clazz) {
        final var steps = SeleniumExecutorUtilities.getStepArray(before, after);
        return DriverFunctionFactory.getFunction(Executor.execute(ExecutionParametersDataFactory.getWithDefaultFunctionDataAndTwoCommandRange(Executor::execute), stateData, steps));
    }

    static <ReturnType> DriverFunction<ExecutionResultData<ReturnType>> repeat(String message, ExecutionStateData stateData, DriverFunction<?> step, int amount) {
        return execute(message, stateData, SeleniumExecutorUtilities.getStepArray(step, amount));
    }

    static <Any> DriverFunction<Any> repeat(String message, DriverFunction<?> step, int amount) {
        return execute(message, SeleniumExecutorUtilities.getStepArray(step, amount));
    }

    static <Any> DriverFunction<Any> repeat(DriverFunction<?> step, int amount) {
        return execute(SeleniumExecutorUtilities.getStepArray(step, amount));
    }
}
