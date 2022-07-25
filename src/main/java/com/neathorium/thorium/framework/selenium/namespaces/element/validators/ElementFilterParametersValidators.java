package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EmptiablePredicates;
import org.openqa.selenium.WebDriver;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ElementFilterParametersValidators {
    static String isNullLazyLocatorListMessage(LazyLocatorList locators) {
        var message = CoreFormatter.isNullMessageWithName(locators, "Locators");
        if (isBlank(message) && EmptiablePredicates.isEmpty(locators)) {
            message += "List was empty" + CoreFormatterConstants.END_LINE;
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isNullLazyLocatorListMessage: ", message);
    }

    private static <T> String isInvalidElementFilterParametersMessageCore(BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data) {
        return isNullLazyLocatorListMessage(data.LOCATORS) + SeleniumFormatter.getManyGetterErrorMessage(data.GETTER_MAP, data.GETTER);
    }

    static <T> String isInvalidElementFilterParametersMessage(BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Element Filter Parameters data");
        if (isBlank(message)) {
            message += isInvalidElementFilterParametersMessageCore(data);
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isInvalidElementIndexFilterParametersMessage: ", message);
    }
}
