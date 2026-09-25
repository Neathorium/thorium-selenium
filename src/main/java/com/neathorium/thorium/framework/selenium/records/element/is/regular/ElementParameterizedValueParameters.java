package com.neathorium.thorium.framework.selenium.records.element.is.regular;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.abstracts.regular.IElementFunctionParameters;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public record ElementParameterizedValueParameters<ReturnType>(
    TriFunction<DriverFunction<String>, Function<Data<String>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> HANDLER,
    ElementFormatData<ReturnType> FORMAT_DATA,
    BiFunction<LazyElement, String, DriverFunction<ReturnType>> FUNCTION
) implements IElementFunctionParameters<String, ReturnType> {}
