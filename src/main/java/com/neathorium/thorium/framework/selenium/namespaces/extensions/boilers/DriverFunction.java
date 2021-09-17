package com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

@FunctionalInterface
public interface DriverFunction<T> extends Function<WebDriver, Data<T>> {
    default Function<WebDriver, Data<T>> get() {
        return this;
    }
}
