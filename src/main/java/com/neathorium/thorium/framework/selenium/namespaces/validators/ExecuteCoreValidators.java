package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.framework.selenium.records.scripter.ParametersFieldDefaultsData;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ExecuteCoreValidators {
    static String isInvalidParametersFieldDefaultsData(ParametersFieldDefaultsData parameterData) {
        final var baseName = "Parameter data";
        var message = CoreFormatter.isNullMessageWithName(parameterData, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameterData.handler, baseName + " Handler") +
                CoreFormatter.isNullMessageWithName(parameterData.validator, baseName + " Validator")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidParametersFieldDefaultsData", message);
    }
}
