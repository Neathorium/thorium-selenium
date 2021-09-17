package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.extensions.namespaces.predicates.CollectionPredicates;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebElement;

public interface WebElementListValidators {
    static boolean isInvalid(WebElementList list) {
        return (
            NullableFunctions.isNull(list) ||
            CollectionPredicates.isEmptyOrNotOfType(list, WebElement.class) ||
            CoreUtilities.isEqual(SeleniumCoreConstants.NULL_ELEMENT_LIST, list) ||
            CoreUtilities.isEqual(SeleniumCoreConstants.STOCK_ELEMENT, list.first())
        );
    }

    static boolean isValid(WebElementList list) {
        return !isInvalid(list);
    }

    static boolean isInvalid(Data<WebElementList> data) {
        return (
            DataPredicates.isInvalidOrFalse(data) ||
            CoreUtilities.isEqual(SeleniumDataConstants.NULL_LIST, data) ||
            isInvalid(data.object)
        );
    }

    static boolean isValid(Data<WebElementList> data) {
        return !isInvalid(data);
    }


}
