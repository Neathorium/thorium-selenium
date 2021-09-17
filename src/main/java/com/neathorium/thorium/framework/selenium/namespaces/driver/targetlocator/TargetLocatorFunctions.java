package com.neathorium.thorium.framework.selenium.namespaces.driver.targetlocator;

import com.neathorium.thorium.framework.selenium.constants.FactoryConstants;
import com.neathorium.thorium.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.WebDriver;

public interface TargetLocatorFunctions {
    private static WebDriver.TargetLocator getTargetLocator(WebDriver driver) {
        return driver.switchTo();
    }

    static DriverFunction<WebDriver.TargetLocator> getTargetLocator() {
        return DriverTypeConversionFunctions.getSubtypeOfDriver(TargetLocatorFunctions::getTargetLocator, FactoryConstants.NULL_TARGET_LOCATOR);
    }
}
