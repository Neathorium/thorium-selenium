package com.neathorium.thorium.framework.selenium.namespaces.scripter.displayed;

import com.neathorium.thorium.framework.selenium.constants.scripts.element.Displayed;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.Driver;
import com.neathorium.thorium.framework.selenium.namespaces.ScriptExecuteFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.namespaces.DataFunctions;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullWebElement;
import static com.neathorium.thorium.core.namespaces.DataFactoryFunctions.getWith;
import static com.neathorium.thorium.core.namespaces.DataFactoryFunctions.replaceMessage;
import static com.neathorium.thorium.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface DisplayedFunctions {
    private static DriverFunction<Boolean> isDisplayedCore(Data<WebElement> data) {
        return ifDriver(
            "isDisplayed",
            SeleniumUtilities.isNotNullWebElement(data),
            driver -> {
                final var parameter = ScriptExecuteFunctions.handleDataParameterWithDefaults(data);
                final var result = Driver.executeSingleParameter(Displayed.IS_DISPLAYED_DISPATCHER, parameter).apply(driver);
                return isValidNonFalse(result) ? (
                    getWith(CoreUtilities.castToBoolean(result.object), result.status, result.message)
                ) : replaceMessage(CoreDataConstants.NULL_BOOLEAN, DataFunctions.getFormattedMessage(result));
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> isDisplayed(DriverFunction<WebElement> getter) {
        return ExecutionCore.ifDriverFunction("isDisplayed", NullableFunctions::isNotNull, getter, DisplayedFunctions::isDisplayedCore, CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isDisplayed(LazyElement element) {
        return SeleniumUtilities.isNotNullLazyElement(element) ? (
                isDisplayed(element.get())
        ) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_BOOLEAN, "isDisplayed", "LazyElement element" + CoreFormatterConstants.WAS_NULL));
    }

    static DriverFunction<Boolean> isDisplayed(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("isDisplayed", isValidNonFalse(data), isDisplayed(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isDisplayed(By locator, SingleGetter getter) {
        return isDisplayed(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> isDisplayed(By locator) {
        return isDisplayed(locator, SingleGetter.DEFAULT);
    }
}
