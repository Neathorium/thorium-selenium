package com.neathorium.thorium.framework.selenium.constants.driver.devtools;

import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.Keys;

public abstract class DevtoolsViewConstants {
    public static final String NAME = "Devtools - ";

    public static final String BODY_NAME = NAME + "Body";
    public static final String RESULT_FIELD_NAME = NAME + "Console result field(zeroth)";
    public static final String CONSOLE_FOCUS_NAME = NAME + "Console focus";

    public static final String BODY_SELECTOR = "-blink-dev-tools";
    public static final String RESULT_FIELD_SELECTOR = ".console-message-text > span";
    public static final String CONSOLE_FOCUS_SELECTOR = "div.source-code.fill";

    public static final LazyElement BODY = LazyElementFactory.getSimple(BODY_NAME, SeleniumLazyLocatorFactory.getID(BODY_SELECTOR));
    public static final LazyElement RESULT_FIELD = LazyElementFactory.getSimple(RESULT_FIELD_NAME, 0, SeleniumLazyLocatorFactory.getCSSSelector(RESULT_FIELD_SELECTOR));
    public static final LazyElement CONSOLE_FOCUS = LazyElementFactory.getSimple(CONSOLE_FOCUS_NAME, SeleniumLazyLocatorFactory.getCSSSelector(CONSOLE_FOCUS_SELECTOR));

    public static final int TAB_SLEEP_MILLIS_LIMIT = 0;
    public static final int TAB_SLEEP_MILLIS = 2;

    public static final String TAB_INPUT = Keys.chord(Keys.TAB);
}
