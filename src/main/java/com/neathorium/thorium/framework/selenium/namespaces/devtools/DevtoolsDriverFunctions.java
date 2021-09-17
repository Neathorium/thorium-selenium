package com.neathorium.thorium.framework.selenium.namespaces.devtools;

import com.neathorium.thorium.framework.selenium.constants.scripts.element.Displayed;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public interface DevtoolsDriverFunctions {
    static DriverFunction<Boolean> clearConsole(LazyElement console) {
        return Element.inputWhenClickable(console, SeleniumFormatterConstants.CLEAR_CONSOLE);
    }

    static DriverFunction<Boolean> getElements(LazyElement input, String cssSelector) {
        return SeleniumExecutor.execute(
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord("var elements = document.querySelectorAll(" + cssSelector + ");", Keys.ENTER))
        );
    }

    static DriverFunction<Boolean> getElementByIndex(LazyElement input, String cssSelector, int index) {
        return SeleniumExecutor.execute(
            getElements(input, cssSelector),
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord("var element = elements[" + index + "]", Keys.ENTER))
        );
    }

    static DriverFunction<Boolean> getSingleElementByIndex(LazyElement input, String cssSelector) {
        return getElementByIndex(input, cssSelector, 0);
    }

    static DriverFunction<Boolean> getElement(LazyElement input, String cssSelector) {
        return getSingleElementByIndex(input, cssSelector);
    }

    private static Data<Integer> getCountOfElementsCore(WebDriver driver, LazyElement input, LazyElement output, String cssSelector) {
        final var result = SeleniumExecutor.execute(
            getElements(input, cssSelector),
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord("elements.length;", Keys.ENTER)),
            Element.getText(output)
        ).apply(driver);

        return DataFactoryFunctions.replaceObject(result, Integer.valueOf(String.valueOf(result.object)));
    }

    private static Function<WebDriver, Data<Integer>> getCountOfElementsCore(LazyElement input, LazyElement output, String cssSelector) {
        return driver -> getCountOfElementsCore(driver, input, output, cssSelector);
    }

    static DriverFunction<Integer> getCountOfElements(LazyElement element, LazyElement output, String cssSelector) {
        return DriverFunctionFactory.getFunction(getCountOfElementsCore(element, output, cssSelector));
    }

    static DriverFunction<Boolean> setDisplayed(LazyElement input) {
        return SeleniumExecutor.execute(
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord(Displayed.DISPLAYED_CORE, Keys.ENTER))
        );
    }

    private static Data<Boolean> isDisplayedCore(WebDriver driver, LazyElement input, LazyElement output, String cssSelector) {
        final var result = SeleniumExecutor.execute(
            setDisplayed(input),
            getElement(input, cssSelector),
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord(Displayed.IS_ELEMENT_DISPLAYED, Keys.ENTER)),
            Element.getText(output)
        ).apply(driver);

        return DataFactoryFunctions.replaceObject(result, CoreUtilities.castToBoolean(String.valueOf(result.object)));
    }

    private static Function<WebDriver, Data<Boolean>> isDisplayedCore(LazyElement input, LazyElement output, String cssSelector) {
        return driver -> isDisplayedCore(driver, input, output, cssSelector);
    }

    static DriverFunction<Boolean> isDisplayed(LazyElement input, LazyElement output, String cssSelector) {
        return DriverFunctionFactory.getFunction(isDisplayedCore(input, output, cssSelector));
    }

    private static Data<Boolean> isPresentCore(WebDriver driver, LazyElement input, LazyElement output, String cssSelector) {
        final var result = SeleniumExecutor.execute(
            getElements(input, cssSelector),
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord("elements.length > 0;", Keys.ENTER)),
            Element.getText(output)
        ).apply(driver);

        return DataFactoryFunctions.replaceObject(result, CoreUtilities.castToBoolean(String.valueOf(result.object)));
    }

    private static Function<WebDriver, Data<Boolean>> isPresentCore(LazyElement input, LazyElement output, String cssSelector) {
        return driver -> isPresentCore(driver, input, output, cssSelector);
    }

    static DriverFunction<Boolean> isPresent(LazyElement input, LazyElement output, String cssSelector) {
        return DriverFunctionFactory.getFunction(isPresentCore(input, output, cssSelector));
    }

    private static Data<Boolean> clickCore(WebDriver driver, LazyElement input, LazyElement output, String cssSelector) {
        final var result = SeleniumExecutor.execute(
            clearConsole(input),
            Element.inputWhenClickable(input, Keys.chord("element.click();", Keys.ENTER))
        ).apply(driver);

        final var status = DataPredicates.isValidNonFalse(result);
        return DataFactoryFunctions.getBoolean(status, "click", (status ? "Click was successful" : "Click wasn't successful") + CoreFormatterConstants.END_LINE);
    }

    private static Function<WebDriver, Data<Boolean>> clickCore(LazyElement input, LazyElement output, String cssSelector) {
        return driver -> clickCore(driver, input, output, cssSelector);
    }

    static DriverFunction<Boolean> click(LazyElement input, LazyElement output, String cssSelector) {
        return DriverFunctionFactory.getFunction(clickCore(input, output, cssSelector));
    }
}
