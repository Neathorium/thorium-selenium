package com.neathorium.thorium.framework.selenium.repositories.method.namespaces;

import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.thorium.framework.selenium.repositories.method.constants.MethodRepositoryConstants;
import com.neathorium.thorium.framework.selenium.repositories.method.namespaces.validators.MethodRepositoryValidators;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodSourceData;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface MethodRepositoryFunctions {
    private static Data<Boolean> putMethodToMap(HashMap<String, MethodData> methodMap, String methodName, Method method) {
        final var nameof = "putMethodToMap";
        if (methodMap.containsKey(methodName)) {
            return DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, CoreFormatterConstants.METHOD_ALREADY_IN_MAP);
        }

        final var status = true;
        method.setAccessible(status);
        methodMap.put(methodName, new MethodData(method, Arrays.asList(method.getParameterTypes()).toString(), method.getReturnType().toGenericString()));

        return DataFactoryFunctions.getBoolean(status, nameof, CoreFormatterConstants.METHOD_PUT_IN_MAP);
    }

    private static Data<MethodData> getMethodFromMap(HashMap<String, MethodData> methodMap, String methodName, Data<MethodData> defaultValue) {
        final var status = methodMap.containsKey(methodName);
        return DataFactoryFunctions.getWith(
            status ? methodMap.get(methodName) : defaultValue.OBJECT(),
            status,
            "getMethodFromMap",
            CoreFormatter.getMethodFromMapMessage(methodName, status)
        );
    }

    private static Data<MethodData> getMethodFromMap(HashMap<String, MethodData> methodMap, MethodParametersData parameterData) {
        return getMethodFromMap(methodMap, parameterData.METHOD_NAME(), MethodRepositoryConstants.NULL_METHODDATA);
    }

    private static Data<MethodData> getMethodFromList(MethodSourceData data, MethodParametersData parameterData) {
        final var nameof = "getMethodFromList";
        final var methodName = parameterData.METHOD_NAME();
        final var validator = parameterData.VALIDATOR();
        final var list = data.LIST();
        final var methodMap = data.METHOD_MAP();
        var statusData = CoreDataConstants.NULL_BOOLEAN;
        for (var method : list) {
            if (!validator.test(method, methodName)) {
                continue;
            }

            statusData = putMethodToMap(methodMap, methodName, method);
            break;
        }

        final var methodData = getMethodFromMap(methodMap, methodName, data.DEFAULT_VALUE());
        final var status = statusData.STATUS() && methodData.STATUS();
        return DataFactoryFunctions.getWith(methodData.OBJECT(), status, nameof, CoreFormatter.getMethodFromListMessage(methodName, status));
    }

    static Data<MethodData> getMethod(MethodSourceData data, MethodParametersData parameterData) {
        final var nameof = "getMethod";
        final var message = MethodRepositoryValidators.validateGetMethodFromList(data, parameterData);
        final var defaultValue = data.DEFAULT_VALUE();
        if (isNotBlank(message)) {
            return DataFactoryFunctions.replaceMessage(defaultValue, nameof, message);
        }

        final var methodData = getMethodFromMap(data.METHOD_MAP(), parameterData.METHOD_NAME(), data.DEFAULT_VALUE());
        return methodData.STATUS() ? methodData : getMethodFromList(data, parameterData);
    }
}
