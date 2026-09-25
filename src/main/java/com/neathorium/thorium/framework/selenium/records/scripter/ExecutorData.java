package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.interfaces.BaseFunctionalData;
import com.neathorium.thorium.framework.selenium.interfaces.scripter.IExecutorResultFunctionsData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.Predicate;

public record ExecutorData<ReturnType, HandlerType> (
    DriverFunction<JavascriptExecutor> GETTER,
    Predicate<HandlerType> GUARD,
    WrappedCastData<ReturnType> CAST_DATA,
    IExecutorResultFunctionsData<HandleResultData<String, ReturnType>, Boolean, ReturnType> RESULT_HANDLER
) implements BaseFunctionalData<JavascriptExecutor, HandlerType, String, Boolean, ReturnType> {}
