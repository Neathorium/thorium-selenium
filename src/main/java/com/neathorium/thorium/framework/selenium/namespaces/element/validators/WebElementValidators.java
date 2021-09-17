package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebElement;

public interface WebElementValidators {
    static boolean isNull(WebElement element) {
        return (
            NullableFunctions.isNull(element) ||
                CoreUtilities.isEqual(element, SeleniumDataConstants.NULL_ELEMENT.object) ||
                CoreUtilities.isEqual(element.getAttribute("id"), SeleniumFormatterConstants.NULL_ELEMENT_ID)
        );
    }

    static boolean isNotNull(WebElement element) {
        return !isNull(element);
    }

    static boolean isNullWebElement(Data<WebElement> data) {
        return DataPredicates.isInvalidOrFalse(data) || CoreUtilities.isEqual(data, SeleniumDataConstants.NULL_ELEMENT) || isNull(data.object);
    }

    static boolean isNotNullWebElement(Data<WebElement> data) {
        return !isNullWebElement(data);
    }

    static Data<Boolean> isValidWebElement(Data<WebElement> data) {
        final var status = isNotNullWebElement(data);
        final var message = status ? ("Element is okay" + CoreFormatterConstants.END_LINE) : ("Element was null or false: " + data.message);
        return DataFactoryFunctions.getBoolean(status, "isValidWebElement", message);
    }

    static Data<Boolean> isInitialized(Data<WebElement> data) {
        final var status = DataPredicates.isInitialized(data);
        final var message = "Element container was" + (status ? "" : "n't") + "initialized" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isInitialized", message);
    }
}
