package com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.dynamic;

import com.neathorium.thorium.framework.selenium.constants.RepositoryConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.simple.SimpleLazyElementFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.core.namespaces.validators.LazyLocatorValidators;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public interface DynamicLazyElementFactory {
    static <T> String getComplexKey(String name, T object) {
        return NullablePredicates.isNotNull(object) && StringUtils.isNotBlank(name) ? "Dynamic-" + Objects.hash(name, object) + "-element" : "";
    }

    static LazyElement getWith(Map<String, LazyElement> map, String name, String message, LazyLocator locator) {
        final var defaultElement = SeleniumDataConstants.NULL_LAZY_ELEMENT.OBJECT();
        if (
            NullablePredicates.isNull(map) ||
            StringUtilities.areAnyBlank(name, message) ||
            StringUtils.isNotBlank(LazyLocatorValidators.isInvalidLazyLocator(locator))
        ) {
            return defaultElement;
        }

        final var key = DynamicLazyElementFactory.getComplexKey(name, message);
        if (StringUtils.isBlank(key)) {
            return defaultElement;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        final var element = SimpleLazyElementFactory.getSimple(name, message, locator);
        map.put(key, element);
        return map.getOrDefault(key, defaultElement);
    }

    static LazyElement getWith(Map<String, LazyElement> map, String name, int index, LazyLocator locator) {
        final var defaultElement = SeleniumDataConstants.NULL_LAZY_ELEMENT.OBJECT();
        if (
            BasicPredicates.isNegative(index) ||
            NullablePredicates.isNull(map) ||
            StringUtils.isBlank(name) ||
            StringUtils.isNotBlank(LazyLocatorValidators.isInvalidLazyLocator(locator))
        ) {
            return defaultElement;
        }

        final var key = DynamicLazyElementFactory.getComplexKey(name, index);
        if (StringUtils.isBlank(key)) {
            return defaultElement;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        final var element = SimpleLazyElementFactory.getSimple(name, index, locator);
        map.put(key, element);
        return map.getOrDefault(key, defaultElement);
    }

    static LazyElement getWith(String name, String message, LazyLocator locator) {
        return DynamicLazyElementFactory.getWith(RepositoryConstants.FACTORY_ELEMENTS, name, message, locator);
    }

    static LazyElement getWith(String name, int index, LazyLocator locator) {
        return DynamicLazyElementFactory.getWith(RepositoryConstants.FACTORY_ELEMENTS, name, index, locator);
    }
}
