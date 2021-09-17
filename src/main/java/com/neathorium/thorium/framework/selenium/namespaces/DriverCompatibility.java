package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverCompatibility {
    static DriverFunction<WebElement> getElementByCssSelector(String selector, String nameof) {
        return ExecutionCore.ifDriver(isNotBlank(nameof) ? nameof : "getElementByCssSelector", isNotBlank(selector), Driver.getElement(By.cssSelector(selector)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElementByCssSelector(String selector) {
        return getElementByCssSelector(selector, null);
    }

    static DriverFunction<WebElementList> getElementsByCssSelector(String selector) {
        return ExecutionCore.ifDriver("getElementsByCssSelector", isNotBlank(selector), Driver.getElements(By.cssSelector(selector)), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElement> getElementById(String id) {
        return ExecutionCore.ifDriver("getElementById", isNotBlank(id), Driver.getElement(By.id(id)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElementList> getElementsByClass(String className) {
        return ExecutionCore.ifDriver("getElementsByClass", isNotBlank(className), Driver.getElements(By.className(className)), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElement> getElementByClass(String className) {
        return ExecutionCore.ifDriver("getElementByClass", isNotBlank(className), Driver.getElement(By.className(className)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElementByXpath(String xpath) {
        return ExecutionCore.ifDriver("getElementByXpath", isNotBlank(xpath), Driver.getElement(By.xpath(xpath)), SeleniumDataConstants.NULL_ELEMENT);
    }
}
