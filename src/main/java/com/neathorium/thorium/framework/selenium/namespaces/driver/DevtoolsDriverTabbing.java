package com.neathorium.thorium.framework.selenium.namespaces.driver;

import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsDriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.driver.DevtoolsDriverUtilities;
import com.neathorium.thorium.core.namespaces.clipboard.ClipboardFunctions;

public interface DevtoolsDriverTabbing {
    private static DriverFunction<Boolean> copyCommand(String command) {
        return DriverFunctionFactory.getFunction((driver) -> ClipboardFunctions.copyToClipboard(command));
    }

    static DriverFunction<Boolean> inputTabAndCommand(String command) {
        return SeleniumExecutor.execute("inputTabAndCommand", copyCommand(command), DevtoolsDriverUtilities.sleepAndAction(DevtoolsDriverFunctionConstants.PASTE_FUNCTION));
    }
}
