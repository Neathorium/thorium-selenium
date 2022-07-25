package com.neathorium.thorium.framework.selenium.namespaces.driver;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsDriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.MutationObserver;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.driver.DevtoolsDriverUtilities;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;

public interface DevtoolsDriverFunctions {
    static DriverFunction<Boolean> clearConsole() {
        return SeleniumExecutor.execute(
            "clearConsole",
            Element.waitPresent(DevtoolsViewConstants.CONSOLE_FOCUS, 500, 10000),
            MutationObserver.setConsoleFocusedFunction(),
            DevtoolsDriverFunctionConstants.CLEAR_CONSOLE,
            DevtoolsDriverFunctionConstants.TAB_TO_CONSOLE_AFTER_CLEAR,
            MutationObserver.isConsoleFocused(),
            DevtoolsDriverFunctionConstants.NO_RESULT_IN_CONSOLE
        );
    }

    private static <T> DriverFunction<T> doCommandCore(String command, DriverFunction<T> resultFunction) {
        return SeleniumExecutor.execute(
            "doCommandCore",
            clearConsole(),
            DevtoolsDriverFunctionConstants.ELEVEN_TABS,
            DevtoolsDriverTabbing.inputTabAndCommand(command),
            resultFunction
        );
    }

    static DriverFunction<String> doCommand(String command) {
        return ExecutionCore.ifDriver("doCommand", CoreFormatter.isBlankMessageWithName(command, "Command"), doCommandCore(command, DevtoolsDriverFunctionConstants.GET_CONSOLE_RESULT), CoreDataConstants.NULL_STRING);
    }

    static DriverFunction<Boolean> doBooleanCommand(String command) {
        return ExecutionCore.ifDriver("doBooleanCommand", CoreFormatter.isBlankMessageWithName(command, "Command"), doCommandCore(command, DevtoolsDriverUtilities.getBooleanConsoleResult()), CoreDataConstants.NULL_BOOLEAN);
    }
}
