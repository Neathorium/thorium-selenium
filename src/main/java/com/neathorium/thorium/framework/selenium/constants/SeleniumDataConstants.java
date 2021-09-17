package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public abstract class SeleniumDataConstants {
    public static final Data<By> NULL_BY = DataFactoryFunctions.getWith(By.cssSelector(CoreFormatterConstants.EMPTY), false, "nullBy", "Null By Data.");
    public static final Data<WebElement> NULL_ELEMENT = DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, "defaultNullWebElementData", "Internal null element" + CoreFormatterConstants.END_LINE);
    public static final Data<WebElement> NULL_ELEMENT_NULL_DRIVER = DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, "nullElementNullDriver", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<WebElementList> NULL_LIST = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, false, "nullList", "nullList data" + CoreFormatterConstants.END_LINE);
    public static final Data<WebElementList> DRIVER_WAS_NULL_LIST = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, false, "driverWasNullList", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<WebElementList> LOCATOR_WAS_NULL_LIST = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, false, "locatorWasNullList", SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    public static final Data<LazyElement> NULL_LAZY_ELEMENT = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_LAZY_ELEMENT, false, "nullLazyElement", "nullLazyElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyElementData> NULL_CACHED_LAZY_ELEMENT = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA, false, "nullCachedLazyElement", "nullCachedLazyElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyElementData> ELEMENT_WAS_NOT_CACHED = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA, false, "getIfContains", "Element wasn't cached" + CoreFormatterConstants.END_LINE);
    public static final Data<ExternalElementData> NULL_EXTERNAL_ELEMENT = DataFactoryFunctions.getWith(SeleniumCoreConstants.NULL_EXTERNAL_ELEMENT_DATA, false, "nullExternalElement", "nullExternalElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = DataFactoryFunctions.getBoolean(false, SeleniumFormatterConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL);
    public static final Data<Boolean> LAZY_ELEMENT_WAS_NULL = DataFactoryFunctions.getBoolean(false, SeleniumFormatterConstants.LAZY_ELEMENT_WAS_NULL);

    public static final Data<WebDriver> NULL_DRIVER = DataFactoryFunctions.getWith(null, false, "nullDriver", "No Drivers.Driver instance found" + CoreFormatterConstants.END_LINE);

    public static final Function<WebDriver.TargetLocator, Data<Boolean>> SWITCH_TO_NEGATIVE = targetLocator -> CoreDataConstants.NULL_BOOLEAN;
    public static final Data<Boolean> DRIVER_WAS_NULL = DataFactoryFunctions.getInvalidBooleanWith("driverWasNull", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<Integer> NULL_INTEGER_NULL_DRIVER = DataFactoryFunctions.getWith(0, false, "nullIntegerNullDriver", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<Integer> NO_ELEMENTS_FOUND = DataFactoryFunctions.getWith(0, false, "noElementsFound", SeleniumFormatterConstants.ELEMENT_LIST_EMPTY_OR_NULL);
    public static final Data<Integer> TYPE_NOT_IN_CACHE_MAP_DATA = DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_INTEGER, "typeNotInCacheData", SeleniumFormatterConstants.TYPE_NOT_IN_CACHE_MAP);

    public static final Data<CachedLookupKeysData> INITIAL_UNCACHED_DATA = DataFactoryFunctions.getWith(SeleniumCoreConstants.INITIAL_UNCACHED_DATA, false, "initialUncachedData", "Element is uncached");
    public static final Data<CachedLookupKeysData> NULL_CACHED_DATA = DataFactoryFunctions.replaceMessage(INITIAL_UNCACHED_DATA, "nullCachedData", "Invalid cache keys" + CoreFormatterConstants.END_LINE);
}