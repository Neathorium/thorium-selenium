package com.neathorium.thorium.framework.selenium.records.element.is.regular;

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
import java.util.function.Predicate;

public class ElementConditionParameters<ReturnType, PredicateType> extends ElementBooleanValueParameters<ReturnType> {
    public final Function<Predicate<PredicateType>, Predicate<PredicateType>> inverter;

    public ElementConditionParameters(
        TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function,
        Function<Predicate<PredicateType>, Predicate<PredicateType>> inverter
    ) {
        super(handler, formatData, function);
        this.inverter = inverter;
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

        final var that = (ElementConditionParameters<?, ?>) o;
        return EqualsPredicates.isEqual(inverter, that.inverter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inverter);
    }

    @Override
    public String toString() {
        return (
            "ElementConditionParameters{" +
            "handler=" + handler +
            ", formatData=" + formatData +
            ", function=" + function +
            ", inverter=" + inverter +
            '}'
        );
    }
}
