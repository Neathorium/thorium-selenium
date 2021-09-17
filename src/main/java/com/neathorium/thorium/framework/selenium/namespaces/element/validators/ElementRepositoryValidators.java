package com.neathorium.thorium.framework.selenium.namespaces.element.validators;

import com.neathorium.thorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;

import java.util.Map;

public interface ElementRepositoryValidators {
    static <T> String isInvalidContainsElementMessage(Map<String, CachedLazyElementData> elementRepository, String name) {
        return CoreFormatter.getNamedErrorMessageOrEmpty(
            "isInvalidContainsElementMessage",
            (
                CoreFormatter.isNullMessageWithName(elementRepository, "Element Repository") +
                CoreFormatter.isBlankMessageWithName(name, "Name")
            )
        );
    }
}
