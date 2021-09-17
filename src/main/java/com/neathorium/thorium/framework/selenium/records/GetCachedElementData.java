package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class GetCachedElementData {
    public final String nameof;
    public final BiFunction<Map<String, CachedLazyElementData>, String, String> validator;
    public final BiFunction<String, CachedLazyElementData, CachedLazyElementData> getter;
    public final BiFunction<Boolean, String, String> formatter;
    public final CachedLazyElementData defaultValue;
    public final Map<String, CachedLazyElementData> repository;

    public GetCachedElementData(
        String nameof,
        BiFunction<Map<String, CachedLazyElementData>, String, String> validator,
        BiFunction<String, CachedLazyElementData, CachedLazyElementData> getter,
        BiFunction<Boolean, String, String> formatter,
        CachedLazyElementData defaultValue,
        Map<String, CachedLazyElementData> repository
    ) {
        this.nameof = nameof;
        this.validator = validator;
        this.getter = getter;
        this.formatter = formatter;
        this.defaultValue = defaultValue;
        this.repository = repository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetCachedElementData) o;
        return (
            Objects.equals(nameof, that.nameof) &&
            Objects.equals(validator, that.validator) &&
            Objects.equals(getter, that.getter) &&
            Objects.equals(formatter, that.formatter) &&
            Objects.equals(defaultValue, that.defaultValue) &&
            Objects.equals(repository, that.repository)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameof, validator, getter, formatter, defaultValue, repository);
    }
}
