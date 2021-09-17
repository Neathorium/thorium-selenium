package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;

import java.util.Objects;

public class SeleniumTypedEnumKeyData<T> {
    public final SeleniumTypeKey key;
    public final Class<T> clazz;

    public SeleniumTypedEnumKeyData(SeleniumTypeKey key, Class<T> clazz) {
        this.key = key;
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (SeleniumTypedEnumKeyData<?>) o;
        return (
            CoreUtilities.isEqual(key, that.key) &&
            CoreUtilities.isEqual(clazz, that.clazz)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, clazz);
    }

    @Override
    public String toString() {
        return (
            "SeleniumTypedEnumKeyData{" +
            "key=" + key +
            ", clazz=" + clazz +
            '}'
        );
    }
}