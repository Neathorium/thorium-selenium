package com.neathorium.thorium.framework.selenium.implementations.reflection;

import com.neathorium.thorium.framework.selenium.interfaces.MethodFunction;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.framework.selenium.records.reflection.InvokerParameterizedParametersFieldData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.lang.reflect.Method;
import java.util.function.Function;

public record InvokerParameterizedData<ParameterType>(
    InvokerParameterizedParametersFieldData<ParameterType> PARAMETER_DATA
) implements MethodFunction<Function<ParameterType, Object>> {
    @Override
    public Function<ParameterType, Object> apply(Method method) {
        if (NullablePredicates.isNull(method)) {
            //TODO: Data message.
            return InvokerFunctions.regularDefault();
        }

        final var parameters = PARAMETER_DATA.PARAMETERS();
        if (!PARAMETER_DATA.VALIDATOR().test(parameters)) {
            // TODO: Data message.
            //throw new InvalidParameterException("Data parameter value field(s) didn't pass validation" + Strings.END_LINE);
            return InvokerFunctions.regularDefault();
        }

        return base -> PARAMETER_DATA.HANDLER().apply(method, base, parameters);
    }
}
