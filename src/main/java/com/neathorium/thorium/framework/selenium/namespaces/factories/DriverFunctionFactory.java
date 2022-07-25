package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.core.data.namespaces.DataFunctions;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.data.records.MethodMessageData;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public interface DriverFunctionFactory {
    static <T> DriverFunction<T> getErrorFunction(String message, T value) {
        return driver -> DataFactoryFunctions.getErrorFunction(value, SeleniumFormatterConstants.DRIVER_WAS_NULL + message);
    }

    static <T> DriverFunction<T> getWithMethodMessage(T object, boolean status, MethodMessageData message) {
        return driver -> DataFactoryFunctions.getWith(object, status, message);
    }

    static <T> DriverFunction<T> getWithMessage(T object, boolean status, String message) {
        return driver -> DataFactoryFunctions.getWith(object, status, message);
    }

    static <T> DriverFunction<T> getWithNameAndMessage(T object, boolean status, String nameof, String message, Exception ex, String exceptionMessage) {
        return driver -> DataFactoryFunctions.getWith(object, status, nameof, message, ex, exceptionMessage);
    }

    static <T> DriverFunction<T> getWithNameAndMessage(T object, boolean status, String nameof, String message, Exception ex) {
        return driver -> DataFactoryFunctions.getWith(object, status, nameof, message, ex, ex.getMessage());
    }

    static <T> DriverFunction<T> getWithMessage(T object, boolean status, String message, Exception ex, String exceptionMessage) {
        return driver -> DataFactoryFunctions.getWith(object, status, message, ex, exceptionMessage);
    }

    static <T> DriverFunction<T> getWithMethodMessage(T object, boolean status, MethodMessageData message, Exception ex) {
        return driver -> DataFactoryFunctions.getWith(object, status, message, ex);
    }

    static <T> DriverFunction<T> getWithMessage(T object, String message, Exception ex, String exceptionMessage) {
        return driver -> DataFactoryFunctions.getWith(object, (Boolean)object, message, ex, exceptionMessage);
    }

    static <T> DriverFunction<T> getWithMessage(T object, String message) {
        return driver -> DataFactoryFunctions.getWith(object, (Boolean)object, message);
    }

    static <T> DriverFunction<T> get(Data<T> data) {
        return driver -> DataFactoryFunctions.getWith(data.OBJECT(), data.STATUS(), DataFunctions.getFormattedMessage(data), data.EXCEPTION(), data.EXCEPTION_MESSAGE());
    }

    static <T> DriverFunction<T> getFunction(Function<WebDriver, Data<T>> function) {
        return function::apply;
    }

    static <T> DriverFunction<T> replaceName(DriverFunction<T> function, String nameof) {
        return driver -> DataFactoryFunctions.replaceName(function.apply(driver), nameof);
    }

    static <T> DriverFunction<T> prependMessage(DriverFunction<T> function, String message) {
        return driver -> DataFactoryFunctions.prependMessage(function.apply(driver), message);
    }

    static <T> DriverFunction<T> replaceMessage(DriverFunction<T> function, String message) {
        return driver -> DataFactoryFunctions.replaceMessage(function.apply(driver), message);
    }

    static <T> DriverFunction<T> appendMessage(DriverFunction<T> function, String message) {
        return driver -> DataFactoryFunctions.appendMessage(function.apply(driver), message);
    }
}
