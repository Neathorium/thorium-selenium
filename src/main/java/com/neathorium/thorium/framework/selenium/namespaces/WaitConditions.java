package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.framework.selenium.constants.DriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.records.element.regular.ElementWaitParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWaitParameters;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.namespaces.factories.wait.WaitDataFactory;
import com.neathorium.thorium.core.namespaces.factories.wait.WaitTimeDataFactory;
import com.neathorium.thorium.core.namespaces.wait.Wait;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import org.openqa.selenium.By;

import java.util.function.Function;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface WaitConditions {
    static DriverFunction<Boolean> waitWith(By locator, Function<By, DriverFunction<Boolean>> conditionGetter, String option, int interval, int timeout, String message) {
        return ExecutionCore.ifDriver(
            "waitConditionCore",
            NullableFunctions.isNotNull(conditionGetter),
            Wait.core(WaitDataFactory.getWith(
                conditionGetter.apply(locator),
                isBlank(option) ? WaitPredicateFunctions::isTruthyData : WaitPredicateFunctions::isFalsyData,
                "Element located by: " + locator + " to be " + (isBlank(message) ? "clickable" : message) + CoreFormatterConstants.END_LINE,
                WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
            ))::apply,
            DataFactoryFunctions.appendMessage(CoreDataConstants.NULL_BOOLEAN, "Condition Getter" + CoreFormatterConstants.WAS_NULL)
        );
    }

    static DriverFunction<Boolean> waitWith(LazyElement data, Function<LazyElement, DriverFunction<Boolean>> conditionGetter, String option, int interval, int timeout, String message) {
        return ExecutionCore.ifDriver(
            "waitConditionCore",
            NullableFunctions.isNotNull(conditionGetter),
            DriverFunctionFactory.prependMessage(
                Wait.core(WaitDataFactory.getWith(
                    conditionGetter.apply(data),
                    isBlank(option) ? WaitPredicateFunctions::isTruthyData : WaitPredicateFunctions::isFalsyData,
                    data.name + " " + message,
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                ))::apply,
                CoreFormatterConstants.ELEMENT + data.name
            ),
            DataFactoryFunctions.prependMessage(CoreDataConstants.NULL_BOOLEAN, CoreFormatterConstants.ELEMENT + data.name)
        );
    }

    static DriverFunction<Boolean> waitWith(ElementWaitParameters data, Function<By, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return SeleniumUtilities.isNotNullElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> waitWith(LazyElementWaitParameters data, Function<LazyElement, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return SeleniumUtilities.isNotNullLazyElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }
}
