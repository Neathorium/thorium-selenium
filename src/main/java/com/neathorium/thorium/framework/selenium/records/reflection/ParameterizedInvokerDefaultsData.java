package com.neathorium.thorium.framework.selenium.records.reflection;

import com.neathorium.thorium.core.extensions.interfaces.functional.boilers.MethodFunction;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.framework.selenium.abstracts.reflection.BaseInvokerDefaultsData;
import com.neathorium.thorium.core.records.reflection.InvokerParameterizedParametersFieldData;

import java.util.function.Function;
import java.util.function.Predicate;

public class ParameterizedInvokerDefaultsData<ParameterType, ReturnType> extends BaseInvokerDefaultsData<ParameterType, InvokerParameterizedParametersFieldData<ParameterType>, ReturnType> {
    public ParameterizedInvokerDefaultsData(
        Function<InvokerParameterizedParametersFieldData<ParameterType>, MethodFunction<Function<ParameterType, Object>>> constructor,
        Predicate<InvokerParameterizedParametersFieldData<ParameterType>> guard,
        BasicCastData<ReturnType> castData,
        Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> castHandler
    ) {
        super(constructor, guard, castData, castHandler);
    }
}
