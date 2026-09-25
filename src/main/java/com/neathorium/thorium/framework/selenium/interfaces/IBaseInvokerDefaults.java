package com.neathorium.thorium.framework.selenium.interfaces;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.BasicCastData;

import java.util.function.Function;
import java.util.function.Predicate;

public interface IBaseInvokerDefaults<ParameterType, HandlerType, ReturnType> {
    Function<HandlerType, MethodFunction<Function<ParameterType, Object>>> CONSTRUCTOR();
    Predicate<HandlerType> GUARD();
    BasicCastData<ReturnType> CAST_DATA();
    Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> CAST_HANDLER();
}
