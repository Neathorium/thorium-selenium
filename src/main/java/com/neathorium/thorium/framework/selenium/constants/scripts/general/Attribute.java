package com.neathorium.thorium.framework.selenium.constants.scripts.general;

import com.neathorium.thorium.framework.selenium.namespaces.scripter.General;

import static com.neathorium.thorium.framework.selenium.namespaces.scripter.General.IF_RETURN;

public abstract class Attribute {
    public static final String SET_ATTRIBUTE = (
        General.IF_RETURN("arguments.length < 3", "null") +
        "arguments[0].setAttribute(arguments[1], arguments[2]);" +
        General.RETURN("arguments[0].getAttribute(arguments[1])")
    );
}
