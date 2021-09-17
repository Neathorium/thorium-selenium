package com.neathorium.thorium.framework.selenium.constants.scripts.element;

import com.neathorium.thorium.framework.selenium.namespaces.scripter.General;

import static com.neathorium.thorium.framework.selenium.namespaces.scripter.General.IF_RETURN;

public abstract class Displayed {
    public static final String DISPLAYED_FUNCTION_NAME = "isDisplayed";

    public static final String IS_ELEMENT_DISPLAYED = DISPLAYED_FUNCTION_NAME + "(element)";

    public static final String DISPLAYED_SET = "return (typeof " + DISPLAYED_FUNCTION_NAME + " === typeof(Function));";

    public static final String DISPLAYED_CORE = (
        "var " + DISPLAYED_FUNCTION_NAME + " = function isDisplayedFunction(element) {" +
        "    var isElement = element instanceof Element;" +
        "    if (!isElement) {" +
        "        return false;" +
        "    }" +
        "    var computedStyles = getComputedStyle(element);" +
        "    var displayNotNone = computedStyles.display !== 'none';" +
        "    var visibilityNotHidden = computedStyles.visibility !== 'hidden';" +
        "    var clientHeightNotZero = element.clientHeight !== 0;" +
        "    var rectsLengthNotZero = element.getClientRects().length !== 0;" +
        "" +
        "    return (isElement && displayNotNone && visibilityNotHidden && clientHeightNotZero && rectsLengthNotZero);" +
        "}"
    );

    public static final String IS_DISPLAYED_DISPATCHER = (
        General.IF_RETURN("arguments.length < 1", "false") + DISPLAYED_CORE + General.RETURN(DISPLAYED_FUNCTION_NAME + "(arguments[0])")
    );

    public static final String IS_DISPLAYED_CONSOLE_DISPATCHER = (
        General.IF_RETURN("!element instanceof Element", "false") + DISPLAYED_CORE + General.RETURN(IS_ELEMENT_DISPLAYED)
    );
}
