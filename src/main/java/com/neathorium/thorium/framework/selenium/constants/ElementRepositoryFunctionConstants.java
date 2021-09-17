package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.namespaces.element.validators.ElementRepositoryValidators;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.GetCachedElementData;

public abstract class ElementRepositoryFunctionConstants {
    public static final GetCachedElementData GET_ELEMENT_DEFAULTS = new GetCachedElementData(
        "getElement",
        ElementRepositoryValidators::isInvalidContainsElementMessage,
        RepositoryConstants.CACHED_ELEMENTS::getOrDefault,
        SeleniumFormatter::getElementFoundInCacheMessage,
        SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA,
        RepositoryConstants.CACHED_ELEMENTS
    );
}
