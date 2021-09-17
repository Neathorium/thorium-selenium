package com.neathorium.thorium.framework.selenium.enums;

import com.neathorium.thorium.framework.selenium.constants.SelectorStrategyNameConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;

import java.util.HashMap;
import java.util.Map;

public enum SeleniumSelectorStrategy {
    ID(SelectorStrategyNameConstants.ID),
    CSS_SELECTOR(SelectorStrategyNameConstants.CSS_SELECTOR),
    CLASS(SelectorStrategyNameConstants.CLASS),
    XPATH(SelectorStrategyNameConstants.XPATH),
    TAG_NAME(SelectorStrategyNameConstants.TAG_NAME),
    NAME(SelectorStrategyNameConstants.NAME),
    PARTIAL_LINK_TEXT(SelectorStrategyNameConstants.PARTIAL_LINK_TEXT),
    LINK_TEXT(SelectorStrategyNameConstants.LINK_TEXT),
    NONE(SeleniumFormatterConstants.NONE),
    DEFAULT(SelectorStrategyNameConstants.ID);

    private static final Map<String, SeleniumSelectorStrategy> VALUES = new HashMap<>();
    private static final Map<Integer, SeleniumSelectorStrategy> INDICES = new HashMap<>();
    private String name;

    SeleniumSelectorStrategy(String name) {
        this.name = name;
    }

    static {
        for(var getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
            INDICES.putIfAbsent(getter.ordinal(), getter);
        }
    }
    public String getName() {
        return name;
    }

    public static SeleniumSelectorStrategy getValueOf(String name) {
        return VALUES.getOrDefault(name, SeleniumSelectorStrategy.DEFAULT);
    }

    public static boolean contains(String name) {
        return VALUES.containsKey(name);
    }
}
