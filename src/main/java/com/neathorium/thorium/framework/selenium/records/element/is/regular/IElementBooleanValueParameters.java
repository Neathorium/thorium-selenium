package com.neathorium.thorium.framework.selenium.records.element.is.regular;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.abstracts.regular.IElementValueParameters;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;

import java.util.function.Function;

public interface IElementBooleanValueParameters<ReturnType> extends IElementValueParameters<Boolean, ReturnType> {
    TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> HANDLER();
    ElementFormatData<ReturnType> FORMAT_DATA();
    Function<LazyElement, DriverFunction<ReturnType>> FUNCTION();
}
