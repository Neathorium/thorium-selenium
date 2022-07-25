package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.core.records.GetByFilterFormatterData;
import com.neathorium.thorium.java.extensions.classes.DecoratedList;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GetElementByData<T, ElementType, ListType extends DecoratedList<ElementType>> {
    public final String NAMEOF;
    public final BiFunction<Data<ListType>, T, String> VALIDATOR;
    public final BiFunction<Data<ListType>, T, ElementType> GETTER;
    public final Function<GetByFilterFormatterData<T>, String> FORMATTER;
    public final Data<ElementType> DEFAULT_VALUE;
    public final String FILTER_NAME;

    public GetElementByData(
        String nameof,
        BiFunction<Data<ListType>, T, String> validator,
        BiFunction<Data<ListType>, T, ElementType> getter,
        Function<GetByFilterFormatterData<T>, String> formatter,
        Data<ElementType> defaultValue,
        String filterName
    ) {
        this.NAMEOF = nameof;
        this.VALIDATOR = validator;
        this.GETTER = getter;
        this.FORMATTER = formatter;
        this.DEFAULT_VALUE = defaultValue;
        this.FILTER_NAME = filterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetElementByData<?, ?, ?>) o;
        return (
            EqualsPredicates.isEqual(NAMEOF, that.NAMEOF) &&
            EqualsPredicates.isEqual(VALIDATOR, that.VALIDATOR) &&
            EqualsPredicates.isEqual(GETTER, that.GETTER) &&
            EqualsPredicates.isEqual(FORMATTER, that.FORMATTER) &&
            EqualsPredicates.isEqual(DEFAULT_VALUE, that.DEFAULT_VALUE) &&
            EqualsPredicates.isEqual(FILTER_NAME, that.FILTER_NAME)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAMEOF, VALIDATOR, GETTER, FORMATTER, DEFAULT_VALUE, FILTER_NAME);
    }

    @Override
    public String toString() {
        return (
            "GetElementByData{" +
            "nameof='" + NAMEOF + '\'' +
            ", validator=" + VALIDATOR +
            ", getter=" + GETTER +
            ", formatter=" + FORMATTER +
            ", defaultValue=" + DEFAULT_VALUE +
            ", filterName='" + FILTER_NAME + '\'' +
            '}'
        );
    }
}
