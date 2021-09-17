package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumGetOrderConstants;
import com.neathorium.thorium.framework.selenium.records.ExternalSeleniumSelectorData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWithOptionsData;
import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.framework.core.constants.AdjusterConstants;
import com.neathorium.thorium.framework.core.namespaces.factory.InternalSelectorDataFactory;
import com.neathorium.thorium.framework.core.records.InternalSelectorData;
import com.neathorium.thorium.framework.core.records.ProbabilityData;

public interface LazyElementWithOptionsDataFactory {
    static LazyElementWithOptionsData get(
        LazyElement element,
        InternalSelectorData internalData,
        ExternalSeleniumSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
       return new LazyElementWithOptionsData(element, internalData, externalData, getOrder, probabilityData);
    }

    static LazyElementWithOptionsData getWithComputedInternalSelectorData(
        LazyElement element,
        ExternalSeleniumSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
       return new LazyElementWithOptionsData(element, InternalSelectorDataFactory.getWithDefaultRange(element.parameters.size()), externalData, getOrder, probabilityData);
    }

    static LazyElementWithOptionsData getWithDefaultProbabilityData(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        return get(element, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA);
    }

    static LazyElementWithOptionsData getWithDefaultGetOrder(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        return get(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, probabilityData);
    }

    static LazyElementWithOptionsData getWithDefaultGetOrderAndProbabilityData(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData) {
        return getWithDefaultProbabilityData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(LazyElement element, ExternalSeleniumSelectorData externalData) {
        return getWithComputedInternalSelectorData(element, externalData, SeleniumGetOrderConstants.DEFAULT, AdjusterConstants.PROBABILITY_DATA);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataAndGetOrder(LazyElement element, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        return getWithComputedInternalSelectorData(element, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataAndProbabilityData(LazyElement element, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        return getWithComputedInternalSelectorData(element, externalData, SeleniumGetOrderConstants.DEFAULT, probabilityData);
    }

    static LazyElementWithOptionsData getWithSpecificLazyElement(LazyElement element) {
        return getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(element, null);
    }

    static LazyElementWithOptionsData getWithDefaults() {
        return getWithSpecificLazyElement(SeleniumCoreConstants.NULL_LAZY_ELEMENT);
    }
}
