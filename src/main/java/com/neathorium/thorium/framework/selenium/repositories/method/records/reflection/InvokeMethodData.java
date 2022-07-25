package com.neathorium.thorium.framework.selenium.repositories.method.records.reflection;

import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;

public class InvokeMethodData {
    public final MethodParametersData parametersData;
    public final String nameof;

    public InvokeMethodData(MethodParametersData parametersData, String nameof) {
        this.parametersData = parametersData;
        this.nameof = nameof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }
        final var that = (InvokeMethodData) o;
        return (
            EqualsPredicates.isEqual(parametersData, that.parametersData) &&
            EqualsPredicates.isEqual(nameof, that.nameof)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(parametersData, nameof);
    }
}
