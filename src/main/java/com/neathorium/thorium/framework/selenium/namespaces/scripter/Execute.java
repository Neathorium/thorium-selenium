package com.neathorium.thorium.framework.selenium.namespaces.scripter;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.DataFunctions;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.wait.exceptions.WaitTimeoutException;
import com.neathorium.thorium.core.wait.namespaces.WaitFunctions;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitDataFactory;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitTimeDataFactory;
import com.neathorium.thorium.exceptions.constants.ExceptionConstants;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.constants.DriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.ScriptExecutorConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.Attribute;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.ClickFunctions;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.GetStyle;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.ReadyState;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.ScrollIntoView;
import com.neathorium.thorium.framework.selenium.constants.scripts.general.ShadowRoot;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.*;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.scripter.ScriptParametersData;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.Predicate;

import static com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions.getWith;
import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface Execute {
    private static <T> Data<T> isCommonExistsCore(WebDriver driver, String nameof, String isExists, Data<T> defaultValue) {
        final var result = Driver.execute(isExists).apply(driver);
        return getWith((T)result.OBJECT(), result.STATUS(), result.MESSAGE());
    }

    private static <T> DriverFunction<T> isCommonExistsCore(String nameof, String isExists, Data<T> defaultValue) {
        return driver -> isCommonExistsCore(driver, nameof, isExists, defaultValue);
    }

    static <T> DriverFunction<T> isCommonExists(String nameof, String isExists, Data<T> defaultValue) {
        return ExecutionCore.ifDriver(
            nameof,
            isNotBlank(isExists) && NullablePredicates.isNotNull(defaultValue),
            isCommonExistsCore(nameof, isExists, defaultValue),
            defaultValue
        );
    }

    private static Data<Boolean> setCommonCore(WebDriver driver, DriverFunction<Boolean> precondition, String function, Data<Boolean> defaultValue) {
        Data<?> result = precondition.apply(driver);
        if (BooleanUtilities.isFalse(result.STATUS())) {
            return defaultValue;
        }

        if (BooleanUtilities.isFalse(result.OBJECT())) {
            result = Driver.execute(function).apply(driver);
        }

        final var status = DataPredicates.isValidNonFalse(result);
        return DataFactoryFunctions.getBoolean(status, CoreFormatter.getExecuteFragment(status) + " Scroll into view: " + DataFunctions.getFormattedMessage(result) + CoreFormatterConstants.END_LINE);
    }

    private static DriverFunction<Boolean> setCommonCore(DriverFunction<Boolean> preconditon, String function, Data<Boolean> defaultValue) {
        return driver -> setCommonCore(driver, preconditon, function, defaultValue);
    }

    static DriverFunction<Boolean> setCommon(String nameof, DriverFunction<Boolean> precondition, String function, Data<Boolean> defaultValue) {
        return ExecutionCore.ifDriver(
            nameof,
            NullablePredicates.areNotNull(precondition, defaultValue) && isNotBlank(function),
            setCommonCore(precondition, function, defaultValue),
            defaultValue
        );
    }


    static <T> DriverFunction<T> commonExecutor(String nameof, DriverFunction<WebElement> getter, String function, Data<T> defaultValue) {
        return ifDriver(
            nameof,
            NullablePredicates.isNotNull(getter),
            driver -> {
                final var result = Driver.executeSingleParameter(function, ScriptExecuteFunctions.handleDataParameterWithDefaults(getter.apply(driver))).apply(driver);
                return getWith((T)result.OBJECT(), result.STATUS(), result.MESSAGE());
            },
            defaultValue
        );
    }

    static DriverFunction<Boolean> isScrollIntoViewExistsData() {
        return ExecutionCore.ifDriver(
            "isScrollIntoViewExistsData",
            driver -> {
                final var script = ScrollIntoView.IS_EXISTS;
                final var result = Driver.execute(script).apply(driver);
                return getWith(Boolean.valueOf(result.OBJECT().toString()), result.STATUS(), result.MESSAGE());
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(LazyElement data, int interval, int duration) {
        return SeleniumUtilities.isNotNullLazyElement(data) ? scrollIntoViewExecutor(data.get(), interval, duration) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(DriverFunction<WebElement> getter, int interval, int duration) {
        return ifDriver(
            "scrollIntoViewExecutor",
            NullablePredicates.isNotNull(getter),
            driver -> {
                final var parameters = new ScriptParametersData<>(getter.apply(driver), DataPredicates::isValidNonFalse, SeleniumUtilities::unwrapToArray);
                final var result = Driver.executeSingleParameter(ScrollIntoView.EXECUTE, ScriptExecuteFunctions.handleDataParameter(parameters)).apply(driver);
                final var resultObject = result.OBJECT();
                if (DataPredicates.isInvalidOrFalse(result)) {
                    return DataFactoryFunctions.replaceObject(result, NullablePredicates.isNotNull(resultObject));
                }

                final DriverFunction<Boolean> step = d -> {
                    final var r = Driver.executeSingleParameter(ScrollIntoView.CHECK, ScriptExecuteFunctions.handleDataParameter(parameters)).apply(d);
                    final var status = Boolean.parseBoolean("" + DataFunctions.getObject(r));
                    final var message = "Element was" + (status ? "" : "n't") + " scrolled into view" + CoreFormatterConstants.END_LINE;
                    return DataFactoryFunctions.getWith(status, status, "scrollIntoViewVerifier", message, r.EXCEPTION());
                };
                Data<Boolean> verifierResult = CoreDataConstants.NULL_BOOLEAN;
                var exception = ExceptionConstants.EXCEPTION;
                try {
                    verifierResult = WaitConditions.waitWith(step, WaitPredicateFunctions::isTruthyData, interval, duration, "scroll into view").apply(driver);
                } catch (WaitTimeoutException ex) {
                    exception = ex;
                }

                final var status = ExceptionFunctions.isNonException(exception);
                return DataFactoryFunctions.getWith(status, status, verifierResult.MESSAGE(), exception);
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> setScrollIntoView() {
        final var nameof = "setScrollIntoView";
        return ExecutionCore.ifDriver(
            nameof,
            driver -> {
                final var isExists = isScrollIntoViewExistsData().apply(driver);
                var status = false;
                Data<?> result = null;
                if (DataPredicates.isValid(isExists) && DataFunctions.getStatus(isExists)) {
                    result = Driver.execute(ScrollIntoView.SET_FUNCTIONS).apply(driver);
                    status = DataPredicates.isValidNonFalse(result);
                }

                return DataFactoryFunctions.getBoolean(status, SeleniumFormatter.getScrollIntoViewMessage(DataFunctions.getFormattedMessage(result), status));
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoView(LazyElement data, int interval, int duration) {
        return SeleniumExecutor.execute(Driver.isElementHidden(data), setScrollIntoView(), scrollIntoViewExecutor(data, interval, duration));
    }

    static DriverFunction<Boolean> scrollIntoViewEvenDisplayed(LazyElement data, int interval, int duration) {
        return SeleniumExecutor.execute(setScrollIntoView(), scrollIntoViewExecutor(data, interval, duration));
    }

    static DriverFunction<Boolean> scrollIntoView(Data<LazyElement> data, int interval, int duration) {
        return ExecutionCore.ifDriver("scrollIntoView", DataPredicates.isValidNonFalse(data), scrollIntoView(data.OBJECT(), interval, duration), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> scrollIntoView(By locator, SingleGetter getter, int interval, int duration) {
        return scrollIntoView(LocatorRepository.getIfContains(locator, getter), interval, duration);
    }

    static DriverFunction<Boolean> scrollIntoView(By locator, int interval, int duration) {
        return scrollIntoView(locator, SingleGetter.DEFAULT, interval, duration);
    }

    static <T> Data<Object[]> handleDataParameterDefault(Data<T> parameter) {
        return getWith(
            ScriptExecuteFunctions.handleDataParameter(new ScriptParametersData<>(parameter, DataPredicates::isValidNonFalse, SeleniumUtilities::unwrapToArray)),
            true,
            "Handle Data parameter default message"
        );
    }

    static DriverFunction<Object> getStyle(LazyElement data) {
        return ifDriver(
            "getStyle",
            NullablePredicates.isNotNull(data),
            driver -> {
                final var steps = DataExecutionFunctions.validChain(data.get(), Execute::handleDataParameterDefault, CoreDataConstants.NULL_PARAMETER_ARRAY);
                final var parameter = SeleniumExecutor.conditionalSequence(Driver.isElementPresent(data), DriverFunctionFactory.getFunction(steps), Object[].class).apply(driver);

                return DataPredicates.isValidNonFalse(parameter) ? Driver.executeSingleParameter(GetStyle.GET_STYLES_IN_JSON, parameter.OBJECT()).apply(driver) : CoreDataConstants.NULL_OBJECT;
            },
            CoreDataConstants.NULL_OBJECT
        );
    }

    private static DriverFunction<WebElement> getShadowRootCore(Data<WebElement> data) {
        return ifDriver(
            "getShadowRootCore",
            SeleniumUtilities.isNotNullWebElement(data),
            driver -> {
                final var parameter = handleDataParameterDefault(data);
                if(DataPredicates.isInvalidOrFalse(parameter)) {
                    return DataFactoryFunctions.replaceMessage(SeleniumDataConstants.NULL_ELEMENT, DataFunctions.getFormattedMessage(parameter));
                }

                final var result = Driver.executeSingleParameter(ShadowRoot.GET_SHADOW_ROOT, parameter.OBJECT()).apply(driver);
                return DataPredicates.isValidNonFalse(result) ? (
                    getWith((WebElement)result.OBJECT(), result.STATUS(), result.MESSAGE())
                ) : DataFactoryFunctions.replaceMessage(SeleniumDataConstants.NULL_ELEMENT, DataFunctions.getFormattedMessage(data));
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(Data<WebElement> data) {
        return getShadowRootCore(data);
    }

    static DriverFunction<WebElement> getShadowRoot(DriverFunction<WebElement> getter) {
        return ExecutionCore.ifDriverFunction("getShadowRoot", NullablePredicates::isNotNull, getter, Execute::getShadowRootCore, SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRoot(LazyElement element) {
        return SeleniumUtilities.isNotNullLazyElement(element) ? (
            getShadowRoot(element.get())
        ) : DriverFunctionFactory.get(DataFactoryFunctions.replaceMessage(SeleniumDataConstants.NULL_ELEMENT, "getShadowRoot", "LazyElement element" + CoreFormatterConstants.WAS_NULL));
    }

    static DriverFunction<WebElement> getShadowRoot(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("getShadowRoot", DataPredicates.isValidNonFalse(data), getShadowRoot(data.OBJECT()), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRoot(By locator, SingleGetter getter) {
        return getShadowRoot(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<WebElement> getShadowRoot(By locator) {
        return getShadowRoot(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<Boolean> readyState() {
        final var negative = CoreDataConstants.NULL_BOOLEAN;
        return ExecutionCore.ifDriver(
            "readyState",
            driver -> {
                final var result = Driver.execute(ReadyState.script).apply(driver);
                return DataPredicates.isValidNonFalse(result) ? DataFactoryFunctions.replaceObject(result, Boolean.valueOf(result.OBJECT().toString())) : negative;
            },
            negative
        );
    }

    static DriverFunction<String> setAttribute(Data<WebElement> element, String attribute, String value) {
        final var nameof = "setAttribute";
        return ifDriver(
            nameof,
            StringUtilities.areNotBlank(attribute, value) && SeleniumUtilities.isNotNullWebElement(element),
            driver -> {
                final var parametersData = DataFactoryFunctions.getArrayWithName(ArrayUtils.toArray(element, attribute, value));
                if (DataPredicates.isInvalidOrFalse(parametersData)) {
                    return CoreDataConstants.NULL_STRING;
                }

                final var result = Driver.executeParameters(Attribute.SET_ATTRIBUTE, parametersData.OBJECT()).apply(driver);
                final var returnedValue = String.valueOf(result.OBJECT());
                final var status = DataPredicates.isValidNonFalse(result) && Objects.equals(value, returnedValue);
                final var message = "Value \"" + value + "\" was " + CoreFormatter.getOptionMessage(status) + "set" + CoreFormatterConstants.END_LINE;
                return DataFactoryFunctions.getWith(returnedValue, status, message);
            },
            CoreDataConstants.NULL_STRING
        );
    }

    static DriverFunction<String> setAttribute(DriverFunction<WebElement> element, String attribute, String value) {
        return ifDriver(
            "setAttribute",
            NullablePredicates.isNotNull(element) && StringUtilities.areNotBlank(attribute, value),
            driver -> setAttribute(element.apply(driver), attribute, value).apply(driver),
            CoreDataConstants.NULL_STRING
        );
    }

    static DriverFunction<String> setAttribute(LazyElement element, String attribute, String value) {
        return ExecutionCore.ifDriver(
            "setAttribute",
            SeleniumUtilities.isNotNullLazyElement(element),
            setAttribute(element.get(), attribute, value),
            CoreDataConstants.NULL_STRING
        );
    }

    static DriverFunction<String> setId(Data<WebElement> element, String value) {
        return setAttribute(element, ScriptExecutorConstants.PRIMARY_STRATEGY, value);
    }

    static DriverFunction<String> setId(LazyElement element, String value) {
        return setAttribute(element, ScriptExecutorConstants.PRIMARY_STRATEGY, value);
    }

    static DriverFunction<Boolean> clickEventDispatcher(Data<WebElement> element) {
        final var nameof = "clickEventDispatcher";
        return ifDriver(
            nameof,
            SeleniumUtilities.isNotNullWebElement(element),
            driver -> {
                if (DataPredicates.isInvalidOrFalse(element)) {
                    return CoreDataConstants.NULL_BOOLEAN;
                }

                final var parametersData = SeleniumUtilities.unwrapToArray(element);
                if (NullablePredicates.isNull(parametersData)) {
                    return CoreDataConstants.NULL_BOOLEAN;
                }

                final var result = Driver.executeParameters(ClickFunctions.CLICK_DISPATCHER, parametersData).apply(driver);
                final var returnedValue = String.valueOf(result.OBJECT());
                final var status = DataPredicates.isValidNonFalse(result);
                return getWith(BooleanUtilities.castToBoolean(returnedValue), status, "Element was " + CoreFormatter.getOptionMessage(status) + "clicked" + CoreFormatterConstants.END_LINE);
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> clickEventDispatcher(DriverFunction<WebElement> getter) {
        return ifDriver("clickEventDispatcher", NullablePredicates.isNotNull(getter), driver -> clickEventDispatcher(getter.apply(driver)).apply(driver), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clickEventDispatcher(LazyElement element) {
        return ExecutionCore.ifDriver("clickEventDispatcher", SeleniumUtilities.isNotNullLazyElement(element), clickEventDispatcher(element.get()), CoreDataConstants.NULL_BOOLEAN);
    }




}
