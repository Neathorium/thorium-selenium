package com.neathorium.thorium.framework.selenium.records.lazy;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.function.Predicate;

public class GetLazyElementData<ReturnType, ListType> {
    public final TriFunction<Data<ReturnType>, Integer, Integer, Boolean> EXIT_CONDITION;
    public final TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> CACHE_FUNCTION;
    public final TriFunction<
        BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>,
        LazyLocatorList,
        String,
        DriverFunction<ReturnType>
    > GETTER;
    public final Predicate<Data<ReturnType>> INVALIDATOR;
    public final Data<ReturnType> DEFAULT_VALUE;

    public GetLazyElementData(
        TriFunction<Data<ReturnType>, Integer, Integer, Boolean> exitCondition,
        TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> cacheFunction,
        TriFunction<BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>, LazyLocatorList, String, DriverFunction<ReturnType>> getter,
        Predicate<Data<ReturnType>> invalidator,
        Data<ReturnType> defaultValue
    ) {
        this.EXIT_CONDITION = exitCondition;
        this.CACHE_FUNCTION = cacheFunction;
        this.GETTER = getter;
        this.INVALIDATOR = invalidator;
        this.DEFAULT_VALUE = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }
        final var that = (GetLazyElementData<?, ?>) o;
        return (
            EqualsPredicates.isEqual(EXIT_CONDITION, that.EXIT_CONDITION) &&
            EqualsPredicates.isEqual(CACHE_FUNCTION, that.CACHE_FUNCTION) &&
            EqualsPredicates.isEqual(GETTER, that.GETTER) &&
            EqualsPredicates.isEqual(INVALIDATOR, that.INVALIDATOR) &&
            EqualsPredicates.isEqual(DEFAULT_VALUE, that.DEFAULT_VALUE)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(EXIT_CONDITION, CACHE_FUNCTION, GETTER, INVALIDATOR, DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return (
            "GetLazyElementData{" +
            "EXIT_CONDITION=" + EXIT_CONDITION +
            ", CACHE_FUNCTION=" + CACHE_FUNCTION +
            ", GETTER=" + GETTER +
            ", INVALIDATOR=" + INVALIDATOR +
            ", DEFAULT_VALUE=" + DEFAULT_VALUE +
            '}'
        );
    }
}
