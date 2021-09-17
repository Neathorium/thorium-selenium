package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.framework.selenium.constants.ElementFinderConstants;
import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.Map;
import java.util.function.Function;

public interface ElementFilterParametersFactory {
    private static <T, U> ElementFilterParameters<T, U> getWithCore(LazyLocatorList locators, Map<T, Function<LazyLocatorList, Function<WebDriver, Data<U>>>> getterMap, T getter) {
        return new ElementFilterParameters<>(locators, getterMap, getter);
    }

    private static <T, U> ElementFilterParameters<T, U> getWithCore(LazyLocatorList locators, Map<T, Function<LazyLocatorList, Function<WebDriver, Data<U>>>> getterMap, String getter, Function<String, T> getterFunction) {
        return new ElementFilterParameters<>(locators, getterMap, getterFunction.apply(getter));
    }

    static ElementFilterParameters<ManyGetter, WebElementList> getWithMany(LazyLocatorList locators, Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElementList>>>> getterMap, ManyGetter getter) {
        return getWithCore(locators, getterMap, getter);
    }

    static ElementFilterParameters<ManyGetter, WebElementList> getWithMany(LazyLocatorList locators, Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElementList>>>> getterMap, String getter) {
        return getWithCore(locators, getterMap, getter, ManyGetter::getValueOf);
    }

    static ElementFilterParameters<ManyGetter, WebElementList> getWithManyDefaultGetterMap(LazyLocatorList locators, ManyGetter getter) {
        return getWithCore(locators, ElementFinderConstants.manyGetterMap, getter);
    }

    static ElementFilterParameters<ManyGetter, WebElementList> getWithManyGetterAndDefaultGetterMap(LazyLocatorList locators, String getter) {
        return getWithMany(locators, ElementFinderConstants.manyGetterMap, getter);
    }

    static ElementFilterParameters<ManyGetter, WebElementList> getWithManyGetterAndDefaultGetterMap(LazyLocatorList locators) {
        return getWithMany(locators, ElementFinderConstants.manyGetterMap, ManyGetter.DEFAULT);
    }

    static ElementFilterParameters<SingleGetter, WebElement> getWithSingle(LazyLocatorList locators, Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElement>>>> getterMap, SingleGetter getter) {
        return getWithCore(locators, getterMap, getter);
    }

    static ElementFilterParameters<SingleGetter, WebElement> getWithSingle(LazyLocatorList locators, Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElement>>>> getterMap, String getter) {
        return getWithCore(locators, getterMap, getter, SingleGetter::getValueOf);
    }

    static ElementFilterParameters<SingleGetter, WebElement> getWithSingleDefaultGetterMap(LazyLocatorList locators, SingleGetter getter) {
        return getWithCore(locators, ElementFinderConstants.singleGetterMap, getter);
    }

    static ElementFilterParameters<SingleGetter, WebElement> getWithSingleGetterAndDefaultGetterMap(LazyLocatorList locators, String getter) {
        return getWithSingle(locators, ElementFinderConstants.singleGetterMap, getter);
    }

    static ElementFilterParameters<SingleGetter, WebElement> getWithSingleGetterAndDefaultGetterMap(LazyLocatorList locators) {
        return getWithSingle(locators, ElementFinderConstants.singleGetterMap, SingleGetter.DEFAULT);
    }
}
