package com.neathorium.thorium.framework.selenium.abstracts.regular;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;

import java.util.function.Function;

public interface IElementFunctionParameters<ParameterType, ReturnType> {
    TriFunction<DriverFunction<ParameterType>, Function<Data<ParameterType>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> HANDLER();
    ElementFormatData<ReturnType> FORMAT_DATA();
}
