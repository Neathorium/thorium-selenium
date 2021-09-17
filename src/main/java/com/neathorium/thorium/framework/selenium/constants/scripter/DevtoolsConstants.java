package com.neathorium.thorium.framework.selenium.constants.scripter;

public abstract class DevtoolsConstants {
    public static final String GUARD = "return ((typeof document['__test']) === (typeof {}))";
    public static final String CONSOLE_FOCUSED_CHECK = "return document['__test']['consoleFocused'];";
}
