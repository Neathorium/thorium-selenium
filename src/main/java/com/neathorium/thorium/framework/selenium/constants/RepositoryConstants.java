package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class RepositoryConstants {
    public static final HashMap<String, CachedLazyElementData> CACHED_ELEMENTS = new HashMap<>();
    public static final Map<String, LazyElement> FACTORY_ELEMENTS = Collections.synchronizedMap(new HashMap<>());
    public static final HashMap<By, String> LOCATOR_ELEMENTS = new HashMap<>();
    public static final HashMap<By, String> NAMED_LOCATORS = new HashMap<>();
}
