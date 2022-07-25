package com.neathorium.thorium.framework.selenium.namespaces.driver.invoke;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.DataFunctions;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.abstracts.reflection.BaseInvokerDefaultsData;
import com.neathorium.thorium.framework.selenium.constants.MethodDefaults;
import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumInvokeConstants;
import com.neathorium.thorium.framework.selenium.constants.SeleniumInvokeFunctionDefaults;
import com.neathorium.thorium.framework.selenium.constants.SeleniumMethodDefaults;
import com.neathorium.thorium.framework.selenium.implementations.reflection.message.ParameterizedMessageData;
import com.neathorium.thorium.framework.selenium.implementations.reflection.message.RegularMessageData;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.validators.InvokeCoreValidator;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.framework.selenium.records.reflection.InvokerParameterizedParametersFieldData;
import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeCommonMessageParametersData;
import com.neathorium.thorium.framework.selenium.repositories.method.namespaces.MethodRepositoryFunctions;
import com.neathorium.thorium.framework.selenium.repositories.method.namespaces.validators.MethodParametersDataValidators;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.reflection.InvokeMethodData;
import com.neathorium.thorium.java.extensions.namespaces.ArrayFunctions;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ElementInvokeFunctions {
    private static <HandlerType, ParameterType, ReturnType> Data<ReturnType> invoke(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        ParameterType parameter
    ) {
        final var nameof = "invokeCore";
        final var castData = defaults.CAST_DATA;
        final var methodData = data.OBJECT();
        final var method = methodData.METHOD();
        final var function = castData.CASTER.compose(defaults.CONSTRUCTOR.apply(handler).apply(method));
        final var result = defaults.CAST_HANDLER.apply(new HandleResultData<>(function, parameter, castData.DEFAULT_VALUE));

        final var status = DataPredicates.isValidNonFalse(result);
        final var message = (BooleanUtilities.isFalse(status)) ? (
            messageHandler
                .apply(new InvokeCommonMessageParametersData(DataFunctions.getFormattedMessage(data), methodData.RETURN_TYPE(), methodData.METHOD_PARAMETER_TYPES()))
                .apply(result.EXCEPTION())
        ) : DataFunctions.getFormattedMessage(result);

        return DataFactoryFunctions.getWith(result.OBJECT(), status, nameof, message, result.EXCEPTION());
    }

    private static <HandlerType, ParameterType, ReturnType> Data<ReturnType> invoke(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        Data<ParameterType> parameter
    ) {
        return invoke(data, defaults, messageHandler, handler, parameter.OBJECT());
    }

    private static <ParameterType, HandlerType, ReturnType> Function<Data<ParameterType>, Data<ReturnType>> invoke(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler
    ) {
        return parameter -> invoke(data, defaults, messageHandler, handler, parameter);
    }

    private static <ParameterType, HandlerType, ReturnType> Data<ReturnType> invoke(
        String name,
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        ParameterType parameter
    ) {
        final var nameof = isNotBlank(name) ? name : "invokeCore";
        final var errorMessage = InvokeCoreValidator.isInvalidInvokeCoreParametersMessage(data, defaults, messageHandler, handler, parameter);
        return isBlank(errorMessage) ? (
            DataFactoryFunctions.replaceName(invoke(data, defaults, messageHandler, handler, parameter), nameof)
        ) : DataFactoryFunctions.getWith(null, false, nameof, errorMessage);
    }

    private static <ParameterType, HandlerType, ReturnType> DriverFunction<ReturnType> invoke(
        String name,
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        DriverFunction<ParameterType> getter
    ) {
        final var nameof = isNotBlank(name) ? name : "invokeCore";
        final var errorMessage = InvokeCoreValidator.isInvalidInvokeCoreParametersMessage(data, defaults, messageHandler, handler, getter);
        final var negative = DataFactoryFunctions.getWith((ReturnType)null, false, errorMessage);
        return ExecutionCore.ifDriver(nameof, errorMessage, DataExecutionFunctions.validChain(getter, invoke(data, defaults, messageHandler, handler), negative)::apply, negative);
    }


    private static Data<WebElement> invokeGetElement(Data<SearchContext> context, By locator) {
        if (DataPredicates.isInvalidOrFalse(context) || NullablePredicates.isNull(locator)) {
            return SeleniumDataConstants.NULL_ELEMENT;
        }

        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, SeleniumMethodDefaults.FIND_ELEMENT);
        final var handler = new InvokerParameterizedParametersFieldData<>(ArrayFunctions.toSingleElementArray(locator), SeleniumInvokeFunctionDefaults.SEARCH_CONTEXT_SINGLE_PARAMETER.validator, SeleniumInvokeFunctionDefaults.SEARCH_CONTEXT_SINGLE_PARAMETER.handler);
        final var messageHandler = new ParameterizedMessageData(locator.toString(), SeleniumFormatter::getInvokeMethodParameterizedMessageFunction);
        return invoke("invokeGetElement", methodData, SeleniumInvokeFunctionDefaults.SEARCH_CONTEXT_PARAMETERS, messageHandler, handler, context.OBJECT());
    }

    static Function<Data<SearchContext>, Data<WebElement>> invokeGetElement(By locator) {
        final var message = CoreFormatter.isNullMessageWithName(locator, "Locator");
        return isBlank(message) ? (
                context -> invokeGetElement(context, locator)
        ) : context -> DataFactoryFunctions.getInvalidWith(SeleniumCoreConstants.STOCK_ELEMENT, "invokeGetElement", message);
    }

    private static DriverFunction<Void> invokeElementVoidMethod(String name, LazyElement element, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementVoidMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWith(null, nameof, errorMessage);
        }

        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(SeleniumFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invoke(nameof, methodData, SeleniumInvokeFunctionDefaults.VOID_REGULAR, messageHandler, InvokerFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.METHOD_NAME() + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<Boolean> invokeElementBooleanMethod(LazyElement element, InvokeMethodData invokeData) {
        final var localName = invokeData.nameof;
        final var parameterData = invokeData.parametersData;
        final var nameof = isNotBlank(localName) ? localName : "invokeElementBooleanMethod";

        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWith(null, nameof, errorMessage);
        }

        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(SeleniumFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invoke(nameof, methodData, SeleniumInvokeFunctionDefaults.BOOLEAN_REGULAR, messageHandler, InvokerFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.METHOD_NAME() + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<String> invokeElementStringMethod(String name, LazyElement element, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementStringMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWith(null, nameof, errorMessage);
        }

        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(SeleniumFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invoke(nameof, methodData, SeleniumInvokeFunctionDefaults.STRING_REGULAR, messageHandler, InvokerFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.METHOD_NAME() + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<String> invokeElementStringMethod(String name, LazyElement element, String parameter, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementStringMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData) + isBlankMessageWithName(parameter, "Execution parameter value");
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWith(null, nameof, errorMessage);
        }

        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var handler = new InvokerParameterizedParametersFieldData<>(ArrayFunctions.toSingleElementArray(parameter, StringUtils::isNotBlank), SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER.validator, SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER.handler);
        final var messageHandler = new ParameterizedMessageData(parameter, SeleniumFormatter::getInvokeMethodParameterizedMessageFunction);
        final var result = invoke(nameof, methodData, SeleniumInvokeFunctionDefaults.STRING_PARAMETERS, messageHandler, handler, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.METHOD_NAME() + CoreFormatterConstants.COLON_SPACE);
    }

    static DriverFunction<Boolean> isDisplayed(LazyElement element) {
        return invokeElementBooleanMethod(element, SeleniumInvokeConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> isEnabled(LazyElement element) {
        return invokeElementBooleanMethod(element, SeleniumInvokeConstants.ENABLED);
    }

    static DriverFunction<Boolean> isSelected(LazyElement element) {
        return invokeElementBooleanMethod(element, SeleniumInvokeConstants.SELECTED);
    }

    static DriverFunction<String> getText(LazyElement element) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_TEXT, element, MethodDefaults.GET_TEXT);
    }

    static DriverFunction<String> getTagname(LazyElement element) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_TAGNAME, element, MethodDefaults.GET_TAG_NAME);
    }

    static DriverFunction<String> getAttribute(LazyElement element, String attribute) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_ATTRIBUTE, element, attribute, MethodDefaults.GET_ATTRIBUTE);
    }

    static DriverFunction<String> getCssValue(LazyElement element, String cssValue) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_CSS_VALUE, element, cssValue, MethodDefaults.GET_CSS_VALUE);
    }

    static DriverFunction<Boolean> isClickable(LazyElement element) {
        return ExecutionCore.ifDriver(
            SeleniumInvokeConstants.ELEMENT_CLICKABLE,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            SeleniumExecutor.execute(CoreFormatter::getExecutionEndMessageAggregate, isDisplayed(element), isEnabled(element)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Void> click(LazyElement element) {
        return invokeElementVoidMethod(SeleniumInvokeConstants.CLICK, element, MethodDefaults.CLICK);
    }

    static DriverFunction<Void> clear(LazyElement element) {
        return invokeElementVoidMethod(SeleniumInvokeConstants.CLEAR, element, MethodDefaults.CLEAR);
    }

    static DriverFunction<Void> sendKeys(LazyElement element, String parameter) {
        final var nameof = "sendKeys";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isBlankMessageWithName(parameter, "Send keys value");
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWith(null, nameof, errorMessage);
        }

        final var methodParameterData = MethodDefaults.SEND_KEYS;
        final var methodData = MethodRepositoryFunctions.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, methodParameterData);
        final var handler = new InvokerParameterizedParametersFieldData<>(ArrayFunctions.toSingleElementArray(new CharSequence[]{parameter}, NullablePredicates::isNotNull), SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER.validator, SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER.handler);
        final var messageHandler = new ParameterizedMessageData(parameter, SeleniumFormatter::getInvokeMethodParameterizedMessageFunction);
        final var result = invoke(nameof, methodData, SeleniumInvokeFunctionDefaults.VOID_PARAMETERS, messageHandler, handler, element.get());
        return DriverFunctionFactory.prependMessage(result, methodParameterData.METHOD_NAME() + CoreFormatterConstants.COLON_SPACE);
    }
}
