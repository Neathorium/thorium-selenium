package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebElement;

public interface WebElementValidators {
    static boolean isNull(WebElement element) {
        return (
            NullablePredicates.isNull(element) ||
            EqualsPredicates.isEqual(element, SeleniumDataConstants.NULL_ELEMENT.OBJECT()) ||
            EqualsPredicates.isEqual(element.getAttribute("id"), SeleniumFormatterConstants.NULL_ELEMENT_ID)
        );
    }

    static boolean isNotNull(WebElement element) {
        return !isNull(element);
    }

    static boolean isNullWebElement(Data<WebElement> data) {
        return DataPredicates.isInvalidOrFalse(data) || EqualsPredicates.isEqual(data, SeleniumDataConstants.NULL_ELEMENT) || isNull(data.OBJECT());
    }

    static boolean isNotNullWebElement(Data<WebElement> data) {
        return !isNullWebElement(data);
    }

    static Data<Boolean> isValidWebElement(Data<WebElement> data) {
        final var status = isNotNullWebElement(data);
        final var message = status ? ("Element is okay" + CoreFormatterConstants.END_LINE) : ("Element was null or false: " + data.MESSAGE());
        return DataFactoryFunctions.getBoolean(status, "isValidWebElement", message);
    }

    static Data<Boolean> isInitialized(Data<WebElement> data) {
        final var status = DataPredicates.isInitialized(data);
        final var message = "Element container was" + (status ? "" : "n't") + "initialized" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isInitialized", message);
    }
}
