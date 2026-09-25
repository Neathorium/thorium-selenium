package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteCoreData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteParameterizedData;

import java.util.function.Function;

public abstract class ExecuteCoreDataConstants {
    public static final ExecuteCoreData<ScriptFunction<Function<String, Object>>, Object> EXECUTE_RETURN_OBJECT = new ExecuteCoreData<>(ScriptExecutorDefaults.OBJECT_REGULAR_DEFAULTS, DriverFunctionConstants.FUNCTION_MAP, DriverFunctionConstants.OBJECT_FUNCTION_KEY);
    public static final ExecuteCoreData<ScriptFunction<Function<String, Object>>, String> EXECUTE_RETURN_STRING = new ExecuteCoreData<>(ScriptExecutorDefaults.STRING_REGULAR_DEFAULTS, DriverFunctionConstants.FUNCTION_MAP, DriverFunctionConstants.STRING_FUNCTION_KEY);
    public static final ExecuteCoreData<ExecuteParameterizedData, Object> EXECUTE_PARAMETERS_RETURN_OBJECT = new ExecuteCoreData<>(ScriptExecutorDefaults.OBJECT_PARAMETERS_DEFAULTS, DriverFunctionConstants.FUNCTION_MAP, DriverFunctionConstants.OBJECT_FUNCTION_KEY);
    public static final ExecuteCoreData<ExecuteParameterizedData, String> EXECUTE_PARAMETERS_RETURN_STRING = new ExecuteCoreData<>(ScriptExecutorDefaults.STRING_PARAMETERS_DEFAULTS, DriverFunctionConstants.FUNCTION_MAP, DriverFunctionConstants.STRING_FUNCTION_KEY);
}
