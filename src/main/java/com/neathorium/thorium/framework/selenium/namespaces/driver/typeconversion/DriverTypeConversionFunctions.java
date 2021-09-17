package com.neathorium.thorium.framework.selenium.namespaces.driver.typeconversion;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverTypeConversionFunctions {
    static <T, U> Data<U> getSubtypeOf(String dependencyName, T dependency, Function<T, U> getter, U defaultValue) {
        final var lDependencyName = isNotBlank(dependencyName) ? dependencyName : "Dependency";
        final var status = NullableFunctions.isNotNull(dependency);
        final var object = status ? getter.apply(dependency) : defaultValue;
        final var message = lDependencyName + (status ? CoreFormatterConstants.WASNT_NULL : CoreFormatterConstants.WAS_NULL);
        return DataFactoryFunctions.getWith(object, status, "getSubtypeOf", message);
    }

    static <T> DriverFunction<T> getSubtypeOfDriver(Function<WebDriver, T> getter, T defaultValue) {
        return driver -> getSubtypeOf("Driver", driver, getter, defaultValue);
    }
}
