package com.neathorium.thorium.framework.selenium.namespaces.driver.quit;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExceptionHandlers;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import org.openqa.selenium.WebDriver;

public interface QuitFunctions {
    private static Void quit(WebDriver driver) {
        driver.quit();
        return null;
    }

    private static Data<Boolean> quitDriverCore(WebDriver driver) {
        final var data = new HandleResultData<>(QuitFunctions::quit, driver, null);
        final var result = SeleniumExceptionHandlers.quitHandler(data);
        final var exception = result.EXCEPTION();
        final var status = ExceptionFunctions.isNonException(exception);
        var message = result.MESSAGE().MESSAGE();
        if (BooleanUtilities.isFalse(status)) {
            message += "Exception:" + exception.getClass() + " Message: " + exception.getMessage();
        }

        return DataFactoryFunctions.getBoolean(status, "quitDriverCore", message, exception);
    }

    static DriverFunction<Boolean> quitDriver() {
        return ExecutionCore.ifDriver("quitDriver", QuitFunctions::quitDriverCore, SeleniumDataConstants.DRIVER_WAS_NULL);
    }
}
