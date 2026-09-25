package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.interfaces.scripter.IExecutorResultFunctionsData;
import com.neathorium.thorium.framework.selenium.namespaces.driver.executor.ExecutorFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteParameterizedData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorData;
import com.neathorium.thorium.core.constants.CastDataConstants;
import com.neathorium.thorium.core.namespaces.ExceptionHandlers;
import com.neathorium.thorium.core.records.HandleResultData;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.Function;

public abstract class ScriptExecutorDefaults {
    public static final IExecutorResultFunctionsData<HandleResultData<String, Object>, Boolean, Object> OBJECT_RESULT_HANDLER = new IExecutorResultFunctionsData<>(SeleniumFormatter::getScriptExecutionMessage, ExceptionHandlers::classCastHandler);
    public static final IExecutorResultFunctionsData<HandleResultData<String, String>, Boolean, String> STRING_RESULT_HANDLER = new IExecutorResultFunctionsData<>(SeleniumFormatter::getScriptExecutionMessage, ExceptionHandlers::classCastHandler);
    public static final DriverFunction<JavascriptExecutor> JAVASCRIPT_EXECUTOR_GETTER = ExecutorFunctions.getExecutor();
    public static final ExecutorData<Object, ScriptFunction<Function<String, Object>>> OBJECT_REGULAR_DEFAULTS = new ExecutorData<>(
        ScriptExecutorDefaults.JAVASCRIPT_EXECUTOR_GETTER,
        ScriptExecutions::isValidExecutorRegularData,
        CastDataConstants.WRAPPED_OBJECT,
        ScriptExecutorDefaults.OBJECT_RESULT_HANDLER
    );
    public static final ExecutorData<String, ScriptFunction<Function<String, Object>>> STRING_REGULAR_DEFAULTS = new ExecutorData<>(
        ScriptExecutorDefaults.JAVASCRIPT_EXECUTOR_GETTER,
        ScriptExecutions::isValidExecutorRegularData,
        CastDataConstants.WRAPPED_STRING,
        ScriptExecutorDefaults.STRING_RESULT_HANDLER
    );
    public static final ExecutorData<Object, ExecuteParameterizedData> OBJECT_PARAMETERS_DEFAULTS = new ExecutorData<>(
        ScriptExecutorDefaults.JAVASCRIPT_EXECUTOR_GETTER,
        ScriptExecutions::isValidExecutorParametersData,
        CastDataConstants.WRAPPED_OBJECT,
        ScriptExecutorDefaults.OBJECT_RESULT_HANDLER
    );
    public static final ExecutorData<String, ExecuteParameterizedData> STRING_PARAMETERS_DEFAULTS = new ExecutorData<>(
        ScriptExecutorDefaults.JAVASCRIPT_EXECUTOR_GETTER,
        ScriptExecutions::isValidExecutorParametersData,
        CastDataConstants.WRAPPED_STRING,
        ScriptExecutorDefaults.STRING_RESULT_HANDLER
    );
}
