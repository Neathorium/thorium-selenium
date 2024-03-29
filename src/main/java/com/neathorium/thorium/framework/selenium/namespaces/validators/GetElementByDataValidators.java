package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.framework.selenium.records.GetElementByData;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface GetElementByDataValidators {
    static String getValidGetElementByDataMessage(GetElementByData<?, ?, ?> data) {
        var message = isNullMessageWithName(data, "Get Element By Data");
        if (isBlank(message)) {
            message += (
                isBlankMessageWithName(data.NAMEOF, "Name of the function") +
                isNullMessageWithName(data.VALIDATOR, "Validator function") +
                isNullMessageWithName(data.GETTER, "Getter function") +
                isNullMessageWithName(data.FORMATTER, "Formatter function") +
                isNullMessageWithName(data.DEFAULT_VALUE, "Default data value") +
                isBlankMessageWithName(data.FILTER_NAME, "Filter type name")
            );
        }

        return getNamedErrorMessageOrEmpty("getValidGetElementByDataMessage", message);
    }
}
