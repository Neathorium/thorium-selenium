package com.neathorium.thorium.framework.selenium.namespaces.repositories;

import com.neathorium.thorium.framework.selenium.constants.CacheElementDefaults;
import com.neathorium.thorium.framework.selenium.constants.ElementRepositoryFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.ElementStrategyMapConstants;
import com.neathorium.thorium.framework.selenium.constants.RepositoryConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.enums.SeleniumSelectorStrategy;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.ElementRepositoryValidators;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;
import com.neathorium.thorium.framework.selenium.namespaces.validators.GetCachedElementDataValidators;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.CacheElementDefaultsData;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.GetCachedElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.extensions.namespaces.factories.DecoratedListFactory;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.DataFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.namespaces.FrameworkCoreUtilities;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.repositories.CoreElementRepository;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.openqa.selenium.WebElement;
import selectorSpecificity.Specificity;
import selectorSpecificity.constants.Strategy;
import selectorSpecificity.tuples.SpecificityData;

import java.util.Map;
import java.util.function.Predicate;

import static com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter.getNotCachedMessage;
import static com.neathorium.thorium.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.neathorium.thorium.core.namespaces.predicates.DataPredicates.isValidNonFalseAndValidContained;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ElementRepository {
    private static Data<Boolean> cacheElement(Map<String, CachedLazyElementData> repository, CachedLazyElementData data) {
        final var name = data.element.name;
        final var returnValue = repository.put(name, data);
        final var errorMessage = getNotCachedMessage(repository, name);
        final var status = NullableFunctions.isNull(returnValue) && isBlank(errorMessage);
        final var message = status ? SeleniumFormatterConstants.LAZY_ELEMENT + "with name(\"" + name + "\") added to cache" + CoreFormatterConstants.END_LINE : errorMessage;
        return DataFactoryFunctions.getBoolean(status, CacheElementDefaults.FUNCTION_NAME, message);
    }

    static Data<Boolean> cacheElement(CacheElementDefaultsData defaults, CachedLazyElementData data) {
        var errorMessage = SeleniumFormatter.getCacheElementDefaultsDataInvalidMessage(defaults);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, "cacheElement", errorMessage);
        }

        errorMessage = SeleniumFormatter.getCacheElementParametersErrorMessage(defaults, data);
        return isBlank(errorMessage) ? cacheElement(defaults.repository, data) : DataFactoryFunctions.replaceMessage(defaults.defaultValue, defaults.name, errorMessage);
    }

    static Data<Boolean> cacheElement(LazyElement element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        return cacheElement(CacheElementDefaults.CACHE_DEFAULT, new CachedLazyElementData(element, typeKeys));
    }

    static Data<Boolean> containsElement(Map<String, CachedLazyElementData> elementRepository, String name, Data<Boolean> defaultValue) {
        final var nameof = "containsElement";
        final var errorMessage = ElementRepositoryValidators.isInvalidContainsElementMessage(elementRepository, name);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.replaceMessage(defaultValue, nameof, errorMessage);
        }

        final var defaultObject = defaultValue.object;
        final var object = elementRepository.containsKey(name);
        final var message = SeleniumFormatterConstants.LAZY_ELEMENT + CoreFormatter.getOptionMessage(object) + " found by name(\"" + name + "\")" + CoreFormatterConstants.END_LINE;

        return DataFactoryFunctions.getWith(object, true, nameof, message);
    }

    static Data<Boolean> containsElement(String name, Data<Boolean> defaultValue) {
        return containsElement(RepositoryConstants.CACHED_ELEMENTS, name, defaultValue);
    }

    static Data<Boolean> containsElement(String name) {
        return containsElement(RepositoryConstants.CACHED_ELEMENTS, name, CoreDataConstants.NULL_BOOLEAN);
    }

    static Data<CachedLazyElementData> getElement(GetCachedElementData data, String elementName) {
        final var guardName = "getElement";
        var errorMessage = GetCachedElementDataValidators.getValidCachedElementDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.NULL_CACHED_LAZY_ELEMENT_DATA, guardName, errorMessage);
        }

        final var nameof = data.nameof;
        errorMessage = data.validator.apply(data.repository, elementName);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(data.defaultValue, nameof, errorMessage);
        }

        final var object = data.getter.apply(elementName, data.defaultValue);
        final var status = CoreUtilities.isNotEqual(object, data.defaultValue);
        final var message = data.formatter.apply(status, elementName);
        return DataFactoryFunctions.getWith(object, status, nameof, message);
    }

    static Data<CachedLazyElementData> getElement(String name) {
        return getElement(ElementRepositoryFunctionConstants.GET_ELEMENT_DEFAULTS, name);
    }

    static <T> Data<Boolean> cacheIfAbsent(AbstractLazyResult<LazyFilteredElementParameters> element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        final var cached = ElementRepository.containsElement(element.name);
        final Predicate<Boolean> isFalse = CoreUtilities::isFalse;
        final var status = isValidNonFalseAndValidContained(cached, isFalse);
        return status ? ElementRepository.cacheElement(LazyElementFactory.getWith(element), typeKeys) : cached;
    }

    static Map<String, DecoratedList<SelectorKeySpecificityData>> getInitializedTypeKeysMap() {
        return CoreElementRepository.getInitializedTypeKeysMap(ElementStrategyMapConstants.STRATEGY_MAP_KEY_SET, String.class);
    }

    static <T> Data<CachedLazyElementData> getIfContains(String name) {
        if (isBlank(name)) {
            return DataFactoryFunctions.appendMessage(SeleniumDataConstants.ELEMENT_WAS_NOT_CACHED, "Passed name was blank, no caching possible on that" + CoreFormatterConstants.END_LINE);
        }

        return DataPredicates.isValidNonFalse(ElementRepository.containsElement(name)) ? ElementRepository.getElement(name) : SeleniumDataConstants.ELEMENT_WAS_NOT_CACHED;
    }

    static <T> Data<CachedLazyElementData> getIfContains(AbstractLazyResult<T> element) {
        return getIfContains(element.name);
    }

    static Data<Boolean> updateTypeKeys(LazyLocatorList locators, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, String key) {
        final var nameof = "updateTypeKeys";
        if (NullableFunctions.isNull(key)) {
            return DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Strategy passed" + CoreFormatterConstants.WAS_NULL);
        }

        final var typeKey = DecoratedListFactory.getWith(typeKeys.keySet()).stream().filter(key::startsWith).findFirst();
        if (typeKey.isEmpty()) {
            return DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Types didn't contain type key(\"" + typeKey + "\")" + CoreFormatterConstants.END_LINE);
        }

        final var type = typeKeys.get(typeKey.get());
        final var selectorKeySpecificityData = getSpecificityForSelector(locators, key);
        if (NullableFunctions.isNotNull(type)) {
            type.addNullSafe(selectorKeySpecificityData);
        }

        final var status = type.contains(selectorKeySpecificityData);
        final var message = "Key(\"" + key + "\") with specificity was " + CoreFormatter.getOptionMessage(status) + " added" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, message);
    }

    static Data<Boolean> updateTypeKeys(String name, LazyLocatorList locators, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, String key) {
        final var nameof = "updateTypeKeys";
        final var cached = containsElement(name);
        if (DataPredicates.isInvalidOrFalse(cached)) {
            return prependMessage(cached, nameof, "There were parameter issues" + CoreFormatterConstants.END_LINE);
        }

        return !cached.object ? (updateTypeKeys(locators, typeKeys, key)) : DataFactoryFunctions.getBoolean(true, nameof, "Element(\"" + name + "\") was already cached" + CoreFormatterConstants.END_LINE);
    }

    static SelectorKeySpecificityData getSpecificityForSelector(LazyLocatorList list, String key) {
        return new SelectorKeySpecificityData(
            key,
            Specificity.reduce(
                list.stream().map(
                    locator -> Specificity.getSelectorSpecificity(locator.LOCATOR, (CoreUtilities.isEqual(locator.STRATEGY, SeleniumSelectorStrategy.CSS_SELECTOR) ? Strategy.CSS_SELECTOR : Strategy.XPATH)).specifics
                ).toArray(SpecificityData[]::new)
            )
        );
    }

    static Data<WebElement> cacheValidLazyElement(AbstractLazyResult<LazyFilteredElementParameters> element, Data<ExternalElementData> regular, Data<ExternalElementData> external) {
        final var errorMessage = SeleniumFormatter.getCacheElementValidParametersMessage(element, regular, external);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.replaceMessage(SeleniumDataConstants.NULL_ELEMENT, "cacheValidLazyElement", errorMessage);
        }

        final var regularStatus = DataPredicates.isValidNonFalse(regular);
        final var externalStatus = DataPredicates.isValidNonFalse(external);
        final var bothInvalid = CoreUtilities.isFalse(regularStatus || externalStatus);
        if (bothInvalid) {
            return SeleniumDataConstants.NULL_ELEMENT;
        }

        final var externalElement = (externalStatus ? external : regular).object;
        final var currentElement = externalElement.FOUND;
        final var message = WebElementValidators.isNotNullWebElement(currentElement) ? (
            DataFunctions.getFormattedMessage(ElementRepository.cacheIfAbsent(element, FrameworkCoreUtilities.getKeysCopy(externalElement.TYPE_KEYS)))
        ) : "All approaches were tried" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.prependMessage(currentElement, message);
    }
}
