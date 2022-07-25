package com.neathorium.thorium.framework.selenium.namespaces.driver.properties;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.Driver;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.BaseExecutionFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.java.extensions.classes.boilers.StringSet;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriverGuardData;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverPropertyFunctions {
    private static <T> Data<T> getPropertyCore(WebDriver driver, String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        final var result = function.apply(driver);
        final var status = guard.test(result);
        final var object = status ? result : defaultValue;
        final var message = (status ? " is: \"" + result + "\"" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.WAS_NULL);
        return DataFactoryFunctions.getWith(object, status, "getCore", property + message);
    }

    private static <T> DriverFunction<T> getPropertyCore(String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        return driver -> getPropertyCore(driver, property, guard, function, defaultValue);
    }

    private static <T> DriverFunction<T> getProperty(String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        var nameof = "getProperty";
        final var isPropertyNotBlank = isNotBlank(property);
        if (isPropertyNotBlank) {
            nameof += " - (\"" + property + "\")";
        }

        final var status = isPropertyNotBlank && NullablePredicates.areNotNull(guard, function, defaultValue);
        return ifDriver(nameof, status, getPropertyCore(property, guard, function, defaultValue), DataFactoryFunctions.getWith(defaultValue, false, SeleniumFormatterConstants.DRIVER_WAS_NULL));
    }

    private static DriverFunction<String> getString(String property, Function<WebDriver, String> function) {
        return getProperty(property, NullablePredicates::isNotNull, function, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<String> getTitle() {
        return getString("Title", WebDriver::getTitle);
    }

    static DriverFunction<String> getWindowHandle() {
        return getString("WindowHandle", WebDriver::getWindowHandle);
    }

    static DriverFunction<String> getUrl() {
        return getProperty("Url", StringUtils::isNotBlank, WebDriver::getCurrentUrl, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<StringSet> getWindowHandles() {
        final var getStringSetOfWindowHandles = BaseExecutionFunctions.conditionalChain(NullablePredicates::isNotNull, WebDriver::getWindowHandles, StringSet::new, SeleniumCoreConstants.NULL_STRING_SET);
        return getProperty("WindowHandles", NullablePredicates::isNotNull, getStringSetOfWindowHandles, SeleniumCoreConstants.NULL_STRING_SET);
    }

    static DriverFunction<Integer> getWindowHandleAmount() {
        return ExecutionCore.ifDriverGuardData("getWindowHandleAmount", getWindowHandles(), Driver::getWindowHandleAmountData, CoreDataConstants.NULL_INTEGER);
    }
}
