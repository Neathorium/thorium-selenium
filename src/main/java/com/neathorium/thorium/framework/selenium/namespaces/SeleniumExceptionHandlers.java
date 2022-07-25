package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.exceptions.constants.ExceptionConstants;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.framework.selenium.constants.SeleniumExceptionHandlersConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.validators.HandlerResultDataValidator;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.framework.selenium.method.exceptions.MethodInvokeException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumExceptionHandlers {
    static <CastParameterType, ReturnType> Data<ReturnType> invokeHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.INVOKE_HANDLER;
        final var defaultValue = data.DEFAULT_VALUE();
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
        }

        var exception = ExceptionConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.CASTER().apply(data.PARAMETER());
        } catch (
            IllegalArgumentException |
            NoSuchElementException |
            StaleElementReferenceException |
            MethodInvokeException ex
        ) {
            exception = ex;
        }

        final var status = ExceptionFunctions.isNonException(exception);
        final var message = status ? CoreFormatterConstants.INVOCATION_SUCCESSFUL : CoreFormatterConstants.INVOCATION_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> findElementsHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.FIND_ELEMENTS_HANDLER;
        final var defaultValue = data.DEFAULT_VALUE();
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
        }

        var exception = ExceptionConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.CASTER().apply(data.PARAMETER());
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = ExceptionFunctions.isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.FIND_ELEMENTS_SUCCESSFUL : SeleniumFormatterConstants.FIND_ELEMENTS_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> quitHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.QUIT_HANDLER;
        final var defaultValue = data.DEFAULT_VALUE();
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
        }

        var exception = ExceptionConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.CASTER().apply(data.PARAMETER());
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = ExceptionFunctions.isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.QUIT_DRIVER_SUCCESSFUL : SeleniumFormatterConstants.QUIT_DRIVER_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }
}
