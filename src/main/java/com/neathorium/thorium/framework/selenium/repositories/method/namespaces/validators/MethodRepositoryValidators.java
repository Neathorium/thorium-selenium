package com.neathorium.thorium.framework.selenium.repositories.method.namespaces.validators;

import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodSourceData;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface MethodRepositoryValidators {
    static String validateMethodSourceData(MethodSourceData data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Method Get Parameters data");
        if (isBlank(message)) {
            message +=  (
                CoreFormatter.isNullMessageWithName(data.METHOD_MAP(), "Method map") +
                CoreFormatter.isNullMessageWithName(data.LIST(), "Method List") +
                CoreFormatter.isFalseMessageWithName(data.DEFAULT_VALUE(), "Default Value")
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("validateMethodGetCommonParametersData", message);
    }

    static String validateMethodParametersData(MethodParametersData parameterData) {
        var message = CoreFormatter.isNullMessageWithName(parameterData, "Method parameters data");
        if (isBlank(message)) {
            message +=  (
                CoreFormatter.isNullMessageWithName(parameterData.VALIDATOR(), "Condition method") +
                CoreFormatter.isBlankMessageWithName(parameterData.METHOD_NAME(), "Method name")
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("validateMethodParametersData", message);
    }

    static String validateGetMethodFromList(MethodSourceData data, MethodParametersData parameterData) {
        return validateMethodSourceData(data) + validateMethodParametersData(parameterData);
    }
}
