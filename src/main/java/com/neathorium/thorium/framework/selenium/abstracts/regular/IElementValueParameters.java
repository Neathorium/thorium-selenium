package com.neathorium.thorium.framework.selenium.abstracts.regular;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

public interface IElementValueParameters<ParameterType, ReturnType> extends IElementFunctionParameters<ParameterType, ReturnType> {
    Function<LazyElement, DriverFunction<ReturnType>> FUNCTION();
}
