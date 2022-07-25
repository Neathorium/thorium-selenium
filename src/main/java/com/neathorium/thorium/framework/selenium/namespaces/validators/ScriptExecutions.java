package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.framework.selenium.abstracts.reflection.BaseInvokerDefaultsData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptHandlerFunction;
import com.neathorium.thorium.framework.selenium.records.reflection.InvokerParameterizedParametersFieldData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorParametersFieldData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecutorResultFunctionsData;
import com.neathorium.thorium.framework.selenium.records.scripter.ScriptParametersData;
import com.neathorium.thorium.core.constants.CastDataConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Objects;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ScriptExecutions {
    static boolean isValidExecutorParametersData(ExecutorParametersFieldData data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.handler, data.parameters, data.validator);
    }

    static <T> boolean isValidInvokerParameterizedData(InvokerParameterizedParametersFieldData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.HANDLER(), data.PARAMETERS(), data.VALIDATOR());
    }

    static <T> boolean isValidExecutorRegularData(ScriptHandlerFunction handler) {
        return NullablePredicates.isNotNull(handler);
    }

    static <T> boolean isValidScriptParametersData(ScriptParametersData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.converter, data.parameters, data.validator);
    }

    static <T> boolean isValidCastData(WrappedCastData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.CASTER, data.DEFAULT_VALUE);
    }

    static <T> String isInvalidCastDataMessage(BasicCastData<T> data) {
        final var baseName = "Basic Cast Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(data.CASTER, baseName + "Caster") +
                CoreFormatter.isNullMessageWithName(data.DEFAULT_VALUE, baseName + "Default value")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidCastDataMessage: ", message);
    }

    static <T> String isInvalidVoidCastDataMessage(BasicCastData<T> data) {
        final var baseName = "Basic Cast Data(Void)";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(data.CASTER, baseName + "Caster");
        }

        return getNamedErrorMessageOrEmpty("isInvalidCastDataMessage: ", message);
    }

    static <T, U, V> boolean isValidExecutorResultFunctionsData(ExecutorResultFunctionsData<T, U, V> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.castHandler, data.messageHandler);
    }

    static <T, U, V, W> boolean isValidConstructorData(ExecutorData<T, U, V, W> data) {
        final var dataNotNull = NullablePredicates.isNotNull(data);
        if (BooleanUtilities.isFalse(dataNotNull)) {
            return false;
        }

        return (
            NullablePredicates.areNotNull(data.CONSTRUCTOR, data.GETTER, data.GUARD) &&
            isValidCastData(data.CAST_DATA) &&
            isValidExecutorResultFunctionsData(data.RESULT_HANDLERS)
        );
    }


    static <T, U, V> String isInvalidInvokerDefaultsMessage(BaseInvokerDefaultsData<T, U, V> data) {
        final var baseName = "Invoker Defaults Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            final var castMessage = Objects.equals(CastDataConstants.VOID, data.CAST_DATA) ? isInvalidVoidCastDataMessage(data.CAST_DATA) : isInvalidCastDataMessage(data.CAST_DATA);
            message += (
                CoreFormatter.isNullMessageWithName(data.CONSTRUCTOR, baseName + " Constructor") +
                castMessage +
                CoreFormatter.isNullMessageWithName(data.GUARD, baseName + " Guard")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidInvokerDefaultsMessage: ", message);
    }



}
