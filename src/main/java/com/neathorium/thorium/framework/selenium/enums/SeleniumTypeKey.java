package com.neathorium.thorium.framework.selenium.enums;

import java.util.HashMap;
import java.util.Map;

public enum SeleniumTypeKey {
    BOOLEAN("Boolean"),
    INTEGER("Integer"),
    STRING("String"),
    OBJECT("Object"),
    VOID("Void"),
    WEB_ELEMENT("WebElement"),
    WEB_ELEMENT_LIST("WebElementList"),
    STRING_SET("StringSet"),
    DEFAULT("Void");

    private static final Map<String, SeleniumTypeKey> VALUES = new HashMap<>();
    private final String name;

    SeleniumTypeKey(String name) {
        this.name = name;
    }

    static {
        for(var getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
        }
    }
    public String getName() {
        return name;
    }

    public static SeleniumTypeKey getValueOf(String name) {
        return VALUES.getOrDefault(name, SeleniumTypeKey.VOID);
    }
}
