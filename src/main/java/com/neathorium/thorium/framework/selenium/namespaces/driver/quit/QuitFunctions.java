package com.neathorium.thorium.framework.selenium.namespaces.driver.quit;

import com.neathorium.thorium.core.namespaces.exception.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExceptionHandlers;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import org.openqa.selenium.WebDriver;

public interface QuitFunctions {
    private static Void quit(WebDriver driver) {
        driver.quit();
        return null;
    }

    private static Data<Boolean> quitDriverCore(WebDriver driver) {
        final var data = new HandleResultData<>(QuitFunctions::quit, driver, null);
        final var result = SeleniumExceptionHandlers.quitHandler(data);
        final var exception = result.exception;
        final var status = ExceptionFunctions.isNonException(exception);
        var message = result.message.message;
        if (CoreUtilities.isFalse(status)) {
            message += "Exception:" + exception.getClass() + " Message: " + exception.getMessage();
        }

        return DataFactoryFunctions.getBoolean(status, message, exception);
    }

    static DriverFunction<Boolean> quitDriver() {
        return ExecutionCore.ifDriver("quitDriver", QuitFunctions::quitDriverCore, SeleniumDataConstants.DRIVER_WAS_NULL);
    }
}
