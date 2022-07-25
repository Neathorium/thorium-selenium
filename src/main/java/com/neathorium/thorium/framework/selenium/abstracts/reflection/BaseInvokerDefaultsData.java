package com.neathorium.thorium.framework.selenium.abstracts.reflection;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> {
    public final Function<HandlerType, MethodFunction<Function<ParameterType, Object>>> CONSTRUCTOR;
    public final Predicate<HandlerType> GUARD;
    public final BasicCastData<ReturnType> CAST_DATA;
    public final Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> CAST_HANDLER;

    public BaseInvokerDefaultsData(
        Function<HandlerType, MethodFunction<Function<ParameterType, Object>>> constructor,
        Predicate<HandlerType> guard,
        BasicCastData<ReturnType> castData,
        Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> castHandler
    ) {
        this.CONSTRUCTOR = constructor;
        this.GUARD = guard;
        this.CAST_DATA = castData;
        this.CAST_HANDLER = castHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (BaseInvokerDefaultsData<?, ?, ?>) o;
        return (
            EqualsPredicates.isEqual(CONSTRUCTOR, that.CONSTRUCTOR) &&
            EqualsPredicates.isEqual(GUARD, that.GUARD) &&
            EqualsPredicates.isEqual(CAST_DATA, that.CAST_DATA) &&
            EqualsPredicates.isEqual(CAST_HANDLER, that.CAST_HANDLER)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(CONSTRUCTOR, GUARD, CAST_DATA, CAST_HANDLER);
    }
}