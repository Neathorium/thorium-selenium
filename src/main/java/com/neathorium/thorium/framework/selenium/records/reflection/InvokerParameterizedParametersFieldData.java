package com.neathorium.thorium.framework.selenium.records.reflection;

import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public record InvokerParameterizedParametersFieldData<ParameterType>(Object[] PARAMETERS, Predicate<Object[]> VALIDATOR, TriFunction<Method, ParameterType, Object[], Object> HANDLER) { }
