package com.neathorium.thorium.framework.selenium.namespaces.validators.element;

import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;

import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.neathorium.thorium.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ElementFormatters {
    static String getConditionStatusMessage(boolean key) {
        return key ? "is" : "isn't";
    }

    static String getConditionMessage(String elementName, String descriptor, boolean option) {
        final var name = "getConditionMessage";
        final var errorMessage = isBlankMessageWithName(elementName, "Element name") + isBlankMessageWithName(descriptor, "Descriptor");
        if (isNotBlank(errorMessage)) {
            return getNamedErrorMessageOrEmpty(name, errorMessage);
        }

        return name + (CoreFormatterConstants.ELEMENT + getConditionStatusMessage(option) + " " + descriptor + CoreFormatterConstants.END_LINE);
    }

    static String getElementValueMessage(String elementName, String descriptor, String value) {
        final var name = "getElementValueMessage";
        final var errorMessage = (
            isBlankMessageWithName(elementName, "Element name") +
            isBlankMessageWithName(descriptor, "Descriptor") +
            CoreFormatter.isNullMessageWithName(value, "Value")
        );

        if (isNotBlank(errorMessage)) {
            return getNamedErrorMessageOrEmpty(name, errorMessage);
        }

        return name + (CoreFormatterConstants.ELEMENT + " " + elementName + " " + descriptor + " was (\"" + value + "\")" + CoreFormatterConstants.END_LINE);
    }
}
