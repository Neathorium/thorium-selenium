package com.neathorium.thorium.framework.selenium.abstracts.reflection;

import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;

public abstract class InvokeBaseMessageData {
    public final String MESSAGE;
    public final String RETURN_TYPE;
    public final String PARAMETER_TYPES;

    public InvokeBaseMessageData(String message, String returnType, String parameterTypes) {
        this.MESSAGE = message;
        this.RETURN_TYPE = returnType;
        this.PARAMETER_TYPES = parameterTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (InvokeBaseMessageData) o;
        return (
            EqualsPredicates.isEqual(MESSAGE, that.MESSAGE) &&
            EqualsPredicates.isEqual(RETURN_TYPE, that.RETURN_TYPE) &&
            EqualsPredicates.isEqual(PARAMETER_TYPES, that.PARAMETER_TYPES)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(MESSAGE, RETURN_TYPE, PARAMETER_TYPES);
    }

    @Override
    public String toString() {
        return (
            "InvokeBaseMessageData{" +
            "message='" + MESSAGE + '\'' +
            ", returnType='" + RETURN_TYPE + '\'' +
            ", parameterTypes='" + PARAMETER_TYPES + '\'' +
            '}'
        );
    }
}
