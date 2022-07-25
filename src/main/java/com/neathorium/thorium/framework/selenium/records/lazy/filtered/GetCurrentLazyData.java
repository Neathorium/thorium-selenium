package com.neathorium.thorium.framework.selenium.records.lazy.filtered;

import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class GetCurrentLazyData {
    public final CachedLookupKeysData data;
    public final BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters<?, ?>, WebElementList, WebElement> elementFilterData;
    public final LazyLocatorList locators;
    public final String getter;

    public GetCurrentLazyData(CachedLookupKeysData data, BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters<?, ?>, WebElementList, WebElement> elementFilterData, LazyLocatorList locators, String getter) {
        this.data = data;
        this.elementFilterData = elementFilterData;
        this.locators = locators;
        this.getter = getter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetCurrentLazyData) o;
        return (
            EqualsPredicates.isEqual(data, that.data) &&
            EqualsPredicates.isEqual(elementFilterData, that.elementFilterData) &&
            EqualsPredicates.isEqual(locators, that.locators) &&
            EqualsPredicates.isEqual(getter, that.getter)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, elementFilterData, locators, getter);
    }

    @Override
    public String toString() {
        return (
            "GetCurrentLazyData{" +
            "data=" + data +
            ", elementFilterData=" + elementFilterData +
            ", locators=" + locators +
            ", getter='" + getter + '\'' +
            '}'
        );
    }
}
