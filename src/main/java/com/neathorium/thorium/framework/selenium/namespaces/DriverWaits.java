package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.wait.namespaces.WaitFunctions;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitDataFactory;
import com.neathorium.thorium.core.wait.namespaces.factories.WaitTimeDataFactory;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.GuardPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverWaits {
    static DriverFunction<Boolean> waitNavigatedTo(String url, String query, int interval, int timeout) {
        final Predicate<Integer> condition = BasicPredicates::isPositiveNonZero;
        return ExecutionCore.ifDriver(
            "waitNavigatedTo",
            isNotBlank(url) && NullablePredicates.isNotNull(query) && GuardPredicates.areAll(condition, interval, timeout) && (interval < timeout),
            driver -> WaitFunctions.core(
                WaitDataFactory.getWith(
                    ExpectedConditions.isUrlContains(url, query),
                    WaitPredicateFunctions::isTruthyData,
                    "Waiting for url",
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                )).apply(driver),
            CoreDataConstants.PARAMETERS_NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> navigateAndWait(String url, String query, int interval, int timeout) {
        final Predicate<Integer> condition = BasicPredicates::isPositiveNonZero;
        return ExecutionCore.ifDriver(
            "navigateAndWait",
            isNotBlank(url) && NullablePredicates.isNotNull(query) && GuardPredicates.areAll(condition, interval, timeout) && BasicPredicates.isSmallerThan(interval, timeout),
            SeleniumExecutor.execute(Driver.navigate(url, query), waitNavigatedTo(url, interval, timeout)),
            CoreDataConstants.PARAMETERS_NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> waitNavigatedTo(String url, int interval, int timeout) {
        return waitNavigatedTo(url, CoreFormatterConstants.EMPTY, interval, timeout);
    }

    static DriverFunction<Boolean> navigateAndWait(String url, int interval, int timeout) {
        return navigateAndWait(url, CoreFormatterConstants.EMPTY, interval, timeout);
    }
}
