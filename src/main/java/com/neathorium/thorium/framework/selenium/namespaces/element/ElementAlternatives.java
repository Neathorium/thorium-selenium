package com.neathorium.thorium.framework.selenium.namespaces.element;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.Execute;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import org.openqa.selenium.By;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface ElementAlternatives {
    static DriverFunction<Boolean> clearWithSelectAll(LazyElement element) {
        return ifDriver("clearWithSelectAll", SeleniumUtilities.isNotNullLazyElement(element), Element.sendKeys(element, SeleniumFormatterConstants.SELECT_ALL), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clearWithSelectAll(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("clearWithSelectAll", DataPredicates.isValidNonFalse(data), clearWithSelectAll(data.OBJECT()), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clearWithSelectAll(By locator, SingleGetter getter) {
        return clearWithSelectAll(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> clearWithSelectAll(By locator) {
        return clearWithSelectAll(LocatorRepository.getIfContains(locator));
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(LazyElement element) {
        return ExecutionCore.ifDriver("clickWithEventDispatcher", SeleniumUtilities.isNotNullLazyElement(element), Execute.clickEventDispatcher(element), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("clickWithEventDispatcher", DataPredicates.isValidNonFalse(data), clickWithEventDispatcher(data.OBJECT()), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(By locator, SingleGetter getter) {
        return clickWithEventDispatcher(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(By locator) {
        return clickWithEventDispatcher(LocatorRepository.getIfContains(locator));
    }
}
