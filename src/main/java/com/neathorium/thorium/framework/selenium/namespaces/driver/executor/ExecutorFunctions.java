package com.neathorium.thorium.framework.selenium.namespaces.driver.executor;

import com.neathorium.thorium.framework.selenium.constants.FactoryConstants;
import com.neathorium.thorium.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public interface ExecutorFunctions {
    private static JavascriptExecutor getExecutor(WebDriver driver) {
        return (JavascriptExecutor)driver;
    }

    static DriverFunction<JavascriptExecutor> getExecutor() {
        return DriverTypeConversionFunctions.getSubtypeOfDriver(ExecutorFunctions::getExecutor, FactoryConstants.NULL_JAVASCRIPT_EXECUTOR);
    }
}
