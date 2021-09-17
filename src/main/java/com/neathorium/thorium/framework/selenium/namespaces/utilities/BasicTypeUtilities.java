package com.neathorium.thorium.framework.selenium.namespaces.utilities;

import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public interface BasicTypeUtilities {
    static WebElement getStockElement() {
        final var element = new RemoteWebElement();
        element.setId(SeleniumFormatterConstants.NULL_ELEMENT_ID);
        return element;
    }
}
