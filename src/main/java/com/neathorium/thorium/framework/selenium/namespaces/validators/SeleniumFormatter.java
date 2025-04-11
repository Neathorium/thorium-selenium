package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.abstracts.regular.AbstractElementValueParameters;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.records.CacheElementDefaultsData;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.SwitchResultMessageData;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementParameterizedValueParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeCommonMessageParametersData;
import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeParameterizedMessageData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities.getLocator;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumFormatter {
    static String getInvokeMethodCoreMessage(Exception exception, String message, String returnType, String parameterTypes) {
        final var endLine = CoreFormatterConstants.END_LINE;
        return ExceptionFunctions.isException(exception) ? (
            String.join(
                endLine,
                message,
                "An Exception(" + exception.getClass() + ") has occurred",
                "Exception Message:\n" + exception.getMessage(),
                "Cause: " + exception.getCause(),
                "Method parameter types: " + parameterTypes,
                "Result is of type " + returnType
            ) + endLine
        ) : CoreFormatterConstants.EMPTY;
    }

    static String getInvokeMethodCommonMessage(InvokeCommonMessageParametersData data, Exception exception) {
        return (NullablePredicates.areNotNull(data, exception) && StringUtilities.areNotBlank(data.MESSAGE, data.PARAMETER_TYPES, data.RETURN_TYPE)) ? (
            getInvokeMethodCoreMessage(exception, data.MESSAGE, data.PARAMETER_TYPES, data.RETURN_TYPE)
        ) : "Data parameter" + CoreFormatterConstants.WAS_NULL;
    }

    static String getInvokeMethodParameterizedMessage(InvokeParameterizedMessageData data, Exception exception) {
        if (NullablePredicates.areAnyNull(data, exception) || StringUtilities.areAnyBlank(data.MESSAGE, data.PARAMETER_TYPES, data.RETURN_TYPE)) {
            return "Data parameter" + CoreFormatterConstants.WAS_NULL;
        }

        final var parameter = data.parameter;
        final var parameterMessage = (isNotBlank(parameter) ? "Parameter was specified: " + parameter : "Parameter wasn't specified") + CoreFormatterConstants.END_LINE;
        final var invokeMessage = getInvokeMethodCoreMessage(exception, data.MESSAGE, data.PARAMETER_TYPES, data.RETURN_TYPE);
        return isNotBlank(invokeMessage) ? invokeMessage + parameterMessage : CoreFormatterConstants.EMPTY;
    }

    static Function<Exception, String> getInvokeMethodCommonMessageFunction(InvokeCommonMessageParametersData data) {
        return exception -> NullablePredicates.areAnyNull(data, exception) ? getInvokeMethodCommonMessage(data, exception) : CoreFormatterConstants.PARAMETER_ISSUES;
    }

    static Function<Exception, String> getInvokeMethodParameterizedMessageFunction(InvokeParameterizedMessageData data) {
        return exception -> NullablePredicates.areAnyNull(data, exception) ? getInvokeMethodParameterizedMessage(data, exception) : "Data or exception" + CoreFormatterConstants.WAS_NULL;
    }

    static Data<String> getIsValuesMessage(Map<String, String> map, Data<String> object, String expected, Boolean keyCondition, String descriptor, String conditionDescriptor) {
        final var valuesMessage = "Values ";
        final var nameof = "getIsValuesMessage";
        final var errorMessage = (
            isBlankMessageWithName(conditionDescriptor, valuesMessage + "conditionDescriptor") +
            CoreFormatter.isNullMessageWithName(descriptor, valuesMessage + "descriptor") +
            CoreFormatter.isNullMessageWithName(keyCondition, valuesMessage + "boolean") +
            CoreFormatter.isNullMessageWithName(map, valuesMessage + " map") +
            CoreFormatter.isNullMessageWithName(expected, valuesMessage + "expected value") +
            CoreFormatter.isInvalidOrFalseMessageWithName(object, valuesMessage + "object")
        );

        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(CoreFormatterConstants.EMPTY, nameof, conditionDescriptor + CoreFormatterConstants.PARAMETER_ISSUES + CoreFormatterConstants.DEFAULT_ERROR_MESSAGE_STRING + errorMessage);
        }

        final var key = String.valueOf(keyCondition);
        final var localObject = map.getOrDefault(key, CoreFormatterConstants.EMPTY);
        final var status = isNotBlank(localObject);
        final var message = (descriptor + "(" + object + ") " + localObject + conditionDescriptor + " expected (\"" + expected + "\")" + CoreFormatterConstants.END_LINE);
        return DataFactoryFunctions.getWith(message, status, nameof, message);
    }

    static String isValidElementFormatData(ElementFormatData<?> parameters) {
        final var baseName = "Element Function Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.FORMATTER(), baseName + " Formatter") +
                isBlankMessageWithName(parameters.CONDITION_NAME(), baseName + " Name") +
                isBlankMessageWithName(parameters.DESCRIPTOR(), baseName + " Descriptor")
            );
        }

        return getNamedErrorMessageOrEmpty("isValidElementFormatData", message);
    }

    static String isValidElementValueParametersMessage(AbstractElementValueParameters<?, ?> parameters) {
        final var baseName = "Element Value Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.function, baseName + " Function") +
                CoreFormatter.isNullMessageWithName(parameters.handler, baseName + " Handler") +
                isValidElementFormatData(parameters.formatData)
            );
        }

        return getNamedErrorMessageOrEmpty("isValidElementValueParametersMessage", message);
    }

    static String isValidElementParameterizedValueParametersMessage(ElementParameterizedValueParameters<?> parameters) {
        final var baseName = "Element Parameterized Value Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.function, baseName + " Function") +
                CoreFormatter.isNullMessageWithName(parameters.handler, baseName + " Handler") +
                isValidElementFormatData(parameters.formatData)
            );
        }

        return getNamedErrorMessageOrEmpty("isValidElementParameterizedValueParametersMessage", message);
    }

    static String getScrollIntoViewMessage(String message, boolean status) {
        return CoreFormatter.getExecuteFragment(status) + " Scroll into view: " + message + CoreFormatterConstants.END_LINE;
    }

    static String getScriptExecutionMessage(boolean status) {
        return CoreFormatter.getExecuteFragment(status) + " script" + CoreFormatterConstants.END_LINE;
    }

    static String getLocatorErrorMessage(By locator) {
        return CoreFormatter.isNullMessageWithName(locator, "By locator");
    }

    static String getNestedElementsErrorMessage(By locator, Data<SearchContext> context) {
        return getLocatorErrorMessage(locator) + CoreFormatter.isInvalidOrFalseMessageWithName(context, "Search Context");
    }

    static String getActionMessage(String message, String dataMessage, boolean status) {
        return CoreFormatterConstants.ELEMENT + CoreFormatter.getOptionMessage(status) + " " + message + CoreFormatterConstants.END_LINE + dataMessage + CoreFormatterConstants.END_LINE;
    }

    static String getLocatorAndGetterErrorMessage(By locator, SingleGetter getter) {
        return getLocatorErrorMessage(locator) + CoreFormatter.isNullMessageWithName(getter, "Getter");
    }

    static String getSendKeysErrorMessage(String message) {
        var lMessage = message;
        if (isNotBlank(message)) {
            lMessage += "Not sending" + CoreFormatterConstants.END_LINE;
        }
        return getNamedErrorMessageOrEmpty("getSendKeysErrorMessage", lMessage);
    }

    static String getSendKeysNotSendingMessage(By locator, String input, SingleGetter getter) {
        return getSendKeysErrorMessage(getLocatorAndGetterErrorMessage(locator, getter) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getSendKeysNotSendingMessage(By locator, String input) {
        return getSendKeysErrorMessage(getLocatorErrorMessage(locator) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getSendKeysNotSendingMessage(LazyElement data, String input) {
        return getSendKeysErrorMessage(FrameworkCoreFormatter.isNullLazyElementMessage(data) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getSendKeysNotSendingMessagePermitBlank(LazyElement data, String input) {
        return getSendKeysErrorMessage(FrameworkCoreFormatter.isNullLazyElementMessage(data) + CoreFormatter.isNullMessageWithName(input, "Input"));
    }

    static String getShadowRootElementMessage(String message, boolean status) {
        return message + " Root element " + CoreFormatter.getOptionMessage(status) + "found" + CoreFormatterConstants.END_LINE;
    }

    static String getElementsAmountMessage(By locator, boolean status, int expectedSize, int size) {
        return (status ? expectedSize : (size > 0 ? "Wrong(" + expectedSize + ") amount of" : "No")) + " elements found by: " + locator.toString() + CoreFormatterConstants.END_LINE;
    }

    static String getFindElementsMessage(String locator, int size) {
        return (size > 0 ? size + " amount of" : "No") + " elements found by: " + locator + CoreFormatterConstants.END_LINE;
    }

    static String getNumberOfWindowsEqualToMessage(boolean status, int expected, int count) {
        return "Number of browser windows are " + CoreFormatter.getOptionMessage(status)+ " equal. Actual: " + count + ", Expected: " + expected + CoreFormatterConstants.END_LINE;
    }

    static <T> String getSwitchToMessage(boolean status, SwitchResultMessageData<T> data) {
        final var target = data.target;
        var message = data.nameof + (status ? SeleniumFormatterConstants.SUCCESSFULLY_SWITCHED_TO : SeleniumFormatterConstants.COULDNT_SWITCH_TO) + data.type;

        if (!Objects.isNull(target)) {
            message += "(\"" + target + "\")";
        }

        return message + CoreFormatterConstants.END_LINE;
    }

    static String isElementFunctionMessage(LazyElement element, AbstractElementValueParameters<?, ?> parameters) {
        final var message = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isValidElementValueParametersMessage(parameters);
        return getNamedErrorMessageOrEmpty("isElementFunctionMessage", message);
    }

    static String isElementFunctionMessage(LazyElement element, ElementParameterizedValueParameters<?> parameters) {
        final var message = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isValidElementParameterizedValueParametersMessage(parameters);
        return getNamedErrorMessageOrEmpty("isElementFunctionMessage", message);
    }

    static <T> String getManyGetterErrorMessage(Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<T>>>> getterMap, ManyGetter key) {
        final var nameof = "getManyGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String getSingleGetterErrorMessage(Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<T>>>> getterMap, SingleGetter key) {
        final var nameof = "getSingleGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static String isNullLazyDataMessage(LazyLocator locator) {
        final var parameterName = "Lazy locator";
        var message = CoreFormatter.isNullMessageWithName(locator, parameterName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(locator.LOCATOR, parameterName + " value") +
                CoreFormatter.isNullMessageWithName(locator.STRATEGY, parameterName + " strategy")
            );
        }

        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(getLocator(locator), "Actual locator from locator");
        }

        return isNotBlank(message) ? "isNotNullLazyDataMessage: " + message : CoreFormatterConstants.EMPTY;
    }

    static String getElementAttributeMessage(Data<LazyElement> data, String value, String parameterName) {
        final var name = isBlank(parameterName) ? "Value" : parameterName;
        var message = CoreFormatter.isInvalidOrFalseMessageWithName(data, "Element data");
        if (isBlank(message)) {
            message += (
                FrameworkCoreFormatter.isNullLazyElementMessage(data.OBJECT()) +
                isBlankMessageWithName(value, name)
            );
        }

        return getNamedErrorMessageOrEmpty("getElementAttributeMessage", message);
    }

    static String isNullWebElementMessage(WebElement element) {
        var message = CoreFormatter.isNullMessageWithName(element, "Element");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isEqualMessage(SeleniumCoreConstants.STOCK_ELEMENT, "Null Selenium Element", element, "Element Parameter") +
                CoreFormatter.isEqualMessage(element.getAttribute("id"), "Element ID", SeleniumFormatterConstants.NULL_ELEMENT_ID, "Null Element ID")
            );
        }

        return getNamedErrorMessageOrEmpty("isNullWebElementMessage", message);
    }

    static String isNullWebElementDataMessage(Data<WebElement> element) {
        var message = CoreFormatter.isInvalidOrFalseMessage(element);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isEqualMessage(SeleniumDataConstants.NULL_ELEMENT, "Null Selenium Element Data", element, "Element Data Parameter") +
                isNullWebElementMessage(element.OBJECT())
            );
        }

        return getNamedErrorMessageOrEmpty("isNullWebElementMessage", message);
    }

    static String getElementsParametersMessage(LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        return getNamedErrorMessageOrEmpty("getElementsParametersMessage", CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List") + CoreFormatter.isNullMessageWithName(getter, "Getter"));
    }

    static String getElementsParametersMessage(LazyLocatorList locators) {
        return getNamedErrorMessageOrEmpty("getElementsParametersMessage", CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List"));
    }

    static String getRepositoryNullMessage(Map<String, CachedLazyElementData> repository) {
        return getNamedErrorMessageOrEmpty("getRepositoryNullMessage", CoreFormatter.isNullMessageWithName(repository, "Element repository"));
    }

    static String getCacheElementDefaultsDataInvalidMessage(CacheElementDefaultsData defaults) {
        var message = CoreFormatter.isNullMessageWithName(defaults, "Defaults data");
        if (isBlank(message)) {
            message += (
                isBlankMessageWithName(defaults.NAME(), "Name of the function") +
                getRepositoryNullMessage(defaults.REPOSITORY()) +
                CoreFormatter.isNullMessageWithName(defaults.DEFAULT_VALUE(), "Default data value")
            );
        }

        return getNamedErrorMessageOrEmpty("getCacheElementDefaultsDataInvalidMessage", message);
    }

    static String getCachedLazyElementDataInvalidMessage(CachedLazyElementData data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Lazy Element Cache data");
        if (isBlank(message)) {
            message += FrameworkCoreFormatter.isNullLazyElementMessage(data.element) + CoreFormatter.isNullMessageWithName(data.typeKeys, "Type keys");
        }

        return getNamedErrorMessageOrEmpty("getCachedLazyElementDataInvalidMessage", message);
    }

    static String getCacheElementParametersErrorMessage(CacheElementDefaultsData defaults, CachedLazyElementData data) {
        var message = getCacheElementDefaultsDataInvalidMessage(defaults) + getCachedLazyElementDataInvalidMessage(data);
        if (isBlank(message)) {
            message += getAlreadyCachedMessage(defaults.REPOSITORY(), data.element.NAME);
        }

        return getNamedErrorMessageOrEmpty("getCacheElementParametersErrorMessage", message);
    }

    private static String getCachedCommonMessage(Map<String, CachedLazyElementData> repository, String name) {
        return getNamedErrorMessageOrEmpty("getCachedCommonMessage", getRepositoryNullMessage(repository) + isBlankMessageWithName(name, "Name of item"));
    }

    static String getAlreadyCachedMessage(Map<String, CachedLazyElementData> repository, String name) {
        var message = SeleniumFormatter.getCachedCommonMessage(repository, name);
        if (isBlank(message) && BooleanUtilities.isTrue(repository.containsKey(name))) {
            message += SeleniumFormatterConstants.LAZY_ELEMENT + " with name(\"" + name + "\") was already cached" + CoreFormatterConstants.END_LINE;
        }

        return getNamedErrorMessageOrEmpty("getAlreadyCachedMessage", message);
    }

    static String getNotCachedMessage(Map<String, CachedLazyElementData> repository, String name) {
        var message = SeleniumFormatter.getCachedCommonMessage(repository, name);
        if (isBlank(message) && BooleanUtilities.isFalse(repository.containsKey(name))) {
            message += SeleniumFormatterConstants.LAZY_ELEMENT + " with name(\"" + name + "\") wasn't cached" + CoreFormatterConstants.END_LINE;
        }

        return getNamedErrorMessageOrEmpty("getNotCachedMessage", message);
    }

    static String getElementFoundInCacheMessage(boolean status, String name) {
        return SeleniumFormatterConstants.LAZY_ELEMENT + CoreFormatter.getOptionMessage(status) + " found by name(\"" + name + "\")" + CoreFormatterConstants.END_LINE;
    }

    static String getCacheElementValidParametersMessage(AbstractLazyResult<LazyFilteredElementParameters> element, Data<ExternalElementData> regular, Data<ExternalElementData> external) {
        return  getNamedErrorMessageOrEmpty("getNotCachedMessage",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) +
            CoreFormatter.isNullMessageWithName(regular, "Regular external element data") +
            CoreFormatter.isNullMessageWithName(external, "External element data")
        );
    }

    static String getJSDependenciesMessage(String message, boolean status) {
        return CoreFormatter.getExecuteFragment(status) + " via Devtools console - Set JS Dependencies: " + message + CoreFormatterConstants.END_LINE;
    }
}
