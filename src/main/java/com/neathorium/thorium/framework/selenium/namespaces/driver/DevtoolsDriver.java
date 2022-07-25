package com.neathorium.thorium.framework.selenium.namespaces.driver;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable.Actions;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable.Conditions;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;

import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DevtoolsDriver {
    private static String invokeElementPresent(LazyElement element) {
        return Conditions.getIsPresent(element);
    }

    private static String invokeElementAbsent(LazyElement element) {
        return Conditions.getIsAbsent(element);
    }

    private static String invokeElementDisplayed(LazyElement element) {
        return Conditions.getIsDisplayed(element);
    }

    private static String invokeElementHidden(LazyElement element) {
        return Conditions.getIsHidden(element);
    }

    private static String invokeElementEnabled(LazyElement element) {
        return Conditions.getIsEnabled(element);
    }

    private static String invokeElementDisabled(LazyElement element) {
        return Conditions.getIsDisabled(element);
    }

    private static String invokeElementClickable(LazyElement element) {
        return Conditions.getIsClickable(element);
    }

    private static String invokeElementUnclickable(LazyElement element) {
        return Conditions.getIsUnclickable(element);
    }

    private static String invokeClick(LazyElement element) {
        return Actions.getClick(element);
    }

    private static String invokeSetValue(LazyElement element, String value) {
        return Actions.getSetValue(element, value);
    }

    private static String invokeGetValue(LazyElement element) {
        return Actions.getGetValue(element);
    }

    private static String invokeSetAttribute(LazyElement element, String attribute, String value) {
        return Actions.getSetAttribute(element, attribute, value);
    }

    private static String invokeGetAttribute(LazyElement element, String attribute) {
        return Actions.getGetAttribute(element, attribute);
    }

    private static String invokeGetCssValue(LazyElement element, String value) {
        return Actions.getGetCssValue(element, value);
    }

    private static String invokeGetText(LazyElement element) {
        return Actions.getGetText(element);
    }

    private static String invokeGetInnerText(LazyElement element) {
        return Actions.getGetInnerText(element);
    }

    static <T> DriverFunction<T> elementActionCore(String name, LazyElement element, Function<LazyElement, String> action, Function<String, DriverFunction<T>> handler, Data<T> defaultValue) {
        final var nameof = isNotBlank(name) ? name : "elementActionCore";
        return ExecutionCore.ifDriver(
            nameof,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            handler.apply(action.apply(element)),
            defaultValue
        );
    }

    static DriverFunction<Boolean> elementAction(String name, LazyElement element, Function<LazyElement, String> action) {
        return elementActionCore(name, element, action, DevtoolsDriverFunctions::doBooleanCommand, CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<String> elementStringAction(String name, LazyElement element, Function<LazyElement, String> action) {
        return elementActionCore(name, element, action, DevtoolsDriverFunctions::doCommand, CoreDataConstants.NULL_STRING);
    }

    static DriverFunction<Boolean> isElementPresent(LazyElement element) {
        return elementAction("isElementPresent", element, DevtoolsDriver::invokeElementPresent);
    }

    static DriverFunction<Boolean> isElementAbsent(LazyElement element) {
        return elementAction("isElementAbsent", element, DevtoolsDriver::invokeElementAbsent);
    }

    static DriverFunction<Boolean> isElementDisplayed(LazyElement element) {
        return elementAction("isElementDisplayed", element, DevtoolsDriver::invokeElementDisplayed);
    }

    static DriverFunction<Boolean> isElementHidden(LazyElement element) {
        return elementAction("isElementHidden", element, DevtoolsDriver::invokeElementHidden);
    }

    static DriverFunction<Boolean> isElementEnabled(LazyElement element) {
        return elementAction("isElementEnabled", element, DevtoolsDriver::invokeElementEnabled);
    }

    static DriverFunction<Boolean> isElementDisabled(LazyElement element) {
        return elementAction("isElementDisabled", element, DevtoolsDriver::invokeElementDisabled);
    }

    static DriverFunction<Boolean> isElementClickable(LazyElement element) {
        return elementAction("isElementClickable", element, DevtoolsDriver::invokeElementClickable);
    }

    static DriverFunction<Boolean> isElementUnclickable(LazyElement element) {
        return elementAction("isElementUnclickable", element, DevtoolsDriver::invokeElementUnclickable);
    }

    static DriverFunction<Boolean> click(LazyElement element) {
        return elementAction("click", element, DevtoolsDriver::invokeClick);
    }

    static DriverFunction<Boolean> setValue(LazyElement element, String value) {
        return ExecutionCore.ifDriver(
            "setValue",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isNullMessageWithName(value, "Value"),
            DevtoolsDriverFunctions.doBooleanCommand(invokeSetValue(element, value)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> setAttribute(LazyElement element, String attribute, String value) {
        return ExecutionCore.ifDriver(
            "setAttribute",
            (
                FrameworkCoreFormatter.isNullLazyElementMessage(element) +
                CoreFormatter.isBlankMessageWithName(attribute, "attribute") +
                CoreFormatter.isBlankMessageWithName(value, "Value")
            ),
            DevtoolsDriverFunctions.doBooleanCommand(invokeSetAttribute(element, attribute, value)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<String> getValue(LazyElement element) {
        return elementStringAction("getValue", element, DevtoolsDriver::invokeGetValue);
    }

    static DriverFunction<String> getText(LazyElement element) {
        return elementStringAction("getText", element, DevtoolsDriver::invokeGetText);
    }

    static DriverFunction<String> getInnerText(LazyElement element) {
        return elementStringAction("getInnerText", element, DevtoolsDriver::invokeGetInnerText);
    }

    static DriverFunction<String> getAttribute(LazyElement element, String attribute) {
        return ExecutionCore.ifDriver(
            "getAttribute",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isNullMessageWithName(attribute, "Attribute"),
            DevtoolsDriverFunctions.doCommand(invokeGetAttribute(element, attribute)),
            CoreDataConstants.NULL_STRING
        );
    }

    static DriverFunction<String> getCssValue(LazyElement element, String value) {
        return ExecutionCore.ifDriver(
            "getCssValue",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isNullMessageWithName(value, "CSS Value"),
            DevtoolsDriverFunctions.doCommand(invokeGetCssValue(element, value)),
            CoreDataConstants.NULL_STRING
        );
    }
}
