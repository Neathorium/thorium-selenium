package com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable;

import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;

public interface Conditions {
    String NS_BASE = "TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_";

    static String getIsPresent(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "PRESENT", element);
    }

    static String getIsAbsent(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "ABSENT", element);
    }

    static String getIsDisplayed(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "DISPLAYED", element);
    }

    static String getIsHidden(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "HIDDEN", element);
    }

    static String getIsEnabled(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "ENABLED", element);
    }

    static String getIsDisabled(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "DISABLED", element);
    }

    static String getIsClickable(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "CLICKABLE", element);
    }

    static String getIsUnclickable(LazyElement element) {
        return MetaCoreFunctions.getBoolean(NS_BASE + "UNCLICKABLE", element);
    }
}
