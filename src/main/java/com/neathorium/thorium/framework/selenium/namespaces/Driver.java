package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.constants.exception.ExceptionConstants;
import com.neathorium.thorium.core.namespaces.exception.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.abstracts.regular.AbstractElementFunctionParameters;
import com.neathorium.thorium.framework.selenium.constants.DriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.ElementFinderConstants;
import com.neathorium.thorium.framework.selenium.constants.ElementFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.ExecuteCoreDataConstants;
import com.neathorium.thorium.framework.selenium.constants.ExecuteCoreFunctionDataConstants;
import com.neathorium.thorium.framework.selenium.constants.RepositoryConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumGetOrderConstants;
import com.neathorium.thorium.framework.selenium.constants.driver.quit.QuitFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.lazy.GetLazyElementConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.driver.execute.DriverExecuteFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.driver.invoke.ElementInvokeFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.driver.properties.DriverPropertyFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.driver.searchcontext.SearchContextFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.WebElementListValidators;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.LazyElementWithOptionsDataFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumDataFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.WebElementListFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyFilteredElementParametersFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.ElementRepository;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.Execute;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.URLUtilities;
import com.neathorium.thorium.framework.selenium.namespaces.validators.GetElementByDataValidators;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.ExternalSeleniumSelectorData;
import com.neathorium.thorium.framework.selenium.records.GetElementByData;
import com.neathorium.thorium.framework.selenium.records.GetWithDriverData;
import com.neathorium.thorium.framework.selenium.records.SwitchResultMessageData;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementConditionParameters;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementParameterizedValueParameters;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementStringValueParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.CachedLookupKeysData;
import com.neathorium.thorium.framework.selenium.records.lazy.GetLazyElementData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWithOptionsData;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.core.constants.CoreConstants;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.core.extensions.boilers.StringSet;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.extensions.namespaces.factories.DecoratedListFactory;
import com.neathorium.thorium.core.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.core.extensions.namespaces.predicates.SizablePredicates;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.DataFunctions;
import com.neathorium.thorium.core.namespaces.factories.MethodMessageDataFactory;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.namespaces.validators.DataValidators;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.constants.AdjusterConstants;
import com.neathorium.thorium.framework.core.namespaces.Adjuster;
import com.neathorium.thorium.framework.core.namespaces.FrameworkFunctions;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.core.records.GetByFilterFormatterData;
import com.neathorium.thorium.framework.core.records.InternalSelectorData;
import com.neathorium.thorium.framework.core.records.ProbabilityData;
import com.neathorium.thorium.framework.core.records.lazy.ExternalSelectorData;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullWebElement;
import static com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities.isNullWebElement;
import static com.neathorium.thorium.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.neathorium.thorium.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.neathorium.thorium.core.extensions.namespaces.NullableFunctions.isNull;
import static com.neathorium.thorium.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.neathorium.thorium.core.namespaces.DataFactoryFunctions.replaceMessage;
import static com.neathorium.thorium.core.namespaces.DataFactoryFunctions.replaceName;
import static com.neathorium.thorium.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.neathorium.thorium.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isNegativeMessage;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static com.neathorium.thorium.framework.core.namespaces.FrameworkCoreUtilities.isNullLazyLocator;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface Driver {
    static DriverFunction<String> getTitle() {
        return DriverPropertyFunctions.getTitle();
    }

    static DriverFunction<String> getWindowHandle() {
        return DriverPropertyFunctions.getWindowHandle();
    }

    static DriverFunction<String> getUrl() {
        return DriverPropertyFunctions.getUrl();
    }

    static DriverFunction<StringSet> getWindowHandles() {
        return DriverPropertyFunctions.getWindowHandles();
    }

    static DriverFunction<Integer> getWindowHandleAmount() {
        return DriverPropertyFunctions.getWindowHandleAmount();
    }

    static Data<Integer> getWindowHandleAmountData(Data<StringSet> data) {
        return FrameworkFunctions.getWindowHandleAmount(data, "window Handles");
    }

    static <T> Data<Integer> getCountOfElements(Data<WebElementList> data) {
        return FrameworkFunctions.getCountOfElements(SeleniumDataFactory.getUnwrapped(data), "Element");
    }

    static <T> DriverFunction<Integer> getCountOfElements(DriverFunction<WebElementList> getter) {
        return ifDriver(
            "getCountOfElements",
            isNotNull(getter),
            SeleniumCoreConstants.WEBELEMENT_LIST_VALIDATOR,
            getter.andThen(SeleniumDataFactory::getUnwrapped),
            FrameworkFunctions.getCountOfElements("Element"),
            SeleniumDataConstants.NULL_INTEGER_NULL_DRIVER
        );
    }


    static DriverFunction<Object> execute(String script) {
        return DriverExecuteFunctions.execute(ExecuteCoreFunctionDataConstants.EXECUTE, ExecuteCoreDataConstants.EXECUTE_RETURN_OBJECT, script);
    }

    static DriverFunction<Object> executeAsync(String script) {
        return DriverExecuteFunctions.execute(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC, ExecuteCoreDataConstants.EXECUTE_RETURN_OBJECT, script);
    }

    static DriverFunction<Object> executeParameters(String script, Object[] parameters) {
        return DriverExecuteFunctions.executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_PARAMETERS, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameters);
    }

    static DriverFunction<Object> executeAsyncParameters(String script, Object[] parameters) {
        return DriverExecuteFunctions.executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC_PARAMETERS, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameters);
    }

    static DriverFunction<Object> executeSingleParameter(String script, Object[] parameter) {
        return DriverExecuteFunctions.executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_SINGLE_PARAMETER, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameter);
    }

    static DriverFunction<Object> executeAsyncSingleParameter(String script, Object[] parameter) {
        return DriverExecuteFunctions.executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC_SINGLE_PARAMETER, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameter);
    }

    static Function<Data<SearchContext>, Data<WebElement>> invokeGetElement(By locator) {
        return ElementInvokeFunctions.invokeGetElement(locator);
    }

    private static <T, U, V> Data<V> elementCore(V object, Data<T> data, boolean status, String elementName, AbstractElementFunctionParameters<U, V> parameters) {
        final var formatData = parameters.formatData;
        var message = formatData.formatter.apply(elementName, formatData.descriptor, object);
        if (CoreUtilities.isFalse(status)) {
            message += data.message.formatter.apply(data.message.nameof, data.message.message);
        }

        return DataFactoryFunctions.getWith(object, status, formatData.conditionName, message);
    }

    private static <T> Data<Boolean> isElementCore(String elementName, Data<T> data, ElementConditionParameters<Boolean> parameters) {
        final var status = parameters.inverter.apply(isValidNonFalse(data));
        return elementCore(status, data, status, elementName, parameters);
    }

    private static Data<String> getElementValueCore(String elementName, Data<String> data, AbstractElementFunctionParameters<String, String> parameters) {
        final var status = isValidNonFalse(data);
        return elementCore(data.object, data, status, elementName, parameters);
    }

    private static <T> Function<Data<T>, Data<Boolean>> isElementCore(String elementName, ElementConditionParameters<Boolean> parameters) {
        return data -> isElementCore(elementName, data, parameters);
    }

    private static Function<Data<String>, Data<String>> getElementValueCore(String elementName, AbstractElementFunctionParameters<String, String> parameters) {
        return data -> getElementValueCore(elementName, data, parameters);
    }

    private static DriverFunction<Boolean> isElementPositive(LazyElement element, ElementConditionParameters<Boolean> parameters, Data<Boolean> guard) {
        return parameters.handler.apply(parameters.function.apply(element), isElementCore(element.name, parameters), replaceName(guard, "isElementPositive"));
    }

    private static DriverFunction<String> getElementValuePositive(String name, DriverFunction<String> function, AbstractElementFunctionParameters<String, String> parameters, Data<String> guard) {
        return parameters.handler.apply(function, getElementValueCore(name, parameters), replaceName(guard, "getElementValuePositive"));
    }

    private static DriverFunction<Boolean> isElement(LazyElement element, ElementConditionParameters<Boolean> parameters) {
        final var negative = CoreDataConstants.NULL_BOOLEAN;
        return ExecutionCore.ifDriver("isElement", SeleniumFormatter.isElementFunctionMessage(element, parameters), isElementPositive(element, parameters, negative), negative);
    }

    private static DriverFunction<String> getElementValue(LazyElement element, ElementStringValueParameters<String> parameters) {
        final var negative = CoreDataConstants.NULL_STRING;
        final var errorMessage = SeleniumFormatter.isElementFunctionMessage(element, parameters);
        if (isNotBlank(errorMessage)) {
            return DriverFunctionFactory.get(replaceMessage(negative, "getElementValue", errorMessage));
        }

        return getElementValuePositive(element.name, parameters.function.apply(element), parameters, negative);
    }

    private static DriverFunction<String> getElementValue(LazyElement element, String value, ElementParameterizedValueParameters<String> parameters) {
        final var negative = CoreDataConstants.NULL_STRING;
        final var errorMessage = SeleniumFormatter.isElementFunctionMessage(element, parameters) + isNullMessageWithName(value, "Value");
        if (isNotBlank(errorMessage)) {
            return DriverFunctionFactory.get(replaceMessage(negative, "getElementValue", errorMessage));
        }

        return getElementValuePositive(element.name, parameters.function.apply(element, value), parameters, negative);
    }

    static DriverFunction<Boolean> isElementPresent(LazyElement element) {
        return isElement(element, ElementFunctionConstants.PRESENT);
    }

    static DriverFunction<Boolean> isElementAbsent(LazyElement element) {
        return isElement(element, ElementFunctionConstants.ABSENT);
    }

    static DriverFunction<Boolean> isElementDisplayed(LazyElement element) {
        return isElement(element, ElementFunctionConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> isElementEnabled(LazyElement element) {
        return isElement(element, ElementFunctionConstants.ENABLED);
    }

    static DriverFunction<Boolean> isElementClickable(LazyElement element) {
        return isElement(element, ElementFunctionConstants.CLICKABLE);
    }

    static DriverFunction<Boolean> isElementSelected(LazyElement element) {
        return isElement(element, ElementFunctionConstants.SELECTED);
    }

    static DriverFunction<Boolean> isElementHidden(LazyElement element) {
        return isElement(element, ElementFunctionConstants.HIDDEN);
    }

    static DriverFunction<Boolean> isElementDisabled(LazyElement element) {
        return isElement(element, ElementFunctionConstants.DISABLED);
    }

    static DriverFunction<Boolean> isElementUnclickable(LazyElement element) {
        return isElement(element, ElementFunctionConstants.UNCLICKABLE);
    }

    static DriverFunction<Boolean> isElementUnselected(LazyElement element) {
        return isElement(element, ElementFunctionConstants.UNSELECTED);
    }

    static DriverFunction<Boolean> isElement(Function<LazyElement, DriverFunction<Boolean>> elementCondition, LazyElement element) {
        return ifDriver("isElement", SeleniumUtilities.isNotNullLazyElement(element) && areNotNull(elementCondition), elementCondition.apply(element), CoreDataConstants.PARAMETERS_NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isElement(Function<LazyElement, DriverFunction<Boolean>> elementCondition, Data<LazyElement> data) {
        return isValidNonFalse(data) ? isElement(elementCondition, data.object) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<String> getElementText(LazyElement element) {
        return getElementValue(element, ElementFunctionConstants.TEXT);
    }

    static DriverFunction<String> getElementTagName(LazyElement element) {
        return getElementValue(element, ElementFunctionConstants.TAGNAME);
    }

    static DriverFunction<String> getElementAttribute(LazyElement element, String value) {
        return getElementValue(element, value, ElementFunctionConstants.ATTRIBUTE);
    }

    static DriverFunction<String> getElementCssValue(LazyElement element, String value) {
        return getElementValue(element, value, ElementFunctionConstants.CSS_VALUE);
    }

    static DriverFunction<String> getElementAttributeValue(LazyElement element) {
        return getElementAttribute(element, "value");
    }

    static DriverFunction<String> getElementText(Data<LazyElement> data) {
        return ifDriver("getElementText", DataValidators.isInvalidOrFalseMessage(data), getElementText(data.object), CoreDataConstants.NULL_STRING);
    }

    static DriverFunction<String> getElementTagName(Data<LazyElement> data) {
        return ifDriver("getElementTagName", DataValidators.isInvalidOrFalseMessage(data), getElementTagName(data.object), CoreDataConstants.NULL_STRING);
    }

    static DriverFunction<String> getElementAttribute(Data<LazyElement> data, String value) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data) + isBlankMessageWithName(value, "Attribute value");
        return isBlank(errorMessage) ? getElementAttribute(data.object, value) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementAttribute", errorMessage));
    }

    static DriverFunction<String> getElementCssValue(Data<LazyElement> data, String value) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data) + isBlankMessageWithName(value, "CSS value");
        return isBlank(errorMessage) ? getElementCssValue(data.object, value) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementCssValue", errorMessage));
    }

    static DriverFunction<String> getElementAttributeValue(Data<LazyElement> data) {
        return getElementAttribute(data, "value");
    }

    static DriverFunction<Boolean> isElementPresent(By locator, SingleGetter getter) {
        return isElement(Driver::isElementPresent, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementDisplayed(By locator, SingleGetter getter) {
        return isElement(Driver::isElementDisplayed, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementEnabled(By locator, SingleGetter getter) {
        return isElement(Driver::isElementEnabled, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementClickable(By locator, SingleGetter getter) {
        return isElement(Driver::isElementClickable, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementSelected(By locator, SingleGetter getter) {
        return isElement(Driver::isElementSelected, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementAbsent(By locator, SingleGetter getter) {
        return isElement(Driver::isElementAbsent, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementHidden(By locator, SingleGetter getter) {
        return isElement(Driver::isElementHidden, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementDisabled(By locator, SingleGetter getter) {
        return isElement(Driver::isElementDisabled, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementUnclickable(By locator, SingleGetter getter) {
        return isElement(Driver::isElementUnclickable, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementUnselected(By locator, SingleGetter getter) {
        return isElement(Driver::isElementUnselected, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementText(By locator, SingleGetter getter) {
        return getElementText(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementTagName(By locator, SingleGetter getter) {
        return getElementTagName(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementAttribute(By locator, SingleGetter getter, String attribute) {
        return getElementAttribute(LocatorRepository.getIfContains(locator, getter), attribute);
    }
    static DriverFunction<String> getElementAttributeValue(By locator, SingleGetter getter) {
        return getElementAttributeValue(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementCssValue(By locator, SingleGetter getter, String cssValue) {
        return getElementCssValue(LocatorRepository.getIfContains(locator, getter), cssValue);
    }

    static DriverFunction<Boolean> isElementPresent(By locator) {
        return isElement(Driver::isElementPresent, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementDisplayed(By locator) {
        return isElement(Driver::isElementDisplayed, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementEnabled(By locator) {
        return isElement(Driver::isElementEnabled, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementClickable(By locator) {
        return isElement(Driver::isElementClickable, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementSelected(By locator) {
        return isElement(Driver::isElementSelected, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementAbsent(By locator) {
        return isElement(Driver::isElementAbsent, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementHidden(By locator) {
        return isElement(Driver::isElementHidden, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementDisabled(By locator) {
        return isElement(Driver::isElementDisabled, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementUnclickable(By locator) {
        return isElement(Driver::isElementUnclickable, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementUnselected(By locator) {
        return isElement(Driver::isElementUnselected, LocatorRepository.getIfContains(locator));
    }

    static DriverFunction<String> getElementText(By locator) {
        return getElementText(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementTagName(By locator) {
        return getElementTagName(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementAttribute(By locator, String attribute) {
        return getElementAttribute(locator, SingleGetter.DEFAULT, attribute);
    }

    static DriverFunction<String> getElementAttributeValue(By locator) {
        return getElementAttributeValue(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementCssValue(By locator, String cssValue) {
        return getElementCssValue(locator, SingleGetter.DEFAULT, cssValue);
    }

    private static Data<WebElementList> getElementsCore(SearchContext context, By locator) {
        final var nameof = "getElementsCore";
        final Function<SearchContext, List<WebElement>> function = locator::findElements;
        final var composed = function.andThen(WebElementList::new);

        final var result = SeleniumExceptionHandlers.findElementsHandler(new HandleResultData<>(composed, context, SeleniumCoreConstants.NULL_ELEMENT_LIST));
        final var list = result.object;
        final var status = result.status;
        final var exception = result.exception;
        final var message = status ? SeleniumFormatter.getFindElementsMessage(locator.toString(), list.size()) : "An Exception(" + exception.getClass() + ") has occurred" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getWith(list, status, nameof, message, exception);
    }

    private static Data<WebElementList> getElementsCore(Data<SearchContext> contextData, LazyLocator locator) {
        final var nameof = "getElementsCore";
        final var negative = SeleniumCoreConstants.NULL_ELEMENT_LIST;
        var errorMessage = FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator) + CoreFormatter.isInvalidOrFalseMessage(contextData);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(negative, nameof, errorMessage);
        }

        final var data = SeleniumUtilities.getLocator(locator);
        errorMessage = CoreFormatter.isInvalidOrFalseMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(negative, nameof, errorMessage);
        }

        final var context = contextData.object;
        errorMessage = isNullMessageWithName(context, "Context");
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(negative, nameof, errorMessage);
        }
        return getElementsCore(context, data.object);
    }

    private static Function<Data<SearchContext>, Data<WebElementList>> getElementsCore(LazyLocator locator) {
        return context -> getElementsCore(context, locator);
    }

    private static DriverFunction<WebElementList> getElementsIf(String errorMessage, DriverFunction<WebElementList> positive) {
        return ExecutionCore.ifDriver("getElements", errorMessage, SeleniumExecutor.execute(switchToDefaultContent(), positive), SeleniumDataConstants.NULL_LIST);
    }

    private static Data<WebElementList> getElements(WebDriver driver, LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        final var elementList = WebElementListFactory.getWithEmptyList();
        final var length = locators.size();
        Data<WebElementList> data;
        LazyLocator locator;
        WebElementList list;
        var message = new StringBuilder();
        var index = 0;
        for (; index < length; ++index) {
            locator = locators.get(index);
            if (isNull(locator)) {
                break;
            }

            data = getter.apply(locator).apply(driver);
            message.append(index).append(DataFunctions.getFormattedMessage(data)).append(CoreFormatterConstants.END_LINE);
            if (isInvalidOrFalse(data)) {
                continue;
            }

            list = data.object;
            if (Objects.equals(locator.STRATEGY, "id") && (list.isMany())) {
                message.append("There's more than one element with id(\"" + locator.LOCATOR + "\") - amount(\"" + list.size() + "\"). Returning" + CoreFormatterConstants.END_LINE);
                break;
            }

            elementList.addAllNullSafe(list);
        }

        return DataFactoryFunctions.getWith(elementList, elementList.isNotNullAndNonEmpty() && SizablePredicates.isSizeEqualTo(length, index), message.toString());
    }

    private static DriverFunction<WebElementList> getElements(DriverFunction<SearchContext> getter, LazyLocator locator) {
        return ExecutionCore.validChain(getter, getElementsCore(locator), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElementList> getElements(LazyLocator locator) {
        return getElementsIf(FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator), getElements(SearchContextFunctions::getSearchContext, locator));
    }

    static DriverFunction<WebElementList> getElements(By locator) {
        return getElementsIf(isNullMessageWithName(locator, "Locator"), getElements(SearchContextFunctions::getSearchContext, SeleniumLazyLocatorFactory.get(locator)));
    }

    static DriverFunction<WebElementList> getElements(LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        return getElementsIf(SeleniumFormatter.getElementsParametersMessage(locators, getter), driver -> getElements(driver, locators, getter));
    }

    static DriverFunction<WebElementList> getElements(LazyLocatorList locators) {
        return getElementsIf(SeleniumFormatter.getElementsParametersMessage(locators), driver -> getElements(driver, locators, Driver::getElements));
    }

    static <T> Data<WebElement> getElementBy(GetElementByData<T, WebElement, WebElementList> defaults, Data<WebElementList> data, T filter) {
        final var guardName = "getElementBy";
        var errorMessage = GetElementByDataValidators.getValidGetElementByDataMessage(defaults);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, guardName, errorMessage);
        }

        final var nameof = defaults.nameof;
        errorMessage = defaults.validator.apply(data, filter);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.prependMessage(defaults.defaultValue, nameof, errorMessage);
        }

        final var object = defaults.getter.apply(data, filter);
        final var status = WebElementValidators.isNotNull(object);
        final var message = defaults.formatter.apply(new GetByFilterFormatterData<>(filter, defaults.filterName, status, data.object.size(), DataFunctions.getFormattedMessage(data)));
        return DataFactoryFunctions.getWith(object, status, nameof, message);
    }

    static Function<Data<WebElementList>, Data<WebElement>> getElementByContainedText(String message) {
        return data -> getElementBy(DriverFunctionConstants.BY_CONTAINED_TEXT_CONSTANTS, data, message);
    }

    static Function<Data<WebElementList>, Data<WebElement>> getElementByIndex(int index) {
        return data -> getElementBy(DriverFunctionConstants.BY_INDEX_CONSTANTS, data, index);
    }

    static DriverFunction<WebElement> getElementByIndex(DriverFunction<WebElementList> getter, int index) {
        return ifDriver(
            "getElementByIndex",
            isNullMessageWithName(getter, "Getter"),
            ExecutionCore.validChain(getter, getElementByIndex(index), SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getElementByContainedText(DriverFunction<WebElementList> getter, String message) {
        return ifDriver(
            "getElementByContainedText",
            isNotNull(getter) && isNotBlank(message),
            ExecutionCore.validChain(getter, getElementByContainedText(message), SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getElementByIndex(By locator, int index) {
        return getElementByIndex(getElements(locator), index);
    }

    static DriverFunction<WebElement> getElementByContainedText(By locator, String message) {
        return getElementByContainedText(getElements(locator), message);
    }

    static Data<WebElement> getElementFromSingle(Data<WebElementList> data) {
        final var nameof = "getElementFromSingle";
        return isValidNonFalse(data) ? getElementByIndex(0).apply(data) : DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Data or index" + CoreFormatterConstants.WAS_NULL + data.toString());
    }

    static DriverFunction<WebElement> getElementFromSingle(DriverFunction<WebElementList> getter) {
        return ifDriver("getElementFromSingle", isNotNull(getter), ExecutionCore.validChain(getter, getElementByIndex(0), SeleniumDataConstants.NULL_ELEMENT), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<Integer> getCountOfElements(By locator) {
        return ifDriver("getCountOfElements", isNullMessageWithName(locator, "By locator"), getCountOfElements(getElements(locator)), SeleniumDataConstants.NO_ELEMENTS_FOUND);
    }

    private static Data<WebElementList> getElementsAmountCore(Data<WebElementList> data, By locator, int expected) {
        final var nameof = "getElementsAmountCore";
        var errorMessage = CoreFormatter.getValidNonFalseAndValidContainedMessage(data, CoreFormatter::isNullOrEmptyMessage) + isNullMessageWithName(locator, "Locator") + isNegativeMessage(expected);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(SeleniumDataConstants.NULL_LIST, nameof, errorMessage);
        }

        final var object = data.object;
        final var size = WebElementListValidators.isValid(data) ? object.size() : 0;
        final var status = SizablePredicates.isSizeEqualTo(size, expected);
        return DataFactoryFunctions.getWith(object, status, nameof, SeleniumFormatter.getElementsAmountMessage(locator, status, expected, size), data.exception);
    }

    static Function<Data<WebElementList>, Data<WebElementList>> getElementsAmountCore(By locator, int expected) {
        return data -> getElementsAmountCore(data, locator, expected);
    }

    static DriverFunction<WebElementList> getElementsAmount(DriverFunction<WebElementList> getter, LazyLocator locator, int expected) {
        return ifDriver(
            "getElementsAmount",
            isNotNull(getter) && SeleniumUtilities.isValidLazyLocator(locator) && BasicPredicates.isNonNegative(expected),
            ExecutionCore.validChain(getter, getElementsAmountCore(SeleniumUtilities.getLocator(locator).object, expected), SeleniumDataConstants.NULL_LIST),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getElementsAmount(By locator, int expected) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        return ifDriver(
            "getElementsAmount",
            SeleniumUtilities.isNotNullLazyLocator(lazyLocator) && BasicPredicates.isNonNegative(expected),
            getElementsAmount(getElements(lazyLocator), lazyLocator, expected),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getElementsAmount(LazyLocator locator, int expected) {
        return ifDriver(
            "getElementsAmount",
            SeleniumUtilities.isNotNullLazyLocator(locator) && BasicPredicates.isNonNegative(expected),
            getElementsAmount(getElements(locator), locator, expected),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getSingleElementList(By locator) {
        return getElementsAmount(locator, 1);
    }

    static DriverFunction<WebElementList> getSingleElementList(LazyLocator locator) {
        return getElementsAmount(locator, 1);
    }

    static DriverFunction<WebElement> getElement(By locator) {
        return ifDriver("getElement", CoreFormatter.isNullMessage(locator), getElementFromSingle(getSingleElementList(locator)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElement(LazyLocator locator) {
        return ifDriver("getElement", FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator), getElementFromSingle(getSingleElementList(locator)), SeleniumDataConstants.NULL_ELEMENT);
    }

    private static Data<WebElement> getShadowRootElementCore(Data<WebElement> data) {
        final var exception = data.exception;
        final var status = SeleniumUtilities.isNotNullWebElement(data);
        final var messageData = MethodMessageDataFactory.getWith("getShadowRootElementCore", SeleniumFormatter.getShadowRootElementMessage(data.message.formatter.apply(data.message.nameof, data.message.message), status));
        return ExceptionFunctions.isNonException(exception) ? (
            DataFactoryFunctions.getWith(data.object, status, messageData)
        ) : DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, messageData, exception);
    }

    private static DriverFunction<WebElement> getShadowRootElement(Data<WebElement> data) {
        return ifDriver(
            "getShadowRootElement",
            CoreFormatter.isInvalidOrFalseMessage(data),
            ExecutionCore.validChain(Execute.getShadowRootElement(data), Driver::getShadowRootElementCore, SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }
    static DriverFunction<WebElement> getShadowRootElement(DriverFunction<WebElement> getter) {
        return ifDriver("getShadowRootElement", isNullMessageWithName(getter, "Getter"), Execute.getShadowRoot(getter), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyElement element) {
        return getShadowRootElement(element.get());
    }

    static DriverFunction<WebElement> getShadowRootElement(By locator, SingleGetter getter) {
        return ifDriver("getShadowRootElement", areNotNull(locator, getter), getShadowRootElement(LocatorRepository.getIfContains(locator, getter).object), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRootElement(By locator) {
        return getShadowRootElement(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(Data<WebElement> data, By locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            isNotNull(locator) && isValidNonFalse(data),
            getShadowRootElement(invokeGetElement(locator).apply(SearchContextFunctions.getSearchContextOf("Element data", data))),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(LazyElement element, By locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            SeleniumUtilities.isNotNullLazyElement(element) && isNotNull(locator),
            getRootElementByInvokedElement(element, SeleniumLazyLocatorFactory.get(locator)),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(Data<WebElement> data, LazyLocator locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            SeleniumUtilities.isNotNullWebElement(data) && SeleniumUtilities.isNotNullLazyLocator(locator),
            getShadowRootElement(invokeGetElement(SeleniumUtilities.getLocator(locator).object).apply(SearchContextFunctions.getSearchContextOf("Element data", data))),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(LazyElement element, LazyLocator locator) {
        return ifDriver(
            "getRootElementByInvokedElement",
            SeleniumUtilities.isNotNullLazyElement(element) && SeleniumUtilities.isNotNullLazyLocator(locator),
            driver -> getShadowRootElement(invokeGetElement(SeleniumUtilities.getLocator(locator).object).apply(SearchContextFunctions.getSearchContextOf("Element data", element.get().apply(driver)))).apply(driver),
            SeleniumDataConstants.NULL_ELEMENT//, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(Data<WebElement> data, LazyLocatorList locators) {
        return ifDriver(
            "getShadowRootElement",
            SeleniumUtilities.isNotNullWebElement(data) && isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (locators.isSingle()) {
                    return getRootElementByInvokedElement(data, locators.first()).apply(driver);
                }

                var current = data;
                var message = "";
                for (var shadowLocator : locators) {
                    if (isNull(shadowLocator)) {
                        break;
                    }

                    current = getRootElementByInvokedElement(current, shadowLocator).apply(driver);
                    if (SeleniumUtilities.isNullWebElement(current)) {
                        break;
                    }

                    message += current.message;
                }

                return replaceMessage(current, message);
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static Function<Data<WebElement>, DriverFunction<WebElement>> getShadowRootElementFunction(LazyLocatorList locators) {
        return isNotNull(locators) && locators.isNotNullAndNonEmpty() ? (
            data -> getShadowRootElement(data, locators)
        ) : data -> DriverFunctionFactory.getWithMessage(SeleniumCoreConstants.STOCK_ELEMENT, false, SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    }

    private static Data<WebElement> getShadowRootElementCore(WebDriver driver, LazyLocatorList locators) {
        final var currentBy = locators.first();
        final var current = getShadowRootElement(SeleniumUtilities.getLocator(currentBy).object).apply(driver);
        if (isInvalidOrFalse(current) || isNull(current.object)) {
            return DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Current " + CoreFormatterConstants.WAS_NULL + DataFunctions.getFormattedMessage(current));
        }

        return locators.isMany() ? getShadowRootElement(current, locators.tail()).apply(driver) : current;
    }

    private static Function<WebDriver, Data<WebElement>> getShadowRootElementCore(LazyLocatorList locators) {
        return driver -> getShadowRootElementCore(driver, locators);
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyLocatorList locators) {
        final var nameof = "getShadowRootElement";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.isNotNullAndNonEmpty() && SeleniumUtilities.isNotNullLazyLocator(locators.first()),
            DriverFunctionFactory.getFunction(getShadowRootElementCore(locators)),
            DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Shadow locators list" + CoreFormatterConstants.WAS_NULL)
        );
    }

    private static Data<WebElement> getShadowRootElementCore(WebDriver driver, DriverFunction<WebElement> getter, LazyLocatorList locators) {
        var current = getter.apply(driver);
        return SeleniumUtilities.isNotNullWebElement(current) ? getShadowRootElement(current, locators).apply(driver) : SeleniumDataConstants.NULL_ELEMENT;
    }

    private static Function<WebDriver, Data<WebElement>> getShadowRootElementCore(DriverFunction<WebElement> getter, LazyLocatorList locators) {
        return driver -> getShadowRootElementCore(driver, getter, locators);
    }

    static DriverFunction<WebElement> getShadowRootElement(DriverFunction<WebElement> data, LazyLocatorList locators) {
        return ifDriver(
            "getShadowRootElement",
            areNotNull(data, locators) && locators.isNotNullAndNonEmpty(),
            DriverFunctionFactory.getFunction(getShadowRootElementCore(data, locators)),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyElement element, LazyLocatorList locators) {
        return ifDriver("getShadowRootElement", areNotNull(element, locators) && locators.isNotNullAndNonEmpty(), getShadowRootElement(element.get(), locators), SeleniumDataConstants.NULL_ELEMENT);
    }

    static Function<Data<SearchContext>, Data<WebElement>> getNestedElement(By locator) {
        final var nameof = "getNestedElement: ";
        return isNotNull(locator) ? (context -> {
            if (isNull(context)) {
                return replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, CoreFormatterConstants.PASSED_DATA_WAS_NULL);
            }

            final var nestedElement = getNestedElementsAmount(locator, 1).apply(context);
            if (isInvalidOrFalse(nestedElement)) {
                return replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Nested Element" + CoreFormatterConstants.WAS_NULL);
            }

            return nestedElement.object.isNotNullAndNonEmpty() ? (
                getElementFromSingle(nestedElement)
            ) : replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Nested Element" + CoreFormatterConstants.WAS_NULL + nestedElement.message);
        }) : context -> replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElements(By locator) {
        return context -> {
            final var nameof = "getNestedElements";
            final var message = SeleniumFormatter.getNestedElementsErrorMessage(locator, context);
            return isBlank(message) ? (
                replaceName(getElementsCore(SearchContextFunctions.getSearchContextOf("Search Context", context), SeleniumLazyLocatorFactory.get(locator)), nameof)
            ) : replaceMessage(SeleniumDataConstants.NULL_LIST, nameof, message);
        };
    }

    static Function<Data<SearchContext>, Data<WebElement>> getNestedElement(LazyLocator locator) {
        return ifDependency("getNestedElement", SeleniumFormatter.isNullLazyDataMessage(locator), getNestedElement(SeleniumUtilities.getLocator(locator).object), SeleniumDataConstants.NULL_ELEMENT);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElements(LazyLocator locator) {
        final var nameof = "getNestedElements";
        if (isNullLazyLocator(locator)) {
            return context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Lazy locator" + CoreFormatterConstants.WAS_NULL);
        }

        final var locatorData = SeleniumUtilities.getLocator(locator);
        return isValidNonFalse(locatorData) ? getNestedElements(locatorData.object) : context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Locator data" + CoreFormatterConstants.WAS_NULL);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElementsAmount(By locator, int count) {
        final var nameof = "getNestedElementsAmount";
        return isNotNull(locator) && BasicPredicates.isNonNegative(count) ? (context -> {
            if (isNull(context)) {
                return replaceMessage(SeleniumDataConstants.NULL_LIST, CoreFormatterConstants.PASSED_DATA_WAS_NULL);
            }

            final var result = getNestedElements(locator).apply(context);
            return getElementsAmountCore(locator, count).apply(result);
        }) : context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Locator was null, or count was wrong. Locator: " + locator + ", count: " + count);
    }

    static Function<Data<SearchContext>, Data<Integer>> getCountOfNestedElements(By locator) {
        return isNotNull(locator) ? (
            context -> isNotNull(context) ? getCountOfElements(getNestedElements(locator).apply(context)) : CoreDataConstants.NO_ELEMENTS_FOUND_DATA_FALSE_OR_NULL
        ) : context -> SeleniumDataConstants.NO_ELEMENTS_FOUND;
    }

    private static <T> Data<T> getShadowNestedCore(WebDriver driver, Function<LazyLocator, Function<Data<SearchContext>, Data<T>>> getter, LazyLocatorList locators, LazyLocator locator, T defaultValue) {
        final var switchResult = switchToDefaultContent().apply(driver);
        if (isInvalidOrFalse(switchResult)) {
            return DataFactoryFunctions.replaceObject(switchResult, defaultValue);
        }

        //TODO valid unwrap chain
        final var shadowRoot = getShadowRootElement(locators).apply(driver);
        if (isInvalidOrFalse(shadowRoot)) {
            return DataFactoryFunctions.replaceObject(shadowRoot, defaultValue);
        }

        final var context = SearchContextFunctions.getSearchContext(shadowRoot.object);
        if (isInvalidOrFalse(context)) {
            return DataFactoryFunctions.replaceObject(context, defaultValue);
        }

        final var contextFunction = getter.apply(locator);
        return contextFunction.apply(context);
    }

    private static <T> Function<WebDriver, Data<T>> getShadowNestedCore(Function<LazyLocator, Function<Data<SearchContext>, Data<T>>> getter, LazyLocatorList locators, LazyLocator locator, T defaultValue) {
        return driver -> getShadowNestedCore(driver, getter, locators, locator, defaultValue);
    }

    private static <T> DriverFunction<T> getShadowNested(Function<LazyLocator, Function<Data<SearchContext>, Data<T>>> getter, LazyLocatorList locators, LazyLocator locator, T defaultValue) {
        final var nameof = "getShadowNested";
        return DriverFunctionFactory.getFunction(ifDependency(
            nameof,
            areNotNull(getter, locators, defaultValue) && locators.isNotNullAndNonEmpty() && SeleniumUtilities.isNotNullLazyLocator(locator),
            getShadowNestedCore(getter, locators, locator, defaultValue),
            DataFactoryFunctions.getWith(defaultValue, false, "There were parameter issues" + CoreFormatterConstants.END_LINE)
        ));
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators, LazyLocator elementLocator) {
        return getShadowNested(Driver::getNestedElement, locators, elementLocator, SeleniumCoreConstants.STOCK_ELEMENT);
    }

    static DriverFunction<WebElementList> getShadowNestedElements(LazyLocatorList locators, LazyLocator elementLocator) {
        return getShadowNested(Driver::getNestedElements, locators, elementLocator, SeleniumCoreConstants.NULL_ELEMENT_LIST);
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators, By locator) {
        return getShadowNestedElement(locators, SeleniumLazyLocatorFactory.get(locator));
    }

    static DriverFunction<WebElementList> getShadowNestedElements(LazyLocatorList locators, By locator) {
        return getShadowNestedElements(locators, SeleniumLazyLocatorFactory.get(locator));
    }

    static <T> Data<Boolean> switchTo(
        TargetLocator locator,
        Function<TargetLocator, T> operation,
        BiFunction<Boolean, SwitchResultMessageData<Void>, String> formatter,
        SwitchResultMessageData<Void> messageData
    ) {
        final var nameof = "switchTo";
        if (isNull(locator)) {
            DataFactoryFunctions.getBoolean(false, nameof, formatter.apply(false, messageData));
        }

        var exception = ExceptionConstants.EXCEPTION;
        try {
            operation.apply(locator);
        } catch (NoSuchFrameException ex) {
            exception = ex;
        }

        var status = ExceptionFunctions.isNonException(exception);
        return DataFactoryFunctions.getBoolean(status, nameof, formatter.apply(status, messageData), exception);
    }

    static <T, U> Data<Boolean> switchTo(
        T target,
        TargetLocator locator,
        boolean guardCondition,
        BiFunction<TargetLocator, T, U> operation,
        BiFunction<Boolean, SwitchResultMessageData<T>, String> formatter,
        SwitchResultMessageData<T> messageData
    ) {
        final var nameof = "switchTo";
        if (CoreUtilities.areAnyNull(target, locator) || CoreUtilities.isFalse(guardCondition)) {
            DataFactoryFunctions.getInvalidBooleanWith(nameof, formatter.apply(false, messageData));
        }

        var exception = ExceptionConstants.EXCEPTION;
        try {
            operation.apply(locator, target);
        } catch (NoSuchFrameException ex) {
            exception = ex;
        }

        var status = ExceptionFunctions.isNonException(exception);
        return DataFactoryFunctions.getBoolean(status, nameof, formatter.apply(status, messageData), exception);
    }

    static <T, U> Data<Boolean> switchTo(T target, TargetLocator locator, BiFunction<TargetLocator, T, U> operation, String type, String nameof) {
        return switchTo(target, locator, true, operation, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<T>(target, type, nameof));
    }

    static <T, U> Function<TargetLocator, Data<Boolean>> switchTo(T target, BiFunction<TargetLocator, T, U> operation, String type, String nameof) {
        return locator -> switchTo(target, locator, operation, type, nameof);
    }

    static Function<TargetLocator, Data<Boolean>> switchToFrame(Data<WebElement> data) {
        final var nameof = "switchToFrame(Data<WebElement> data): ";
        return target -> ExecutionCore.ifData(
            nameof,
            NullableFunctions::isNotNull,
            target,
            switchTo(data.object, TargetLocator::frame, "frame", nameof),
            DataFactoryFunctions.getBoolean(false, nameof, "Couldn't attempt, data was null or false" + CoreFormatterConstants.END_LINE)
        );
    }

    static Function<TargetLocator, Data<Boolean>> switchToFrameSingleList(Data<WebElementList> data) {
        final var nameof = "switchToFrame(Data<WebElement> data): ";
        if (isInvalidOrFalse(data)) {
            return SeleniumDataConstants.SWITCH_TO_NEGATIVE;
        }
        final var list = data.object;
        final var element = DataFactoryFunctions.getWith(list.first(), list.isSingle(), nameof, data.message.message);
        return isValidNonFalse(data) ? switchToFrame(element) : SeleniumDataConstants.SWITCH_TO_NEGATIVE;
    }

    static DriverFunction<Boolean> switchToFrame(DriverFunction<WebElement> data) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isNotNull(data),
            driver -> switchToFrame(data.apply(driver)).apply(driver.switchTo()),
            DataFactoryFunctions.getBoolean(false, nameof, "Data parameter " + CoreFormatterConstants.WAS_NULL)
        );
    }

    static DriverFunction<Boolean> switchToFrame(By locator, Function<By, DriverFunction<WebElement>> getter) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            areNotNull(locator, getter),
            switchToFrame(getter.apply(locator)),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrame(Data<By> locator, Function<By, DriverFunction<WebElement>> getter) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isValidNonFalse(locator) && isNotNull(getter),
            switchToFrame(getter.apply(locator.object)),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrame(By locator) {
        return switchToFrame(locator, Driver::getElement);
    }

    static DriverFunction<Boolean> switchToFrame(LazyLocator locator) {
        return switchToFrame(SeleniumUtilities.getLocator(locator), Driver::getElement);
    }

    static DriverFunction<Boolean> switchToFrame(LazyLocatorList locator) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isNotNull(locator) && locator.isSingle(),
            switchToFrame(SeleniumUtilities.getLocator(locator.first()), Driver::getElement),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrameFromSingle(LazyLocatorList locators) {
        final var nameof = "switchToFrameFromSingle";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.isSingle(),
            switchToFrame(locators.first()),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame. Non-singular list used in function")
        );
    }

    static DriverFunction<Boolean> switchToFrame(int target) {
        return driver -> switchTo(
            target,
            driver.switchTo(),
            BasicPredicates.isNonNegative(target),
            TargetLocator::frame,
            SeleniumFormatter::getSwitchToMessage,
            new SwitchResultMessageData<>(target, "frame", "switchToFrame(int frameLocator): ")
        );
    }

    static DriverFunction<Boolean> switchToWindow(String target) {
        return driver -> switchTo(
            target,
            driver.switchTo(),
            isNotBlank(target),
            TargetLocator::frame,
            SeleniumFormatter::getSwitchToMessage,
            new SwitchResultMessageData<>(target, "window", "switchToWindow(String target): ")
        );
    }

    static DriverFunction<Boolean> switchToParentFrame() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::parentFrame, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<>(null, "parent frame", "switchToParentFrame: "));
    }

    static DriverFunction<Boolean> switchToAlert() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::alert, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<>(null, "alert.", "switchToAlert: "));
    }

    static DriverFunction<Boolean> switchToDefaultContent() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::defaultContent, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<>(null, "default content.", "switchToDefaultContent: "));
    }

    static <T> DriverFunction<T> switchToDefaultContentWith(DriverFunction<T> action) {
        return SeleniumExecutor.execute(switchToDefaultContent(), action);
    }


    static DriverFunction<Boolean> switchToNestedFrame(LazyLocatorList locators) {
        return ifDriver(
            "switchToNestedFrame",
            isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(CoreDataConstants.NULL_BOOLEAN, "Couldn't switch to default content.");
                }

                final var length = locators.size();
                var message = "";
                var index = 0;
                LazyLocator locator;
                Data<?> data;
                for(; index < length; ++index) {
                    locator = locators.get(index);
                    message += index + ".: ";
                    if (isNull(locator)) {
                        message += (SeleniumFormatterConstants.LOCATOR_WAS_NULL);
                        break;
                    }

                    data = switchToFrame(Driver.getElement(locator)).apply(driver);
                    message += data.message;
                    if (isValidNonFalse(data)) {
                        break;
                    }
                }

                return DataFactoryFunctions.getBoolean(index == length, message);
            },
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, "Locators were empty.")
        );
    }

    static DriverFunction<WebElementList> getFrameNestedElements(LazyLocatorList locators) {
        return ifDriver(
            "getFrameNestedElements",
            isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (locators.isSingle()) {
                    return Driver.getElements(locators.first()).apply(driver);
                }

                final var data = switchToNestedFrame(locators.initials()).apply(driver);
                return isValidNonFalse(data) ? Driver.getElements(locators.last()).apply(driver) : SeleniumDataConstants.NULL_LIST;
            },
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElement> getFrameNestedElement(LazyLocatorList locators, Function<By, DriverFunction<WebElement>> getter) {
        return ifDriver(
            "getFrameNestedElement",
            areNotNull(getter, locators) && locators.isMany() && !locators.hasMoreThan(2),
            driver -> {
                final var frameData = locators.first();
                if (isNullLazyLocator(frameData)) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var frameLocator = SeleniumUtilities.getLocator(frameData).object;
                if (isInvalidOrFalse(getter.apply(frameLocator).apply(driver))) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var elementData = locators.last();
                if (isNullLazyLocator(elementData)) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var elementLocator = SeleniumUtilities.getLocator(elementData).object;
                if (isInvalidOrFalse(getter.apply(frameLocator).apply(driver))) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var data = switchToFrame(getter.apply(frameLocator).apply(driver)).apply(driver.switchTo());
                return isValidNonFalse(data) ? getter.apply(elementLocator).apply(driver) : SeleniumDataConstants.NULL_ELEMENT;
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static <T, U> DriverFunction<U> getWithLocator(GetWithDriverData<DecoratedList<T>, T, T, U> data) {
        return DriverFunctionFactory.getFunction(ifDependency("getWithLocator", areNotNull(data.locatorGetter, data.getter), data.getter.apply(data.locatorGetter.apply(data.locators)), data.guardData));
    }

    static <T> DriverFunction<T> getWithLazyLocator(GetWithDriverData<LazyLocatorList, LazyLocator, By, T> data) {
        return DriverFunctionFactory.getFunction(ifDependency("getWithLazyLocator", areNotNull(data.locatorGetter, data.getter), data.getter.apply(SeleniumUtilities.getLocator(data.locatorGetter.apply(data.locators)).object), data.guardData));
    }

    static <WE, BY, BYY extends By, W extends DecoratedList<BY>> DriverFunction<WE> getFromSingle(
        Function<GetWithDriverData<W, BY, BYY, WE>, DriverFunction<WE>> getter,
        GetWithDriverData<W, BY, BYY, WE> data,
        String nameof
    ) {
        return ifDriver(nameof, data.locators.isSingle(), getter.apply(data), data.guardData);
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators) {
        if (isNull(locators) || CoreUtilities.isFalse(locators.hasAtleast(2))) {
            return DriverFunctionFactory.get(DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Lazy Locator list doesn't have enough items" + CoreFormatterConstants.END_LINE));
        }

        final var element = locators.last();
        final var nests = locators.initials();
        return SeleniumUtilities.isNotNullLazyLocator(element) && isNotNull(nests) ? (
            getShadowNestedElement(nests, element)
        ) : DriverFunctionFactory.get(DataFactoryFunctions.prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Lazy locator item issues" + CoreFormatterConstants.END_LINE));
    }

    static DriverFunction<WebElement> getElementFromSingle(LazyLocatorList locator) {
        return getFromSingle(
            Driver::getWithLazyLocator,
            new GetWithDriverData<LazyLocatorList, LazyLocator, By, WebElement>(locator, LazyLocatorList::first, Driver::getElement, SeleniumDataConstants.NULL_ELEMENT),
            "getElementFromSingle"
        );
    }

    static DriverFunction<WebElement> getRootElementFromSingle(LazyLocatorList locator) {
        return getFromSingle(
            Driver::getWithLazyLocator,
            new GetWithDriverData<LazyLocatorList, LazyLocator, By, WebElement>(locator, LazyLocatorList::first, Driver::getShadowRootElement, SeleniumDataConstants.NULL_ELEMENT),
            "getRootElementFromSingle"
        );
    }

    static DriverFunction<WebElement> getNestedElement(LazyLocatorList locators) {
        final var nameof = "getNestedElement";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.hasAtleast(2) && SeleniumUtilities.isNotNullLazyLocator(locators.first()),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, nameof, "Driver was null or couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                var locator = locators.first();
                var data = Driver.getElement(locator).apply(driver);
                if (SeleniumUtilities.isNullWebElement(data)) {
                    return DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, nameof, DataFunctions.getFormattedMessage(data));
                }

                final var sublist = locators.tail();
                final var length = sublist.size();
                var index = 0;
                for(; index < length; ++index) {
                    locator = sublist.get(index);
                    if (isNullLazyLocator(locator)) {
                        break;
                    }

                    data = getNestedElement(locator).apply(SearchContextFunctions.getSearchContext(data.object));
                    if (SeleniumUtilities.isNullWebElement(data)) {
                        break;
                    }
                }

                return (index == length) ? data : DataFactoryFunctions.appendMessage(SeleniumDataConstants.NULL_ELEMENT, nameof);
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static <T> DriverFunction<T> getFrameNested(Function<LazyLocator, DriverFunction<T>> getter, LazyLocatorList locators, Data<T> defaultValue, String nameof) {
        return ifDriver(
            nameof,
            areNotNull(getter, locators, defaultValue, nameof) && locators.hasAtleast(2) && SeleniumUtilities.isNotNullLazyLocator(locators.first()),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(defaultValue, nameof, "Driver was null or couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                final var function = ElementFinderConstants.frameNestedStrategyMap.get("" + locators.hasMoreThan(2));
                if (isNull(function)) {
                    return replaceMessage(defaultValue, nameof, "Function from Frame nested Strategy map " + CoreFormatterConstants.WAS_NULL);
                }

                final var data = function.apply(locators.initials()).apply(driver);
                if (isInvalidOrFalse(data)) {
                    return replaceMessage(defaultValue, nameof, "Couldn't switch into frame. By list length: " + locators.size());
                }

                final var locator = locators.last();
                return isNotNull(locator) ? getter.apply(locator).apply(driver) : replaceMessage(defaultValue, "Locator was null" + CoreFormatterConstants.END_LINE);
            },
            replaceMessage(defaultValue, nameof, "Couldn't switch into frame. Guard.")
        );
    }

    static DriverFunction<WebElement> getFrameNestedElement(LazyLocatorList locators) {
        return getFrameNested(Driver::getElement, locators, SeleniumDataConstants.NULL_ELEMENT, "getFrameNestedElement");
    }

    static DriverFunction<WebElementList> getFrameNestedElementsFromLast(LazyLocatorList locators) {
        return getFrameNested(Driver::getElements, locators, SeleniumDataConstants.NULL_LIST, "getFrameNestedElementsFromLast");
    }

    static DriverFunction<WebElementList> getNestedElementsFromLast(LazyLocatorList locators) {
        return ifDriver(
            "getNestedElementsFromLast",
            isNotNull(locators) && locators.hasAtleast(2) && SeleniumUtilities.isNotNullLazyLocator(locators.first()), //TODO: Move to formatter, and do blank check instead
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                final var function = ElementFinderConstants.frameAmountStrategyMap.get("" + locators.hasMoreThan(2));
                final var element = function.apply(locators.initials()).apply(driver);
                if (isInvalidOrFalse(element)) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Failed, element issue - sublist length(" + locators.initials().size() + "): " + element.message);
                }

                final var locator = locators.last();
                if (isNullLazyLocator(locator)) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Locator was null" + CoreFormatterConstants.END_LINE);
                }

                final var data = getNestedElements(locator).apply(SearchContextFunctions.getSearchContext(element.object));
                final var nested = locators.hasAtleast(2);
                return isValidNonFalse(data) ? data : DataFactoryFunctions.prependMessage(data, (nested ? "Nested " : "") + "Elements weren't found by locator: " + locator);
            },
            replaceMessage(SeleniumDataConstants.NULL_LIST, "Locators list was null or empty.")
        );
    }

    static DriverFunction<WebElementList> getShadowNestedElementsFromLast(LazyLocatorList locators) {
        return ifDriver(
            "getShadowNestedElementsFromLast",
            isNotNull(locators) && locators.isNotNullAndNonEmpty() && SeleniumUtilities.isNotNullLazyLocator(locators.last()),
            getShadowNestedElements(locators.initials(), locators.last()),
            replaceMessage(SeleniumDataConstants.NULL_LIST, "Locators were null" + CoreFormatterConstants.END_LINE)
        );
    }


    static <LazyElement extends AbstractLazyResult<LazyFilteredElementParameters>> DriverFunction<ExternalElementData> getLazyElementByExternal(LazyElement element, ExternalSelectorData<WebDriver> externalData, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        //TODO: Validate all the parameters and provide overloads for defaults.
        final var nameof = "getLazyElementByExternal";
        final var defaultValue = SeleniumDataConstants.NULL_EXTERNAL_ELEMENT;
        return ifDriver(
            nameof,
            FrameworkCoreFormatter.getExternalSelectorDataErrorMessage(element, externalData, nameof),
            driver -> {
                LazyLocator locator;
                var selector = externalData.defaultSelector;
                var parameterKey = "";
                var selectorType = externalData.selectorType;
                var currentElement = SeleniumDataConstants.NULL_ELEMENT;

                final var failedSelectors = DecoratedListFactory.getWith(String.class);
                final var length = externalData.limit;
                var index = 0;
                var message = replaceMessage(CoreDataConstants.NULL_STRING, nameof, "");
                var lep = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, 0, SeleniumLazyLocatorFactory.getWithDefaults());
                var getSelector = externalData.getSelector;
                for(; index < length; ++index) {
                    switchToDefaultContent().apply(driver);
                    selector = getSelector.apply(externalData.preferredProperties, failedSelectors.list).apply(driver);
                    if (isInvalidOrFalse(selector)) {
                        continue;
                    }

                    if (isBlank(selector.object)) {
                        DataFactoryFunctions.appendMessage(message, "Empty selector returned, attempt: " + index + CoreFormatterConstants.END_LINE);
                        continue;
                    }

                    locator = SeleniumLazyLocatorFactory.get(selector.object, selectorType);
                    parameterKey = FrameworkCoreFormatter.getUniqueGeneratedName(selectorType, SeleniumCoreConstants.ATOMIC_COUNT);
                    lep = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, 0, locator);
                    currentElement = ElementFilterFunctions.getElement(lep.LAZY_LOCATORS, ElementFinderConstants.singleGetterMap, SingleGetter.DEFAULT).apply(driver);
                    if (isNullWebElement(currentElement)) {
                        break;
                    }

                    failedSelectors.addNullSafe(selector.object);
                }

                element.parameters.putIfAbsent(parameterKey, lep);
                final var update = ElementRepository.updateTypeKeys(element.name, lep.LAZY_LOCATORS, typeKeys, parameterKey);
                return isNotNullWebElement(currentElement) ? (
                    DataFactoryFunctions.getWith(new ExternalElementData(typeKeys, currentElement), true, nameof, "External function element" + CoreFormatterConstants.END_LINE)
                ) : replaceMessage(defaultValue, nameof, "All(\"" + length + "\") approaches were tried" + CoreFormatterConstants.END_LINE + DataFunctions.getFormattedMessage(currentElement));
            },
            defaultValue
        );
    }

    private static <T> Data<CachedLookupKeysData> getNextCachedKeyCore(DecoratedList<String> selectorTypes, CachedLookupKeysData data) {
        final var selectorKeys = RepositoryConstants.CACHED_ELEMENTS.get(data.name).typeKeys;
        var selectorType = isNotBlank(data.entryName) ? data.entryName : selectorTypes.first();
        var selectorList = selectorKeys.get(selectorType);
        var index = data.index;
        var key = "";
        if (selectorList.hasIndex(index)) {
            key = selectorList.get(index).selectorKey;
        } else {
            index = 0;
            final var selectorLength = selectorTypes.size();
            var selectorIndex = selectorTypes.indexOf(selectorType) + 1;
            for(; isBlank(key) && (selectorIndex < selectorLength); ++selectorIndex) {
                selectorList = selectorKeys.get(selectorTypes.get(selectorIndex));
                if (selectorList.isNullOrEmpty()) {
                    continue;
                }

                key = selectorList.get(index).selectorKey;
            }
        }

        final var status = isNotBlank(key);
        return DataFactoryFunctions.getWith(
            new CachedLookupKeysData(data.name, selectorType, key, index),
            status,
            "getNextCachedKey",
            (status ? "Found" : "Didn't find") + " next lookup key(\"" + key + "\")"
        );
    }

    private static <T> Function<CachedLookupKeysData, Data<CachedLookupKeysData>> getNextCachedKeyCore(DecoratedList<String> selectorTypes) {
        return data -> getNextCachedKeyCore(selectorTypes, data);
    }

    static <T> Function<CachedLookupKeysData, Data<CachedLookupKeysData>> getNextCachedKey(DecoratedList<String> selectorTypes) {
        final var errorMessage = CoreFormatter.isNullOrEmptyListMessageWithName(selectorTypes, "Selector Types list");
        return isBlank(errorMessage) ? getNextCachedKeyCore(selectorTypes) : index -> replaceMessage(SeleniumDataConstants.NULL_CACHED_DATA, "getNextCachedKey", errorMessage);
    }

    private static Data<CachedLookupKeysData> getNextKeyCore(DecoratedList<String> elementKeys, CachedLookupKeysData data) {
        final var nameof = "getNextKey";
        final var status = isBlank(CoreFormatter.isNullOrEmptyListMessageWithName(elementKeys, "Element keys list")) && isNotNull(data);
        return status ? DataFactoryFunctions.getWith(new CachedLookupKeysData(data.name, "", elementKeys.get(data.index), 0), true, nameof, "Had key.") : replaceName(SeleniumDataConstants.NULL_CACHED_DATA, nameof);
    }

    private static Function<CachedLookupKeysData, Data<CachedLookupKeysData>> getNextKeyCore(DecoratedList<String> elementKeys) {
        return data -> getNextKeyCore(elementKeys, data);
    }

    static Function<CachedLookupKeysData, Data<CachedLookupKeysData>> getNextKey(DecoratedList<String> elementKeys) {
        return data -> isNotNull(elementKeys) && isNotNull(data) ? getNextKeyCore(elementKeys).apply(data) : replaceMessage(SeleniumDataConstants.NULL_CACHED_DATA, "getNextKey", "Index or key issue" + CoreFormatterConstants.END_LINE);
    }

    static Data<WebElement> getLazyElementCore(WebDriver driver, LazyElementWithOptionsData data, GetLazyElementData<WebElement, WebElementList> defaults) {
        final var nameof = "getLazyElementCore";
        final var dataElement = data.element;
        final var name = dataElement.name;
        final var getResult = ElementRepository.getIfContains(name);
        final var isCached = getResult.status;
        final var parameterMap = (isCached ? getResult.object.element : dataElement).parameters;
        final var keyGetter = isCached ? getNextCachedKey(data.getOrder) : getNextKey(DecoratedListFactory.getWith(parameterMap.keySet()));
        final var typeKeys = isCached ? getResult.object.typeKeys : ElementRepository.getInitializedTypeKeysMap();
        final var exitCondition = defaults.exitCondition;
        var message = new StringBuilder();
        var parameterIndex = 0;
        var index = 0;
        var switchData = CoreDataConstants.NULL_BOOLEAN;
        var current = defaults.defaultValue;
        final var length = data.internalData.limit;
        var cacheKeyData = new CachedLookupKeysData(name, "", "", 0);
        while (exitCondition.apply(current, index++, length)) {
            switchData = switchToDefaultContent().apply(driver);
            if (isInvalidOrFalse(switchData)) {
                return replaceMessage(current, nameof, DataFunctions.getFormattedMessage(switchData));
            }

            var keyData = keyGetter.apply(cacheKeyData);
            if (isInvalidOrFalse(keyData)) {
                return replaceMessage(current, nameof, "Parameter key wasn't found in " + (isCached ? "cached" : "") + " keys" + CoreFormatterConstants.END_LINE);
            }

            final var key = keyData.object.strategy;
            var parameters = parameterMap.get(key);
            if (isNull(parameters) || parameters.LAZY_LOCATORS.isNullOrEmpty()) {
                continue;
            }

            var locators = parameters.LAZY_LOCATORS;
            var update = ElementRepository.updateTypeKeys(name, locators, typeKeys, key);
            if (isInvalidOrFalse(update)) {
                continue;
            }

            current = defaults.getter.apply(parameters.ELEMENT_FILTER_DATA, locators, parameters.GETTER).apply(driver);
            message.append(DataFunctions.getFormattedMessage(current));
            message.append(DataFunctions.getFormattedMessage(Adjuster.adjustProbability(parameters, typeKeys, key, isValidNonFalse(current), data.probabilityData)));
            cacheKeyData = new CachedLookupKeysData(name, keyData.object.entryName, keyData.object.strategy, isCached ? keyData.object.index : ++parameterIndex);
        }

        if (defaults.invalidator.test(current)) {
            current = defaults.defaultValue;
        }

        final var externalData = data.externalData;
        return defaults.cacheFunction.apply(
            dataElement,
            DataFactoryFunctions.getWith(new ExternalElementData(typeKeys, current), isValidNonFalse(current), message.toString()),
            isBlank(FrameworkCoreFormatter.getExternalSelectorDataMessage(externalData)) ? getLazyElementByExternal(dataElement, externalData, typeKeys).apply(driver) : SeleniumDataConstants.NULL_EXTERNAL_ELEMENT
        );
    }

    private static Function<WebDriver, Data<WebElement>> getLazyElementCore(LazyElementWithOptionsData data) {
        return driver -> getLazyElementCore(driver, data, GetLazyElementConstants.REGULAR_LAZY_CONSTANTS);
    }

    static <T> DriverFunction<WebElement> getLazyElement(LazyElementWithOptionsData data) {
        final var nameof = "getLazyElement";
        return ifDriver(
            nameof,
            FrameworkCoreFormatter.getLazyResultWithOptionsMessage(data, nameof),
            DriverFunctionFactory.getFunction(getLazyElementCore(data)),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder, ProbabilityData probabilityData) {
        final var data = new LazyElementWithOptionsData(element, internalData, externalData, getOrder, probabilityData);
        return getLazyElement(data);
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        final var data = new LazyElementWithOptionsData(element, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA);
        return getLazyElement(data);
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        final var data = new LazyElementWithOptionsData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, probabilityData);
        return getLazyElement(data);
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData) {
        final var data = new LazyElementWithOptionsData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, AdjusterConstants.PROBABILITY_DATA);
        return getLazyElement(data);
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element) {
        return getLazyElement(LazyElementWithOptionsDataFactory.getWithSpecificLazyElement(element));
    }

    private static Data<Boolean> navigateCore(WebDriver driver, String url, String query) {
        final var target = URLUtilities.handle(url, query);
        var exception = ExceptionConstants
                .EXCEPTION;
        try {
            driver.get(target);
        } catch (NullPointerException ex) {
            exception = ex;
        }

        final var status = ExceptionFunctions.isNonException(exception);
        final var message = (
            status ? "Driver navigated successfully to \"" + url + "\"" + CoreFormatterConstants.END_LINE
                    : "Exception occurred while navigating to \"" + url + "\". Exception:" + exception.getClass() + " Message: " +  exception.getMessage()
        );

        return DataFactoryFunctions.getBoolean(status, message, exception);
    }

    private static DriverFunction<Boolean> navigateCore(String url, String query) {
        return driver -> navigateCore(driver, url, query);
    }

    static DriverFunction<Boolean> quitDriver() {
        return QuitFunctionConstants.QUIT_DRIVER;
    }

    static DriverFunction<Boolean> navigate(String url, String query) {
        return ExecutionCore.ifDriver("navigate", isBlankMessageWithName(url, "Url") + isNullMessageWithName(query, "Query Fragment"), navigateCore(url, query), SeleniumDataConstants.DRIVER_WAS_NULL);
    }

    static DriverFunction<Boolean> navigate(String url) {
        return navigate(url, CoreFormatterConstants.EMPTY);
    }
}
