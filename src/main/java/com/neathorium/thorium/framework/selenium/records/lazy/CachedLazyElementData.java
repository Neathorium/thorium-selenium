package com.neathorium.thorium.framework.selenium.records.lazy;

import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.framework.core.records.lazy.CachedLazyData;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.thorium.java.extensions.classes.DecoratedList;

import java.util.Map;

public class CachedLazyElementData extends CachedLazyData<LazyFilteredElementParameters, LazyElement> {
    public CachedLazyElementData(LazyElement element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        super(element, typeKeys);
    }
}
