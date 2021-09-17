package com.neathorium.thorium.framework.selenium.records.element.is;

import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;

import java.util.Objects;

public class ElementFormatData<T> {
    public final TriFunction<String, String, T, String> formatter;
    public final String conditionName;
    public final String descriptor;

    public ElementFormatData(TriFunction<String, String, T, String> formatter, String conditionName, String descriptor) {
        this.formatter = formatter;
        this.conditionName = conditionName;
        this.descriptor = descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (ElementFormatData<?>) o;
        return (
            CoreUtilities.isEqual(formatter, that.formatter) &&
            CoreUtilities.isEqual(conditionName, that.conditionName) &&
            CoreUtilities.isEqual(descriptor, that.descriptor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatter, conditionName, descriptor);
    }
}
