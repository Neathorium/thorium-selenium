package com.neathorium.thorium.framework.selenium.interfaces;

import com.neathorium.thorium.framework.selenium.interfaces.scripter.IExecutorResultFunctionsData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;

import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;

import java.util.function.Predicate;

public interface BaseFunctionalData<GetterType, HandlerType, ParameterType, MessageParameterType, ReturnType> {
    DriverFunction<GetterType> GETTER();
    Predicate<HandlerType> GUARD();
    WrappedCastData<ReturnType> CAST_DATA();
    IExecutorResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> RESULT_HANDLER();
}
