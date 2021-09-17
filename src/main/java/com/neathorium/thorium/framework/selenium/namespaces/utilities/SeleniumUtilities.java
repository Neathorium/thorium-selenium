package com.neathorium.thorium.framework.selenium.namespaces.utilities;

import com.neathorium.thorium.framework.selenium.abstracts.AbstractWaitParameters;
import com.neathorium.thorium.framework.selenium.constants.ElementStrategyMapConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.enums.SeleniumSelectorStrategy;
import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyFilteredElementParametersFactory;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumLazyLocatorValidators;
import com.neathorium.thorium.framework.selenium.records.element.regular.ElementWaitParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWaitParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.ElementFilterData;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.constants.CoreConstants;
import com.neathorium.thorium.core.extensions.namespaces.EmptiableFunctions;
import com.neathorium.thorium.core.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.neathorium.thorium.core.extensions.namespaces.CoreUtilities.areAll;
import static com.neathorium.thorium.core.extensions.namespaces.CoreUtilities.areAny;
import static com.neathorium.thorium.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.neathorium.thorium.core.extensions.namespaces.CoreUtilities.isEqual;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumUtilities {
    static boolean isInvalidLazyLocator(LazyLocator data) {
        return isNull(data) || isBlank(data.LOCATOR) || isNull(data.STRATEGY);
    }

    static boolean isValidLazyLocator(LazyLocator data) {
        return !isInvalidLazyLocator(data);
    }

    static boolean areNullLazyLocators(LazyLocator... data) {
        return areAll(SeleniumUtilities::isInvalidLazyLocator, data);
    }

    static LazyLocator[] getEmptyLazyLocatorArray() {
        return new LazyLocator[0];
    }

    static boolean areNullLazyLocators(List<LazyLocator> data) {
        return areNullLazyLocators(data.toArray(getEmptyLazyLocatorArray()));
    }

    static boolean isNullLazyDataList(LazyLocatorList list) {
        return EmptiableFunctions.isNullOrEmpty(list) || areNullLazyLocators(list.list);
    }

    static boolean isNotNullLazyLocator(LazyLocator data) {
        return !isInvalidLazyLocator(data);
    }

    static <T> boolean isNullAbstractLazyElementParametersList(Collection<T> data, Predicate<T> validator) {
        for(T params : data) {
            if (validator.test(params)) {
                return false;
            }
        }

        return true;
    }

    static <T> boolean isNullLazyElement(AbstractLazyResult<T> element) {
        return (
            isNull(element) ||
            isBlank(element.name) ||
            areAnyNull(element.parameters, element.validator) ||
            element.parameters.isEmpty() ||
            isNullAbstractLazyElementParametersList(element.parameters.values(), element.validator)
        );
    }

    static <T> boolean isNotNullLazyElement(AbstractLazyResult<T> data) {
        return !isNullLazyElement(data);
    }

    static boolean isNullWebElement(WebElement element) {
        return (
            isNull(element) ||
            Objects.equals(SeleniumDataConstants.NULL_ELEMENT.object, element) ||
            isEqual(element.getAttribute("id"), SeleniumFormatterConstants.NULL_ELEMENT_ID)
        );
    }

    static boolean isNotNullWebElement(WebElement element) {
        return !isNullWebElement(element);
    }

    static boolean isNullWebElement(Data<WebElement> element) {
        return DataPredicates.isInvalidOrFalse(element) || Objects.equals(SeleniumDataConstants.NULL_ELEMENT, element) || isNullWebElement(element.object);
    }

    static boolean isNotNullWebElement(Data<WebElement> element) {
        return !isNullWebElement(element);
    }

    static <T> boolean isNullCommonWaitParametersData(AbstractWaitParameters<T> data) {
        return isNull(data) || areAny(BasicPredicates::isNegative, data.duration, data.interval);
    }

    static boolean isNullElementWaitParametersData(ElementWaitParameters data) {
        return isNullCommonWaitParametersData(data) || isNull(data.object);
    }

    static boolean isNotNullElementWaitParametersData(ElementWaitParameters data) {
        return !isNullElementWaitParametersData(data);
    }

    static boolean isNullLazyElementWaitParametersData(LazyElementWaitParameters data) {
        return isNullCommonWaitParametersData(data) || isNullLazyElement(data.object);
    }

    static boolean isNotNullLazyElementWaitParametersData(LazyElementWaitParameters data) {
        return !isNullLazyElementWaitParametersData(data);
    }


    static LazyLocatorList getLazyLocatorList(By locator) {
        return LazyLocatorListFactory.getWithSingleItem(SeleniumLazyLocatorFactory.get(locator));
    }

    static Data<By> getLocator(Map<SeleniumSelectorStrategy, Function<String, By>> strategyMap, LazyLocator data) {
        final var errorMessage = SeleniumLazyLocatorValidators.isInvalidLazyLocator(data);
        if (isNotBlank(errorMessage)) {
            return SeleniumDataConstants.NULL_BY;
        }

        final var locator = data.LOCATOR;
        final var strategy = data.STRATEGY;
        final var object = strategyMap.get(SeleniumSelectorStrategy.getValueOf(strategy)).apply(locator);
        return DataFactoryFunctions.getValidWith(object, "getLocator", "Locator: By " + strategy + " with locator: " + locator);
    }

    static Data<By> getLocator(LazyLocator data) {
        return getLocator(ElementStrategyMapConstants.STRATEGY_MAP, data);
    }

    static <T> Map<T, LazyFilteredElementParameters> getParametersCopy(Map<T, LazyFilteredElementParameters> source) {
        final var keys = source.keySet().iterator();
        final var values = source.values().iterator();

        final var map = Collections.synchronizedMap(new LinkedHashMap<T, LazyFilteredElementParameters>());
        LazyFilteredElementParameters lep;
        while(keys.hasNext() && values.hasNext()) {
            lep = values.next();
            map.putIfAbsent(keys.next(), LazyFilteredElementParametersFactory.getWithFilterDataAndLocatorList((ElementFilterData<?>) lep.ELEMENT_FILTER_DATA, lep.PROBABILITY, lep.LAZY_LOCATORS, lep.GETTER));
        }

        return map;
    }

    static Object[] unwrapToArray(Data<?> data) {
        return DataPredicates.isValidNonFalse(data) ? ArrayUtils.toArray(data.object) : CoreConstants.EMPTY_OBJECT_ARRAY;
    }
}
