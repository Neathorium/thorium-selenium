package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptHandlerFunction;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.thorium.framework.selenium.records.scripter.ScriptParametersData;
import com.neathorium.thorium.core.constants.CoreConstants;

import java.util.function.BiFunction;

public interface ScriptExecuteFunctions {
    static <T extends Data> Object[] handleDataParameter(ScriptParametersData<T> data) {
        if (!ScriptExecutions.isValidScriptParametersData(data)) {
            return CoreConstants.EMPTY_OBJECT_ARRAY;
        }

        final var parameters = data.parameters;
        return data.validator.test(parameters) ? data.converter.apply(parameters) : CoreConstants.EMPTY_OBJECT_ARRAY;
    }

    static <T> Object[] handleParameter(ScriptParametersData<T> data) {
        if (!ScriptExecutions.isValidScriptParametersData(data)) {
            return CoreConstants.EMPTY_OBJECT_ARRAY;
        }

        final var parameters = data.parameters;
        return data.validator.test(parameters) ? data.converter.apply(parameters) : CoreConstants.EMPTY_OBJECT_ARRAY;
    }

    static ScriptHandlerFunction executeScript() {
        return executor -> executor::executeScript;
    }

    static ScriptHandlerFunction executeAsyncScript() {
        return executor -> executor::executeAsyncScript;
    }

    static ScriptFunction<BiFunction<String, Object[], Object>> executeScriptWithParameters() {
        return executor -> executor::executeScript;
    }

    static ScriptFunction<BiFunction<String, Object[], Object>> executeAsyncScriptWithParameters() {
        return executor -> executor::executeAsyncScript;
    }

    static <T> ScriptParametersData<Data<T>> getScriptParametersDataWithDefaults(Data<T> data) {
        return new ScriptParametersData<>(data, DataPredicates::isValidNonFalse, SeleniumUtilities::unwrapToArray);
    }

    static <T, V extends Data<T>> Object[] handleDataParameterWithDefaults(V data) {
        return handleDataParameter(getScriptParametersDataWithDefaults(data));
    }
}