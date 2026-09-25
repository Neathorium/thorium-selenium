package com.neathorium.thorium.framework.selenium.records.reflection;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.records.HandleResultData;
import com.neathorium.thorium.core.records.caster.BasicCastData;
import com.neathorium.thorium.framework.selenium.interfaces.IBaseInvokerDefaults;
import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;

import java.util.function.Function;
import java.util.function.Predicate;

public record ParameterizedInvokerDefaultsData<ParameterType, ReturnType> (
    Function<InvokerParameterizedParametersFieldData<ParameterType>, MethodFunction<Function<ParameterType, Object>>> CONSTRUCTOR,
    Predicate<InvokerParameterizedParametersFieldData<ParameterType>> GUARD,
    BasicCastData<ReturnType> CAST_DATA,
    Function<HandleResultData<ParameterType, ReturnType>, Data<ReturnType>> CAST_HANDLER
) implements IBaseInvokerDefaults<ParameterType, InvokerParameterizedParametersFieldData<ParameterType>, ReturnType> {}
