package com.neathorium.thorium.framework.selenium.repositories.method.namespaces.validators;

import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface MethodParametersDataValidators {
    static String isValid(MethodParametersData data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Method Parameter data");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(data.METHOD_NAME(), "Method name") +
                CoreFormatter.isNullMessageWithName(data.VALIDATOR(), "Validator")
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isValid", message);
    }
}
