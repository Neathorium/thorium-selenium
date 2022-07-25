package com.neathorium.thorium.framework.selenium.namespaces.utilities;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.ElementFinderConstants;
import com.neathorium.thorium.framework.selenium.constants.SelectorStrategyNameConstants;
import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.ElementFilterParametersFactory;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.BiPredicate;

import static com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities.isNullWebElement;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface LazyElementUtilities {
    static Data<String> getIndexedData(LazyElement element) {
        final var nameof = "getIndexedData";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final var parameters = element.PARAMETERS;
        final var selectorData = parameters.get(SelectorStrategyNameConstants.CSS_SELECTOR);
        if (NullablePredicates.isNull(selectorData)) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final var status = selectorData.ELEMENT_FILTER_DATA.isFiltered;
        if (!status) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final String object = String.valueOf(selectorData.ELEMENT_FILTER_DATA.filterParameter);
        var handler = "TU.GEBI";
        try {
            Integer.parseInt(object);
        } catch (NumberFormatException ex) {
            handler = "TU.GEBT";
        }
        return DataFactoryFunctions.getWith(object, true, nameof, handler);
    }

    static String getCSSSelectorFromElement(LazyElement element) {
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        if (isNotBlank(errorMessage)) {
            return CoreFormatterConstants.EMPTY;
        }

        final var parameters = element.PARAMETERS;
        final var selectorData = parameters.get(SelectorStrategyNameConstants.CSS_SELECTOR);
        if (NullablePredicates.isNull(selectorData)) {
            return CoreFormatterConstants.EMPTY;
        }
        final var lazyLocators = selectorData.LAZY_LOCATORS;
        final var lazyLocator = lazyLocators.first();
        return lazyLocator.LOCATOR;
    }

    static boolean lazyExitConditionCore(Data<WebElement> element, int index, int attempts) {
        return isNullWebElement(element) && BasicPredicates.isSmallerThan(index, attempts);
    }

    static BiPredicate<Data<WebElement>, Integer> lazyExitCondition(int attempts) {
        return (element, value) -> lazyExitConditionCore(element, value, attempts);
    }

    static <T> DriverFunction<WebElement> getCurrentLazyElement(
        BaseFilterData<WebDriver, ManyGetter, T, ElementFilterParameters, WebElementList, WebElement> data,
        LazyLocatorList locators,
        String getter
    ) {
        return data.isFiltered ? (
            DriverFunctionFactory.getFunction(data.apply(ElementFilterParametersFactory.getWithManyGetterAndDefaultGetterMap(locators, getter)))
        ) : ElementFilterFunctions.getElement(locators, ElementFinderConstants.singleGetterMap, SingleGetter.getValueOf(getter));
    }
}
