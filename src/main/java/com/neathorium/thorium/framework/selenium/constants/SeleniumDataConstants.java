package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.classes.boilers.StringSet;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public abstract class SeleniumDataConstants {
    public static final Data<By> NULL_BY = DataFactoryFunctions.getInvalidWith(By.cssSelector(CoreFormatterConstants.EMPTY), "nullBy", "Null By Data.");
    public static final Data<WebElement> NULL_ELEMENT = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.STOCK_ELEMENT, "defaultNullWebElementData", "Internal null element" + CoreFormatterConstants.END_LINE);
    public static final Data<SearchContext> NULL_CONTEXT = DataFactoryFunctions.getInvalidWith((SearchContext)SeleniumCoreConstants.STOCK_ELEMENT, "defaultNullSearchContextData", "Internal null searchContext" + CoreFormatterConstants.END_LINE);
    public static final Data<WebElement> NULL_ELEMENT_NULL_DRIVER = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.STOCK_ELEMENT, "nullElementNullDriver", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<WebElementList> NULL_LIST = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, "nullList", "nullList data" + CoreFormatterConstants.END_LINE);
    public static final Data<WebElementList> DRIVER_WAS_NULL_LIST = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, "driverWasNullList", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<WebElementList> LOCATOR_WAS_NULL_LIST = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_ELEMENT_LIST, "locatorWasNullList", SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    public static final Data<LazyElement> NULL_LAZY_ELEMENT = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_LAZY_ELEMENT, "nullLazyElement", "nullLazyElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyElementData> NULL_CACHED_LAZY_ELEMENT = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA, "nullCachedLazyElement", "nullCachedLazyElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<CachedLazyElementData> ELEMENT_WAS_NOT_CACHED = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA, "getIfContains", "Element wasn't cached" + CoreFormatterConstants.END_LINE);
    public static final Data<ExternalElementData> NULL_EXTERNAL_ELEMENT = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_EXTERNAL_ELEMENT_DATA, "nullExternalElement", "nullExternalElement data" + CoreFormatterConstants.END_LINE);
    public static final Data<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = DataFactoryFunctions.getBoolean(false, SeleniumFormatterConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL);
    public static final Data<Boolean> LAZY_ELEMENT_WAS_NULL = DataFactoryFunctions.getBoolean(false, SeleniumFormatterConstants.LAZY_ELEMENT_WAS_NULL);
    public static final Data<StringSet> NULL_STRING_SET_DATA = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_STRING_SET, "nullStringSet", "Null String Set data" + CoreFormatterConstants.END_LINE);
    public static final Data<WebDriver> NULL_DRIVER = DataFactoryFunctions.getInvalidWith(null, "nullDriver", "No Drivers.Driver instance found" + CoreFormatterConstants.END_LINE);

    public static final Function<WebDriver.TargetLocator, Data<SearchContext>> SWITCH_TO_NEGATIVE = targetLocator -> SeleniumDataConstants.NULL_CONTEXT;
    public static final Data<Boolean> DRIVER_WAS_NULL = DataFactoryFunctions.getInvalidBooleanWith("driverWasNull", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<Integer> NULL_INTEGER_NULL_DRIVER = DataFactoryFunctions.getInvalidWith(0, "nullIntegerNullDriver", SeleniumFormatterConstants.DRIVER_WAS_NULL);
    public static final Data<Integer> NO_ELEMENTS_FOUND = DataFactoryFunctions.getInvalidWith(0, "noElementsFound", SeleniumFormatterConstants.ELEMENT_LIST_EMPTY_OR_NULL);
    public static final Data<Integer> TYPE_NOT_IN_CACHE_MAP_DATA = DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_INTEGER, "typeNotInCacheData", SeleniumFormatterConstants.TYPE_NOT_IN_CACHE_MAP);

    public static final Data<CachedLookupKeysData> INITIAL_UNCACHED_DATA = DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.INITIAL_UNCACHED_DATA, "initialUncachedData", "Element is uncached");
    public static final Data<CachedLookupKeysData> NULL_CACHED_DATA = DataFactoryFunctions.replaceMessage(INITIAL_UNCACHED_DATA, "nullCachedData", "Invalid cache keys" + CoreFormatterConstants.END_LINE);

    public static final Data<String> DATA_WAS_NULL_OR_FALSE_STRING = DataFactoryFunctions.getInvalidWith("", "dataParameterWasNullOrFalse", "Data parameter was null or false.\n");
}