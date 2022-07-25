package com.neathorium.thorium.framework.selenium.implementations.reflection;

import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.framework.selenium.records.reflection.InvokerParameterizedParametersFieldData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;

public class InvokerParameterizedData<ParameterType> implements MethodFunction<Function<ParameterType, Object>> {
    public final InvokerParameterizedParametersFieldData<ParameterType> parameterData;

    public InvokerParameterizedData(InvokerParameterizedParametersFieldData<ParameterType> parameterData) {
        this.parameterData = parameterData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (InvokerParameterizedData<?>) o;
        return Objects.equals(parameterData, that.parameterData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterData);
    }

    @Override
    public Function<ParameterType, Object> apply(Method method) {
        if (NullablePredicates.isNull(method)) {
            //TODO: Data message.
            return InvokerFunctions.regularDefault();
        }

        final var parameters = parameterData.PARAMETERS();
        if (!parameterData.VALIDATOR().test(parameters)) {
            // TODO: Data message.
            //throw new InvalidParameterException("Data parameter value field(s) didn't pass validation" + Strings.END_LINE);
            return InvokerFunctions.regularDefault();
        }

        return base -> parameterData.HANDLER().apply(method, base, parameters);
    }
}
