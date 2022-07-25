package com.neathorium.thorium.framework.selenium.abstracts.regular;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractElementValueParameters<ParameterType, ReturnType> extends AbstractElementFunctionParameters<ParameterType, ReturnType> {
    public final Function<LazyElement, DriverFunction<ReturnType>> function;

    public AbstractElementValueParameters(
        TriFunction<DriverFunction<ParameterType>, Function<Data<ParameterType>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData);
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (
            NullablePredicates.isNull(o) ||
            EqualsPredicates.isNotEqual(getClass(), o.getClass()) ||
            BooleanUtilities.isFalse(super.equals(o))
        ) {
            return false;
        }

        final var that = (AbstractElementValueParameters<?, ?>) o;
        return EqualsPredicates.isEqual(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function);
    }

    @Override
    public String toString() {
        return (
            "AbstractElementValueParameters{" +
            "handler=" + handler +
            ", formatData=" + formatData +
            ", function=" + function +
            '}'
        );
    }
}
