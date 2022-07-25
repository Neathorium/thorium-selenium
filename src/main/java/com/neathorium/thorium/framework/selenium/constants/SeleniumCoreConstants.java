package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.namespaces.factories.WebElementListFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.ElementRepository;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.BasicTypeUtilities;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.thorium.framework.selenium.repositories.method.constants.MethodRepositoryConstants;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodSourceData;
import com.neathorium.thorium.java.extensions.classes.DecoratedList;
import com.neathorium.thorium.java.extensions.classes.boilers.StringSet;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public abstract class SeleniumCoreConstants {
    public static final Method[] WEBELEMENT_METHODS = WebElement.class.getMethods();
    public static final HashMap<String, MethodData> METHODS = new HashMap<>();
    public static final WebElement STOCK_ELEMENT = BasicTypeUtilities.getStockElement();
    public static final WebElementList NULL_ELEMENT_LIST = WebElementListFactory.getWithEmptyList();

    public static AtomicInteger ATOMIC_COUNT = new AtomicInteger();
    public static AtomicInteger ELEMENT_ATOMIC_COUNT = new AtomicInteger();
    public static final LazyElement NULL_LAZY_ELEMENT = LazyElementFactory.getWithInvalidData("Null Lazy Element -1");
    public static final Map<String, DecoratedList<SelectorKeySpecificityData>> NULL_CACHED_KEYS = ElementRepository.getInitializedTypeKeysMap();
    public static final CachedLazyElementData NULL_CACHED_LAZY_ELEMENT_DATA = new CachedLazyElementData(NULL_LAZY_ELEMENT, NULL_CACHED_KEYS);
    public static final CachedLookupKeysData INITIAL_UNCACHED_DATA = new CachedLookupKeysData("", "", "", 0);
    public static final ExternalElementData NULL_EXTERNAL_ELEMENT_DATA = new ExternalElementData(NULL_CACHED_KEYS, SeleniumDataConstants.NULL_ELEMENT);

    public static final List<Class<?>> CLASSES_OF_GET_MECHANISMS = Arrays.asList(WebElementList.class, WebElement.class);

    public static final StringSet NULL_STRING_SET = new StringSet(new HashSet<>());
    public static final List<Method> WEB_ELEMENT_METHOD_LIST = Arrays.asList(WEBELEMENT_METHODS);
    public static final MethodSourceData DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS = new MethodSourceData(MethodRepositoryConstants.METHODS, SeleniumCoreConstants.WEB_ELEMENT_METHOD_LIST, MethodRepositoryConstants.NULL_METHODDATA);
    public static final Function<Object, WebElement> WEB_ELEMENT_CASTER_FUNCTION = WebElement.class::cast;

    public static final Function<Data<DecoratedList<?>>, String> WEBELEMENT_LIST_VALIDATOR = CoreFormatter.isValidTypedNonEmptyListMessage(WebElement.class);
}
