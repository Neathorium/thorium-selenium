package com.neathorium.thorium.framework.selenium.records.element.is.regular;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.Data;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ElementConditionParameters<ReturnType> extends ElementBooleanValueParameters<ReturnType> {
    public final UnaryOperator<Boolean> inverter;

    public ElementConditionParameters(
        TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function,
        UnaryOperator<Boolean> inverter
    ) {
        super(handler, formatData, function);
        this.inverter = inverter;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass()) || CoreUtilities.isFalse(super.equals(o))) {
            return false;
        }

        final var that = (ElementConditionParameters<?>) o;
        return CoreUtilities.isEqual(inverter, that.inverter);
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
