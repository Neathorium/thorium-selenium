package com.neathorium.thorium.framework.selenium.element.enums;

import java.util.HashMap;
import java.util.Map;

public enum ElementStatuses {
    PRESENT("Present"),
    DISPLAYED("Displayed"),
    ENABLED("Enabled"),
    CLICKABLE("Clickable"),
    SELECTED("Selected"),
    ABSENT("Absent"),
    HIDDEN("Hidden"),
    DISABLED("Disabled"),
    UNCLICKABLE("Unclickable"),
    UNSELECTED("Unselected"),
    NONE("None");


    private static final Map<String, ElementStatuses> VALUES = new HashMap<>();
    private final String NAME;

    static {
        for(var value : values()) {
            VALUES.putIfAbsent(value.NAME, value);
        }
    }

    ElementStatuses(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }

    public static ElementStatuses getValueOf(String name) {
        return VALUES.getOrDefault(name, ElementStatuses.NONE);
    }
}
