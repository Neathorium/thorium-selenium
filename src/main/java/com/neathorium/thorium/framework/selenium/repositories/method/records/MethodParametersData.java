package com.neathorium.thorium.framework.selenium.repositories.method.records;

import java.lang.reflect.Method;
import java.util.function.BiPredicate;

public record MethodParametersData(String METHOD_NAME, BiPredicate<Method, String> VALIDATOR, MethodSourceData SOURCE_DATA) {
}
