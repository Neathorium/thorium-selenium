package com.neathorium.thorium.framework.selenium.records.lazy;

import com.neathorium.thorium.framework.selenium.records.ExternalSeleniumSelectorData;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.neathorium.thorium.framework.core.records.InternalSelectorData;
import com.neathorium.thorium.framework.core.records.ProbabilityData;
import org.openqa.selenium.WebDriver;

public class LazyElementWithOptionsData extends AbstractLazyElementWithOptionsData<LazyFilteredElementParameters, LazyElement, WebDriver, ExternalSeleniumSelectorData> {
    public LazyElementWithOptionsData(
        LazyElement element,
        InternalSelectorData internalData,
        ExternalSeleniumSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
        super(element, internalData, externalData, getOrder, probabilityData);
    }
}
