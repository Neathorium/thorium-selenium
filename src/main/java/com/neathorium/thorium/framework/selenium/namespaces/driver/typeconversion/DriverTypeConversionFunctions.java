package com.neathorium.thorium.framework.selenium.namespaces.driver.typeconversion;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverTypeConversionFunctions {
    static <T, U> Data<U> getSubtypeOf(String dependencyName, T dependency, Function<T, U> getter, U defaultValue) {
        final var lDependencyName = isNotBlank(dependencyName) ? dependencyName : "Dependency";
        final var status = NullablePredicates.isNotNull(dependency);
        final var object = status ? getter.apply(dependency) : defaultValue;
        final var message = lDependencyName + (status ? CoreFormatterConstants.WASNT_NULL : CoreFormatterConstants.WAS_NULL);
        return DataFactoryFunctions.getWith(object, status, "getSubtypeOf", message);
    }

    static <T> DriverFunction<T> getSubtypeOfDriver(Function<WebDriver, T> getter, T defaultValue) {
        return driver -> getSubtypeOf("Driver", driver, getter, defaultValue);
    }
}
