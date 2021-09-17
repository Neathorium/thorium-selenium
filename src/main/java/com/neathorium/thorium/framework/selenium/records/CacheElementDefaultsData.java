package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.Data;

import java.util.Map;
import java.util.Objects;

public class CacheElementDefaultsData {
    public final String name;
    public final Map<String, CachedLazyElementData> repository;
    public final Data<Boolean> defaultValue;

    public CacheElementDefaultsData(String name, Map<String, CachedLazyElementData> repository, Data<Boolean> defaultValue) {
        this.name = name;
        this.repository = repository;
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (CacheElementDefaultsData) o;
        return (
            CoreUtilities.isEqual(name, that.name) &&
            CoreUtilities.isEqual(repository, that.repository) &&
            CoreUtilities.isEqual(defaultValue, that.defaultValue)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, repository, defaultValue);
    }

    @Override
    public String toString() {
        return (
            "CacheElementDefaultsData{" +
            "name='" + name + '\'' +
            ", repository=" + repository +
            ", defaultValue=" + defaultValue +
            '}'
        );
    }
}
