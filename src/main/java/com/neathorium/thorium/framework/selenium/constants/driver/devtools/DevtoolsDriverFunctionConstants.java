package com.neathorium.thorium.framework.selenium.constants.driver.devtools;

import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.namespaces.systemidentity.BasicSystemIdentityFunctions;
import com.neathorium.thorium.core.platform.enums.PlatformKey;
import com.neathorium.thorium.core.platform.namespaces.PlatformFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.driver.DevtoolsDriverUtilities;
import org.openqa.selenium.Keys;

import java.util.Map;

public abstract class DevtoolsDriverFunctionConstants {
    public static final DriverFunction<Boolean> TAB = SeleniumExecutor.execute("inputTab", DevtoolsDriverUtilities.sleepAndAction(Element.sendKeys(DevtoolsViewConstants.BODY, DevtoolsViewConstants.TAB_INPUT)));

    public static final DriverFunction<Boolean> FIVE_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 5);
    public static final DriverFunction<Boolean> SIX_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 6);
    public static final DriverFunction<Boolean> ELEVEN_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 11);

    public static final DriverFunction<Boolean> TAB_AND_ENTER = SeleniumExecutor.execute("tabAndEnter", DevtoolsDriverUtilities.sleepAndAction(Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.ENTER))));
    public static final DriverFunction<Boolean> TAB_TO_BEFORE_CLEAR_BUTTON = SeleniumExecutor.execute("tabToClear", Element.clickWhenClickable(DevtoolsViewConstants.BODY), FIVE_TABS);
    public static final DriverFunction<Boolean> CLEAR_CONSOLE = SeleniumExecutor.execute("clearConsole", TAB_TO_BEFORE_CLEAR_BUTTON, TAB_AND_ENTER);
    public static final DriverFunction<Boolean> TAB_TO_CONSOLE_AFTER_CLEAR = SeleniumExecutor.execute("tabToConsoleAfterClear", SIX_TABS);
    public static final DriverFunction<Boolean> NO_RESULT_IN_CONSOLE = SeleniumExecutor.execute("noResultInConsole", Element.waitAbsent(DevtoolsViewConstants.RESULT_FIELD, 300, 3000));
    public static final DriverFunction<Boolean> CONSOLE_RESULT_DISPLAYED = SeleniumExecutor.execute("consoleResultDisplayed", Element.waitDisplayed(DevtoolsViewConstants.RESULT_FIELD, 300, 3000));
    public static final DriverFunction<String> GET_CONSOLE_RESULT = SeleniumExecutor.execute("getConsoleResult", DevtoolsDriverFunctionConstants.CONSOLE_RESULT_DISPLAYED, Element.getText(DevtoolsViewConstants.RESULT_FIELD));


    private static final DriverFunction<Boolean> WINDOWS_KEYBOARD_PASTE = Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.CONTROL, "v"), Keys.END, Keys.ENTER));
    private static final DriverFunction<Boolean> MAC_KEYBOARD_PASTE = Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.SHIFT, Keys.INSERT), Keys.END, Keys.ENTER));

    private static final Map<PlatformKey, DriverFunction<Boolean>> PASTE_FUNCTIONS = PlatformFunctions.getPlatformMap(WINDOWS_KEYBOARD_PASTE, MAC_KEYBOARD_PASTE, WINDOWS_KEYBOARD_PASTE);
    public static final DriverFunction<Boolean> PASTE_FUNCTION = DevtoolsDriverFunctionConstants.PASTE_FUNCTIONS.get(BasicSystemIdentityFunctions.getSystemTypeOrUnknown());
}
