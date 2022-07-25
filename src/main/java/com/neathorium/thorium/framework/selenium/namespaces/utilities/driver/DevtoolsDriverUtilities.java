package com.neathorium.thorium.framework.selenium.namespaces.utilities.driver;

import com.neathorium.thorium.core.data.namespaces.DataFunctions;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.wait.namespaces.WaitFunctions;
import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsDriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.WebDriver;

public interface DevtoolsDriverUtilities {
    private static Data<Boolean> sleepAndAction(WebDriver driver, DriverFunction<Boolean> action) {
        WaitFunctions.sleep(DevtoolsViewConstants.TAB_SLEEP_MILLIS);
        return action.apply(driver);
    }

    static DriverFunction<Boolean> sleepAndAction(DriverFunction<Boolean> action) {
        return driver -> sleepAndAction(driver, action);
    }

    static Data<Boolean> getBooleanConsoleResultCore(WebDriver driver) {
        final var result = DevtoolsDriverFunctionConstants.GET_CONSOLE_RESULT.apply(driver);
        final var status = BooleanUtils.toBoolean(result.OBJECT());
        return DataFactoryFunctions.getBoolean(status, "getBooleanConsoleResultCore", DataFunctions.getFormattedMessage(result), result.EXCEPTION(), result.EXCEPTION_MESSAGE());
    }

    static DriverFunction<Boolean> getBooleanConsoleResult() {
        return DevtoolsDriverUtilities::getBooleanConsoleResultCore;
    }
}
