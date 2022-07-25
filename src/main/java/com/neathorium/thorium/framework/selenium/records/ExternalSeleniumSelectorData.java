package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.records.command.CommandRangeData;
import com.neathorium.thorium.framework.core.records.lazy.ExternalSelectorData;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExternalSeleniumSelectorData extends ExternalSelectorData<WebDriver> {
    public ExternalSeleniumSelectorData(BiFunction<String, List<String>, Function<WebDriver, Data<String>>> getSelector, String preferredProperties, String selectorType, CommandRangeData range, int limit, Data<String> defaultSelector) {
        super(getSelector, preferredProperties, selectorType, range, limit, defaultSelector);
    }
}
