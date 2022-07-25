package com.neathorium.thorium.framework.selenium.implementations.reflection;

import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class InvokerRegularData<ParameterType> implements MethodFunction<Function<ParameterType, Object>> {
    public final BiFunction<Method, ParameterType, Object> handler;

    public InvokerRegularData(BiFunction<Method, ParameterType, Object> handler) {
        this.handler = handler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (InvokerRegularData<?>) o;
        return EqualsPredicates.isEqual(handler, that.handler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handler);
    }

    @Override
    public Function<ParameterType, Object> apply(Method method) {
        return NullablePredicates.isNotNull(method) ? base -> handler.apply(method, base) : InvokerFunctions.regularDefault();
    }
}