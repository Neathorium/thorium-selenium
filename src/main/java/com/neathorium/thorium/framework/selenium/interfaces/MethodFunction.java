package com.neathorium.thorium.framework.selenium.interfaces;

import java.lang.reflect.Method;
import java.util.function.Function;

@FunctionalInterface
public interface MethodFunction<ReturnType> extends Function<Method, ReturnType> {}