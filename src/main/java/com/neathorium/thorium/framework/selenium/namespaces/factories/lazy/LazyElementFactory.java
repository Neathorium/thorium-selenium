package com.neathorium.thorium.framework.selenium.namespaces.factories.lazy;

import com.neathorium.thorium.framework.selenium.constants.RepositoryConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.ElementParameters;
import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.core.namespaces.validators.Invalids;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.thorium.java.extensions.constants.IExtendedListConstants;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface LazyElementFactory {
    private static <T> String getComplexKey(String name, Map<String, LazyFilteredElementParameters> parameters, Predicate<LazyFilteredElementParameters> validator) {
        return "Regular-" + Objects.hash(name, parameters, validator) + "-element";
    }

    static LazyElement getWith(String name, Map<String, LazyFilteredElementParameters> parameters, Predicate<LazyFilteredElementParameters> validator) {
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementParametersMessage(name, parameters, validator);
        if (isNotBlank(errorMessage)) {
            throw new IllegalArgumentException(errorMessage);
        }

        final var key = getComplexKey(name, parameters, validator);
        if (RepositoryConstants.FACTORY_ELEMENTS.containsKey(key)) {
            throw new IllegalArgumentException("Name collision in lazy element repository" + CoreFormatterConstants.END_LINE);
        }

        return new LazyElement(name, parameters, validator);
    }

    static LazyElement getWith(AbstractLazyResult<LazyFilteredElementParameters> element) {
        return getWith(element.NAME, SeleniumUtilities.getParametersCopy(element.PARAMETERS), element.VALIDATOR);
    }

    static LazyElement getWithInvalidAlwaysFalseValidator(String name, Map<String, LazyFilteredElementParameters> parameters) {
        return getWith(name, parameters, Invalids::defaultFalseValidator);
    }

    static LazyElement getWithInvalidData(String name) {
        return getWithInvalidAlwaysFalseValidator(name, new HashMap<>());
    }

    static LazyElement getWithDefaultValidator(String name, Map<String, LazyFilteredElementParameters> parameters) {
        return getWith(name, parameters, ElementParameters::isValidLazyFilteredElement);
    }

    static LazyElement getWithDefaultNameAndValidator(Map<String, LazyFilteredElementParameters> parameters) {
        return getWithDefaultValidator("id-" + SeleniumCoreConstants.ATOMIC_COUNT.getAndIncrement(), parameters);
    }

    static LazyElement getWithFilterParametersAndNestedLocator(String name, boolean isIndexed, int index, LazyLocatorList locators, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocatorList(isIndexed, index, locators, getter);
        final var map = LazyElementParameterMapFactory.getWithNestedLocatorAndParameters(parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParametersAndNestedLocator(String name, boolean isFiltered, String message, LazyLocatorList locators, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocatorList(isFiltered, message, locators, getter);
        final var map = LazyElementParameterMapFactory.getWithNestedLocatorAndParameters(parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParametersAndNestedLocator(String name, int index, LazyLocatorList locators, String getter) {
        return getWithFilterParametersAndNestedLocator(name, true, index, locators, getter);
    }

    static LazyElement getWithFilterParametersAndNestedLocator(String name, LazyLocatorList locators, String getter) {
        return getWithFilterParametersAndNestedLocator(name, false, IExtendedListConstants.FIRST_INDEX, locators, getter);
    }

    static LazyElement getWithFilterParametersAndNestedLocator(String name, String message, LazyLocatorList locators, String getter) {
        return getWithFilterParametersAndNestedLocator(name, true, message, locators, getter);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(isIndexed, index, locator, getter);
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(locator.STRATEGY, parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(isFiltered, message, locator, getter);
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(locator.STRATEGY, parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, isIndexed, 0, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, int index, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, index, locator, getter);
    }

    static LazyElement getWithFilterParametersAndDefaultIndex(String name, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, IExtendedListConstants.FIRST_INDEX, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, index, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, 0, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, true, index, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, LazyLocator locator) {
        return getWithFilterParameters(name, true, 0, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, LazyLocatorList locators, String getter) {
        final var parameterList = new ArrayList<LazyFilteredElementParameters>();
        final var keyList = new ArrayList<String>();
        for(var locator : locators) {
            parameterList.add(LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(true, 0, locator, getter));
            keyList.add(locator.STRATEGY + "-" + SeleniumCoreConstants.ELEMENT_ATOMIC_COUNT.getAndIncrement() + "-generated");
        }

        final var map = LazyElementParameterMapFactory.getWith(IntStream.range(0, keyList.size()).boxed().collect(Collectors.toMap(keyList::get, parameterList::get)));

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParameters(String name, LazyLocatorList locators) {
        return getWithFilterParameters(name, locators, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, String message, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, message, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator) {
        return getWithFilterParameters(name, isFiltered, message, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, String message, LazyLocator locator) {
        return getWithFilterParameters(name, true, message, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getSimple(String name, LazyLocator locator, String getter) {
        return getWithFilterParametersAndDefaultIndex(name, locator, getter);
    }

    static LazyElement getSimple(String name, LazyLocator locator) {
        return getWithFilterParameters(name, locator);
    }

    static LazyElement getSimple(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, index, locator);
    }

    static LazyElement getSimple(String name, String message, LazyLocator locator) {
        return getWithFilterParameters(name, message, locator);
    }

    static LazyElement getWithFilterParameters(By locator, SingleGetter getter) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, lazyLocator, getter.getName());
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(lazyLocator.STRATEGY, parameters);

        return getWithDefaultNameAndValidator(map);
    }

    static LazyElement getWithFilterParameters(By locator) {
        return getWithFilterParameters(locator, SingleGetter.DEFAULT);
    }
}
