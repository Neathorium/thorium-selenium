package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.wait.namespaces.WaitFunctions;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitDataFactory;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitTimeDataFactory;
import com.neathorium.thorium.framework.selenium.constants.DriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.records.element.regular.ElementWaitParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWaitParameters;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.By;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface WaitConditions {
    static DriverFunction<Boolean> waitWith(By locator, Function<By, DriverFunction<Boolean>> conditionGetter, String option, int interval, int timeout, String message) {
        return ExecutionCore.ifDriver(
            "waitConditionCore",
            NullablePredicates.isNotNull(conditionGetter),
            WaitFunctions.core(WaitDataFactory.getWith(
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
            NullablePredicates.isNotNull(conditionGetter),
            DriverFunctionFactory.prependMessage(
                WaitFunctions.core(WaitDataFactory.getWith(
                    conditionGetter.apply(data),
                    isBlank(option) ? WaitPredicateFunctions::isTruthyData : WaitPredicateFunctions::isFalsyData,
                    data.NAME + " " + message,
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                ))::apply,
                CoreFormatterConstants.ELEMENT + data.NAME
            ),
            DataFactoryFunctions.prependMessage(CoreDataConstants.NULL_BOOLEAN, CoreFormatterConstants.ELEMENT + data.NAME)
        );
    }

    static DriverFunction<Boolean> waitWith(ElementWaitParameters data, Function<By, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return SeleniumUtilities.isNotNullElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> waitWith(LazyElementWaitParameters data, Function<LazyElement, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return SeleniumUtilities.isNotNullLazyElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> waitWith(DriverFunction<Boolean> conditionGetter, Predicate<Data<Boolean>> exitCondition, int interval, int timeout, String message) {
        return ExecutionCore.ifDriver(
            "waitConditionCore",
            NullablePredicates.isNotNull(conditionGetter),
            DriverFunctionFactory.getFunction(
                WaitFunctions.core(WaitDataFactory.getWith(
                    conditionGetter,
                    exitCondition,
                    message,
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                ))
            ),
            DataFactoryFunctions.prependMessage(CoreDataConstants.NULL_BOOLEAN, "There were parameter issues" + CoreFormatterConstants.END_LINE)
        );
    }
}
