package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.abstracts.ExecuteBaseData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptHandlerFunction;
import com.neathorium.thorium.core.records.ExecuteCommonData;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class RegularExecutorData<ReturnType> extends ExecutorData<ScriptHandlerFunction, String, Boolean, ReturnType> {
    public RegularExecutorData(
        DriverFunction<JavascriptExecutor> getter,
        BiFunction<ExecuteCommonData<String>, ScriptHandlerFunction, ExecuteBaseData<String, Function<String, Object>>> constructor,
        Predicate<ScriptHandlerFunction> guard,
        WrappedCastData<ReturnType> castData,
        ExecutorWrappedResultFunctionsData<HandleResultData<String, ReturnType>, Boolean, ReturnType> resultHandlers
    ) {
        super(getter, constructor, guard, castData, resultHandlers);
    }
}
