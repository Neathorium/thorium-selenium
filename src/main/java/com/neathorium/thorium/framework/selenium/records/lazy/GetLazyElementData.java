package com.neathorium.thorium.framework.selenium.records.lazy;

import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.function.Predicate;

public class GetLazyElementData<ReturnType, ListType> {
    public final TriFunction<Data<ReturnType>, Integer, Integer, Boolean> exitCondition;
    public final TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> cacheFunction;
    public final TriFunction<
        BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>,
        LazyLocatorList,
        String,
            DriverFunction<ReturnType>
    > getter;
    public final Predicate<Data<ReturnType>> invalidator;
    public final Data<ReturnType> defaultValue;

    public GetLazyElementData(
        TriFunction<Data<ReturnType>, Integer, Integer, Boolean> exitCondition,
        TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> cacheFunction,
        TriFunction<BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>, LazyLocatorList, String, DriverFunction<ReturnType>> getter,
        Predicate<Data<ReturnType>> invalidator,
        Data<ReturnType> defaultValue
    ) {
        this.exitCondition = exitCondition;
        this.cacheFunction = cacheFunction;
        this.getter = getter;
        this.invalidator = invalidator;
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
        final var that = (GetLazyElementData<?, ?>) o;
        return (
            CoreUtilities.isEqual(exitCondition, that.exitCondition) &&
            CoreUtilities.isEqual(cacheFunction, that.cacheFunction) &&
            CoreUtilities.isEqual(getter, that.getter) &&
            CoreUtilities.isEqual(invalidator, that.invalidator) &&
            CoreUtilities.isEqual(defaultValue, that.defaultValue)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(exitCondition, cacheFunction, getter, invalidator, defaultValue);
    }

    @Override
    public String toString() {
        return (
            "GetLazyElementData{" +
            "exitCondition=" + exitCondition +
            ", cacheFunction=" + cacheFunction +
            ", getter=" + getter +
            ", invalidator=" + invalidator +
            ", defaultValue=" + defaultValue +
            '}'
        );
    }
}
