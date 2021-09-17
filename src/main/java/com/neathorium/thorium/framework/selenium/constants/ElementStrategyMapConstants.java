package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.enums.SeleniumSelectorStrategy;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class ElementStrategyMapConstants {
    public static final Map<SeleniumSelectorStrategy, Function<String, By>> STRATEGY_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SeleniumSelectorStrategy.ID, By::id),
                entry(SeleniumSelectorStrategy.CSS_SELECTOR, By::cssSelector),
                entry(SeleniumSelectorStrategy.CLASS, By::className),
                entry(SeleniumSelectorStrategy.XPATH, By::xpath),
                entry(SeleniumSelectorStrategy.TAG_NAME, By::tagName),
                entry(SeleniumSelectorStrategy.NAME, By::name),
                entry(SeleniumSelectorStrategy.PARTIAL_LINK_TEXT, By::partialLinkText),
                entry(SeleniumSelectorStrategy.LINK_TEXT, By::linkText)
            )
        )
    );

    public static final Set<String> STRATEGY_MAP_KEY_SET = new HashSet<>(Arrays.asList(
        SeleniumSelectorStrategy.ID.getName(),
        SeleniumSelectorStrategy.CSS_SELECTOR.getName(),
        SeleniumSelectorStrategy.CLASS.getName(),
        SeleniumSelectorStrategy.XPATH.getName(),
        SeleniumSelectorStrategy.TAG_NAME.getName(),
        SeleniumSelectorStrategy.NAME.getName(),
        SeleniumSelectorStrategy.PARTIAL_LINK_TEXT.getName(),
        SeleniumSelectorStrategy.LINK_TEXT.getName(),
        "nested"
    ));
}
