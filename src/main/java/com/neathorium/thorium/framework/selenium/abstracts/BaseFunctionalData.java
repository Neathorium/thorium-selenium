package com.neathorium.thorium.framework.selenium.abstracts;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorWrappedResultFunctionsData;

import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public abstract class BaseFunctionalData<GetterType, HandlerType, ParameterType, MessageParameterType, ConstructorType, ConstructorResultType, ReturnType> {
    public final DriverFunction<GetterType> GETTER;
    public final BiFunction<ConstructorType, HandlerType, ConstructorResultType> CONSTRUCTOR;
    public final Predicate<HandlerType> GUARD;
    public final WrappedCastData<ReturnType> CAST_DATA;
    public final ExecutorWrappedResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> RESULT_HANDLERS;

    public BaseFunctionalData(
        DriverFunction<GetterType> getter,
        BiFunction<ConstructorType, HandlerType, ConstructorResultType> constructor,
        Predicate<HandlerType> guard,
        WrappedCastData<ReturnType> castData,
        ExecutorWrappedResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> resultHandlers
    ) {
        this.GETTER = getter;
        this.CONSTRUCTOR = constructor;
        this.GUARD = guard;
        this.CAST_DATA = castData;
        this.RESULT_HANDLERS = resultHandlers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (BaseFunctionalData<?, ?, ?, ?, ?, ?, ?>) o;
        return (
            EqualsPredicates.isEqual(GETTER, that.GETTER) &&
            EqualsPredicates.isEqual(CONSTRUCTOR, that.CONSTRUCTOR) &&
            EqualsPredicates.isEqual(GUARD, that.GUARD) &&
            EqualsPredicates.isEqual(CAST_DATA, that.CAST_DATA) &&
            EqualsPredicates.isEqual(RESULT_HANDLERS, that.RESULT_HANDLERS)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(GETTER, CONSTRUCTOR, GUARD, CAST_DATA, RESULT_HANDLERS);
    }

    @Override
    public String toString() {
        return (
            "BaseFunctionalData{" +
            "GETTER=" + GETTER +
            ", CONSTRUCTOR=" + CONSTRUCTOR +
            ", GUARD=" + GUARD +
            ", CAST_DATA=" + CAST_DATA +
            ", RESULT_HANDLERS=" + RESULT_HANDLERS +
            '}'
        );
    }
}
