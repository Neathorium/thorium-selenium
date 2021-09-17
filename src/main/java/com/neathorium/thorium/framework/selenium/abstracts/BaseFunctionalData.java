package com.neathorium.thorium.framework.selenium.abstracts;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorWrappedResultFunctionsData;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public abstract class BaseFunctionalData<GetterType, HandlerType, ParameterType, MessageParameterType, ConstructorType, ConstructorResultType, ReturnType> {
    public final DriverFunction<GetterType> getter;
    public final BiFunction<ConstructorType, HandlerType, ConstructorResultType> constructor;
    public final Predicate<HandlerType> guard;
    public final WrappedCastData<ReturnType> castData;
    public final ExecutorWrappedResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> resultHandlers;

    public BaseFunctionalData(
        DriverFunction<GetterType> getter,
        BiFunction<ConstructorType, HandlerType, ConstructorResultType> constructor,
        Predicate<HandlerType> guard,
        WrappedCastData<ReturnType> castData,
        ExecutorWrappedResultFunctionsData<HandleResultData<ParameterType, ReturnType>, MessageParameterType, ReturnType> resultHandlers
    ) {
        this.getter = getter;
        this.constructor = constructor;
        this.guard = guard;
        this.castData = castData;
        this.resultHandlers = resultHandlers;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (BaseFunctionalData<?, ?, ?, ?, ?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(getter, that.getter) &&
            CoreUtilities.isEqual(constructor, that.constructor) &&
            CoreUtilities.isEqual(guard, that.guard) &&
            CoreUtilities.isEqual(castData, that.castData) &&
            CoreUtilities.isEqual(resultHandlers, that.resultHandlers)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getter, constructor, guard, castData, resultHandlers);
    }

    @Override
    public String toString() {
        return (
            "BaseFunctionalData{" +
            "getter=" + getter +
            ", constructor=" + constructor +
            ", guard=" + guard +
            ", castData=" + castData +
            ", resultHandlers=" + resultHandlers +
            '}'
        );
    }
}
