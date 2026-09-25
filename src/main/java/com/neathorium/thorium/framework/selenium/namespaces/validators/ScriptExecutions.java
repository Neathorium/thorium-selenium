package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.framework.selenium.interfaces.BaseFunctionalData;
import com.neathorium.thorium.framework.selenium.interfaces.IBaseInvokerDefaults;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.framework.selenium.records.reflection.InvokerParameterizedParametersFieldData;
import com.neathorium.thorium.framework.selenium.records.scripter.ExecuteParameterizedData;
import com.neathorium.thorium.framework.selenium.interfaces.scripter.IExecutorResultFunctionsData;
import com.neathorium.thorium.framework.selenium.records.scripter.ScriptParametersData;
import com.neathorium.thorium.core.constants.CastDataConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.core.records.caster.WrappedCastData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Function;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;

public interface ScriptExecutions {
    static boolean isValidExecutorParametersData(ExecuteParameterizedData data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.HANDLER(), data.PARAMETERS());
    }

    static <T> boolean isValidInvokerParameterizedData(InvokerParameterizedParametersFieldData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.HANDLER(), data.PARAMETERS(), data.VALIDATOR());
    }

    static <T> boolean isValidExecutorRegularData(ScriptFunction<Function<String, Object>> handler) {
        return NullablePredicates.isNotNull(handler);
    }

    static <T> boolean isValidScriptParametersData(ScriptParametersData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.CONVERTER(), data.PARAMETERS(), data.VALIDATOR());
    }

    static <T> boolean isValidCastData(WrappedCastData<T> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.CASTER, data.DEFAULT_VALUE);
    }

    static <T> String isInvalidCastDataMessage(BasicCastData<T> data) {
        final var baseName = "Basic Cast Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (StringUtils.isBlank(message)) {
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
        if (StringUtils.isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(data.CASTER, baseName + "Caster");
        }

        return getNamedErrorMessageOrEmpty("isInvalidCastDataMessage: ", message);
    }

    static boolean isValidExecutorResultFunctionsData(IExecutorResultFunctionsData<?, ?, ?> data) {
        return NullablePredicates.isNotNull(data) && NullablePredicates.areNotNull(data.CAST_HANDLER(), data.MESSAGE_HANDLER());
    }

    static boolean isValidConstructorData(BaseFunctionalData<?, ?, ?, ?, ?> data) {
        final var dataNotNull = NullablePredicates.isNotNull(data);
        if (BooleanUtilities.isFalse(dataNotNull)) {
            return false;
        }

        return (
            NullablePredicates.areNotNull(data.GETTER(), data.GUARD()) &&
            isValidCastData(data.CAST_DATA()) &&
            isValidExecutorResultFunctionsData(data.RESULT_HANDLER())
        );
    }

    static <T, U, V> String isInvalidInvokerDefaultsMessage(IBaseInvokerDefaults<T, U, V> data) {
        final var baseName = "Invoker Defaults Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (StringUtils.isBlank(message)) {
            final var castMessage = Objects.equals(CastDataConstants.VOID, data.CAST_DATA()) ? isInvalidVoidCastDataMessage(data.CAST_DATA()) : isInvalidCastDataMessage(data.CAST_DATA());
            message += (
                CoreFormatter.isNullMessageWithName(data.CONSTRUCTOR(), baseName + " Constructor") +
                castMessage +
                CoreFormatter.isNullMessageWithName(data.GUARD(), baseName + " Guard")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidInvokerDefaultsMessage: ", message);
    }
}
