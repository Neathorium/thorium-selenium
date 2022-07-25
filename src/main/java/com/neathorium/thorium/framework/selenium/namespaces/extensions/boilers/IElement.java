package com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.thorium.framework.core.interfaces.IContainedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface IElement extends IContainedData<WebDriver, WebElement> {
    DriverFunction<WebElement> get();
}
