package com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

@FunctionalInterface
public interface LazyElementFunction<T> extends Function<LazyElement, DriverFunction<T>> {}
