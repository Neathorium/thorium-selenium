package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.SeleniumTypedEnumKeyData;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;

import java.util.Map;
import java.util.Objects;

public class ExecuteCoreData<HandlerType, ReturnType> {
    public final ExecutorData<HandlerType, String, Boolean, ReturnType> data;
    public final Map<SeleniumTypeKey, DriverFunction<?>> functionMap;
    public final SeleniumTypedEnumKeyData<ReturnType> negativeKeyData;

    public ExecuteCoreData(ExecutorData<HandlerType, String, Boolean, ReturnType> data, Map<SeleniumTypeKey, DriverFunction<?>> functionMap, SeleniumTypedEnumKeyData<ReturnType> negativeKeyData) {
        this.data = data;
        this.functionMap = functionMap;
        this.negativeKeyData = negativeKeyData;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (ExecuteCoreData<?, ?>) o;
        return (
            CoreUtilities.isEqual(data, that.data) &&
            CoreUtilities.isEqual(functionMap, that.functionMap) &&
            CoreUtilities.isEqual(negativeKeyData, that.negativeKeyData)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, functionMap, negativeKeyData);
    }

    @Override
    public String toString() {
        return (
            "ExecuteCoreData{" +
            "data=" + data +
            ", functionMap=" + functionMap +
            ", negativeKeyData=" + negativeKeyData +
            '}'
        );
    }
}
