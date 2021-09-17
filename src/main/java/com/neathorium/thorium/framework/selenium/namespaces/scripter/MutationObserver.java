package com.neathorium.thorium.framework.selenium.namespaces.scripter;

import com.neathorium.thorium.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.neathorium.thorium.framework.selenium.constants.scripter.DevtoolsConstants;
import com.neathorium.thorium.framework.selenium.namespaces.Driver;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.neathorium.thorium.core.namespaces.DataExecutionFunctions.ifDependency;

public interface MutationObserver {
    private static String getMutationObserverScript(String locator) {
        return (
            "if ((typeof document['__test']) === (typeof {})) {" +
            "    return true;" +
            "}" +
            "document['__test'] = {};" +
            "document['__test']['consoleFocused'] = false;" +
            "document['__test']['observing'] = document.querySelectorAll('" + locator + "')[0];" +
            "document['__test']['observerCore'] = function observerCoreFunction(mutations) {" +
            "    var length = mutations.length," +
            "        index = 0," +
            "        mutation;" +
            "    for(; index < length; ++index) {" +
            "        mutation = mutations[index];" +
            "        if (mutation.attributeName != 'class') {" +
            "            continue;" +
            "        }" +
            "        document['__test']['consoleFocused'] = mutation.target.classList.contains('CodeMirror-focused');" +
            "        break;" +
            "    }" +
            "};" +
            "document['__test']['observer'] = new MutationObserver(document['__test']['observerCore']);" +
            "document['__test']['observer'].observe(document['__test']['observing'], {attributes: true});" +
            "return true;"
        );
    }

    private static Data<Boolean> isConsoleFocusedObserverSet(WebDriver driver) {
        final var result = Driver.execute(DevtoolsConstants.GUARD).apply(driver);
        final var object = result.object instanceof String ? ((String) result.object).trim().replaceAll("\"", "") : CoreFormatterConstants.EMPTY;
        final var status = Boolean.parseBoolean(object);
        final var message = "Observer was" + (status ? "" : "not") + " set" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isConsoleFocusedObserverSet", message, result.exception);
    }

    private static Data<Boolean> isConsoleFocusedCore(WebDriver driver) {
        final var result = Driver.execute(DevtoolsConstants.CONSOLE_FOCUSED_CHECK).apply(driver);
        final var status = CoreUtilities.castToBoolean(result.object);
        final var message = "Console was" + (status ? "" : "not") + " focused" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isConsoleFocused", message, result.exception);
    }

    private static Data<Boolean> setConsoleFocusedFunctionCore(WebDriver driver, LazyElement element) {
        final var locator = LazyElementUtilities.getCSSSelectorFromElement(element);
        final var script = getMutationObserverScript(locator);
        final var result = Driver.execute(script).apply(driver);
        final var status = CoreUtilities.castToBoolean(result.object);
        final var message = "Observer was" + (status ? "" : "not") + " set" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, message, result.exception);
    }

    private static Function<WebDriver, Data<Boolean>> setConsoleFocusedFunctionCore(LazyElement element) {
        return driver -> setConsoleFocusedFunctionCore(driver, element);
    }

    static DriverFunction<Boolean> setConsoleFocusedFunction(LazyElement element) {
        return DataExecutionFunctions.ifDependency(
            "setConsoleFocusedFunction",
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            SeleniumExecutor.execute(
                Element.waitPresent(element, 500, 10000),
                DriverFunctionFactory.getFunction(setConsoleFocusedFunctionCore(element))
            ),
            CoreDataConstants.NULL_BOOLEAN
        )::apply;
    }

    static DriverFunction<Boolean> setConsoleFocusedFunction() {
        return setConsoleFocusedFunction(DevtoolsViewConstants.CONSOLE_FOCUS);
    }

    private static DriverFunction<Boolean> isConsoleFocusedObserverSet() {
        return MutationObserver::isConsoleFocusedObserverSet;
    }

    static DriverFunction<Boolean> isConsoleFocused() {
        return MutationObserver::isConsoleFocusedCore;
    }
}
