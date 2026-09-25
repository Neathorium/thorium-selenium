package com.neathorium.thorium.framework.selenium.records.reflection;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.CastData;
import com.neathorium.thorium.framework.selenium.interfaces.IBaseInvokerDefaults;
import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;

import java.lang.reflect.Method;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public record RegularInvokerDefaultsData<ParameterType, ReturnType> (
    Function<BiFunction<Method, ParameterType, Object>, MethodFunction<Function<ParameterType, Object>>> CONSTRUCTOR,
    Predicate<BiFunction<Method, ParameterType, Object>> GUARD,
    CastData<ReturnType, ReturnType> CAST_DATA,
    Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> CAST_HANDLER
) implements IBaseInvokerDefaults<ParameterType, BiFunction<Method, ParameterType, Object>, ReturnType> {
}
