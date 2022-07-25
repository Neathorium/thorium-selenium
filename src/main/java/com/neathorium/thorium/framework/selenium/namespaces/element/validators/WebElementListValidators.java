package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.java.extensions.namespaces.predicates.CollectionPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebElement;

public interface WebElementListValidators {
    static boolean isInvalid(WebElementList list) {
        return (
            NullablePredicates.isNull(list) ||
            CollectionPredicates.isEmptyOrNotOfType(list, WebElement.class) ||
            EqualsPredicates.isEqual(SeleniumCoreConstants.NULL_ELEMENT_LIST, list) ||
            EqualsPredicates.isEqual(SeleniumCoreConstants.STOCK_ELEMENT, list.first())
        );
    }

    static boolean isValid(WebElementList list) {
        return !isInvalid(list);
    }

    static boolean isInvalid(Data<WebElementList> data) {
        return (
            DataPredicates.isInvalidOrFalse(data) ||
            EqualsPredicates.isEqual(SeleniumDataConstants.NULL_LIST, data) ||
            isInvalid(data.OBJECT())
        );
    }

    static boolean isValid(Data<WebElementList> data) {
        return !isInvalid(data);
    }


}
