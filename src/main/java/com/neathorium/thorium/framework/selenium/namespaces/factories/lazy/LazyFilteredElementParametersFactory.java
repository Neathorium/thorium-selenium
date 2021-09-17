package com.neathorium.thorium.framework.selenium.namespaces.factories.lazy;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.namespaces.factories.ElementFilterDataFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.ElementFilterData;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import org.openqa.selenium.WebElement;

import static com.neathorium.thorium.framework.selenium.constants.LazyIndexedElementFactoryConstants.GETTER;
import static com.neathorium.thorium.core.extensions.constants.IExtendedListConstants.FIRST_INDEX;
import static com.neathorium.thorium.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.PROBABILITY;

public interface LazyFilteredElementParametersFactory {
    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, double probability, LazyLocatorList lazyLocators, String getter) {
        return new LazyFilteredElementParameters(data, data.isFiltered ? WebElement.class : WebElementList.class, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, double probability, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocator(data, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocator(data, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocator(data, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators, String getter) {
        final var filterData = ElementFilterDataFactory.getWithDefaultStringHandler(isFiltered, message);
        return getWithFilterDataAndLocatorList(filterData, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators, String getter) {
        final var filterData = ElementFilterDataFactory.getWithDefaultIndexHandler(isFiltered, index);
        return getWithFilterDataAndLocatorList(filterData, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, LazyLocatorList lazyLocators, String getter) {
        final var filterData = ElementFilterDataFactory.getWithDefaultStringHandler(isFiltered, message);
        return getWithFilterDataAndLocatorList(filterData, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, LazyLocatorList lazyLocators, String getter) {
        final var filterData = ElementFilterDataFactory.getWithDefaultIndexHandler(isFiltered, index);
        return getWithFilterDataAndLocatorList(filterData, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, message, PROBABILITY, lazyLocators, getter);
    }


    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, index, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorListAndIndex(LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, FIRST_INDEX, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, index, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, index, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, probability, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(true, index, probability, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, index, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(isFiltered, message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, message, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(true, message, probability, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, GETTER);
    }
}