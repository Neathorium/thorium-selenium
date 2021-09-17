package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.core.namespaces.validators.LazyLocatorValidators;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SeleniumLazyLocatorValidators {
    static String isInvalidLazyLocator(LazyLocator data) {
        var message = LazyLocatorValidators.isInvalidLazyLocatorCommon(data);
        if (isBlank(message)) {
            message += (
                SeleniumStrategyValidators.isValidStrategy(data.STRATEGY)
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isInvalidLazyLocator", message);
    }
}
