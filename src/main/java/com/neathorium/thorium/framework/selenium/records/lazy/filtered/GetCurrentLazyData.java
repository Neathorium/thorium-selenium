package com.neathorium.thorium.framework.selenium.records.lazy.filtered;

import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetCurrentLazyData) o;
        return (
            CoreUtilities.isEqual(data, that.data) &&
            CoreUtilities.isEqual(elementFilterData, that.elementFilterData) &&
            CoreUtilities.isEqual(locators, that.locators) &&
            CoreUtilities.isEqual(getter, that.getter)
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
