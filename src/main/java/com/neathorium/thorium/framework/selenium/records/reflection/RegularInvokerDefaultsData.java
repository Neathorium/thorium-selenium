package com.neathorium.thorium.framework.selenium.records.reflection;

import com.neathorium.thorium.core.extensions.interfaces.functional.boilers.MethodFunction;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.framework.selenium.abstracts.reflection.BaseInvokerDefaultsData;

import java.lang.reflect.Method;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class RegularInvokerDefaultsData<ParameterType, ReturnType> extends BaseInvokerDefaultsData<ParameterType, BiFunction<Method, ParameterType, Object>, ReturnType> {
    public RegularInvokerDefaultsData(
        Function<BiFunction<Method, ParameterType, Object>, MethodFunction<Function<ParameterType, Object>>> constructor,
        Predicate<BiFunction<Method, ParameterType, Object>> guard,
        BasicCastData<ReturnType> castData,
        Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> castHandler) {
        super(constructor, guard, castData, castHandler);
    }
}
