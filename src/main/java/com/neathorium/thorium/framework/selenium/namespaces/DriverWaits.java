package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.core.namespaces.factories.wait.WaitDataFactory;
import com.neathorium.thorium.core.namespaces.factories.wait.WaitTimeDataFactory;
import com.neathorium.thorium.core.namespaces.wait.Wait;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverWaits {
    static DriverFunction<Boolean> waitNavigatedTo(String url, String query, int interval, int timeout) {
        return ExecutionCore.ifDriver(
            "waitNavigatedTo",
            isNotBlank(url) && NullableFunctions.isNotNull(query) && CoreUtilities.areAll(BasicPredicates::isPositiveNonZero, interval, timeout) && (interval < timeout),
            driver -> Wait.core(
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
        return ExecutionCore.ifDriver(
            "navigateAndWait",
            isNotBlank(url) && NullableFunctions.isNotNull(query) && CoreUtilities.areAll(BasicPredicates::isPositiveNonZero, interval, timeout) && BasicPredicates.isSmallerThan(interval, timeout),
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
