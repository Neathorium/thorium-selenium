package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.framework.selenium.records.CacheElementDefaultsData;

public abstract class CacheElementDefaults {
    public static final String FUNCTION_NAME = "cacheElement";
    public static final CacheElementDefaultsData CACHE_DEFAULT = new CacheElementDefaultsData(FUNCTION_NAME, RepositoryConstants.CACHED_ELEMENTS, CoreDataConstants.NULL_BOOLEAN);
}
