package com.neathorium.thorium.framework.selenium.implementations.reflection;

import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.lang.reflect.Method;
import java.util.function.BiFunction;
import java.util.function.Function;

public record InvokerRegularData<ParameterType>(
    BiFunction<Method, ParameterType, Object> HANDLER
) implements MethodFunction<Function<ParameterType, Object>> {
    @Override
    public Function<ParameterType, Object> apply(Method method) {
        return NullablePredicates.isNotNull(method) ? base -> HANDLER.apply(method, base) : InvokerFunctions.regularDefault();
    }
}