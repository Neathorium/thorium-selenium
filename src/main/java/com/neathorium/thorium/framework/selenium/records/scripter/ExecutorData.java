package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.abstracts.BaseFunctionalData;
import com.neathorium.thorium.framework.selenium.abstracts.ExecuteBaseData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.records.ExecuteCommonData;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExecutorData<HandlerType, ParameterType, MessageParameterType, ReturnType> extends
        BaseFunctionalData<JavascriptExecutor, HandlerType, ParameterType, MessageParameterType, ExecuteCommonData<ParameterType>, ExecuteBaseData<ParameterType, Function<String, Object>>, ReturnType> {
    public ExecutorData(
            DriverFunction<JavascriptExecutor> getter,
            BiFunction<ExecuteCommonData<ParameterType>, HandlerType, ExecuteBaseData<ParameterType, Function<String, Object>>> constructor,
            Predicate<HandlerType> guard,
            WrappedCastData<ReturnType> castData,
            ExecutorWrappedResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> resultHandlers
    ) {
        super(getter, constructor, guard, castData, resultHandlers);
    }
}