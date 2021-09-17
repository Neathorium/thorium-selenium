package com.neathorium.thorium.framework.selenium.constants.scripts.element;

public abstract class Present {
    /*public static final String PRESENT_CORE = (
        "var isPresent = function isPresentFunction(element) {\n" +
        "    var isElement = element instanceof Element;\n" +
        "    var displayNotNone = getComputedStyle(element).display !== 'none';\n" +
        "    var visibilityNotHidden = getComputedStyle(element).visibility !== 'hidden';\n" +
        "    var clientHeightNotZero = element.clientHeight !== 0;\n" +
        "    var rectsLengthNotZero = element.getClientRects().length !== 0;\n" +
        "\n" +
        "    return isElement && displayNotNone && visibilityNotHidden && clientHeightNotZero && rectsLengthNotZero;\n" +
        "}\n"
    );

    public static final String IS_DISPLAYED_DISPATCHER = (
        IF_RETURN("arguments.length < 1", "false") + DISPLAYED_CORE + RETURN("isDisplayed(arguments[0])")
    );

    public static final String IS_DISPLAYED_CONSOLE_DISPATCHER = (
        IF_RETURN("!element instanceof Element", "false") + DISPLAYED_CORE + RETURN("isDisplayed(element)")
    );*/
}
