package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.Map;

public record CacheElementDefaultsData(String NAME, Map<String, CachedLazyElementData> REPOSITORY, Data<Boolean> DEFAULT_VALUE) {}
