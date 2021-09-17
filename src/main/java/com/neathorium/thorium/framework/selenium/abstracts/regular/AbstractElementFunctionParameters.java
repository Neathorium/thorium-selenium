package com.neathorium.thorium.framework.selenium.abstracts.regular;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.Data;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractElementFunctionParameters<ParameterType, ReturnType> {
    public final TriFunction<DriverFunction<ParameterType>, Function<Data<ParameterType>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler;
    public final ElementFormatData<ReturnType> formatData;

    public AbstractElementFunctionParameters(
        TriFunction<DriverFunction<ParameterType>, Function<Data<ParameterType>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData
    ) {
        this.handler = handler;
        this.formatData = formatData;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (AbstractElementFunctionParameters<?, ?>) o;
        return (
            CoreUtilities.isEqual(handler, that.handler) &&
            CoreUtilities.isEqual(formatData, that.formatData)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(handler, formatData);
    }

    @Override
    public String toString() {
        return (
            "AbstractElementFunctionParameters{" +
            "handler=" + handler +
            ", formatData=" + formatData +
            '}'
        );
    }
}
