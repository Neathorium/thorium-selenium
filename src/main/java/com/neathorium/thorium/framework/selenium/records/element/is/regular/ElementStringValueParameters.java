package com.neathorium.thorium.framework.selenium.records.element.is.regular;

import com.neathorium.thorium.framework.selenium.abstracts.regular.AbstractElementValueParameters;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.records.Data;

import java.util.function.Function;

public class ElementStringValueParameters<ReturnType> extends AbstractElementValueParameters<String, ReturnType> {
    public ElementStringValueParameters(
        TriFunction<DriverFunction<String>, Function<Data<String>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData, function);
    }
}
