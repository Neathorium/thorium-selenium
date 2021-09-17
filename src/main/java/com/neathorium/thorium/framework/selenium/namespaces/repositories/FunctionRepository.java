package com.neathorium.thorium.framework.selenium.namespaces.repositories;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.SeleniumTypedEnumKeyData;

import java.util.Map;

public interface FunctionRepository {
    static <T> DriverFunction<T> get(Map<SeleniumTypeKey, DriverFunction<?>> functionMap, SeleniumTypedEnumKeyData<T> keyData) {
        return (DriverFunction<T>) functionMap.get(keyData.key);
    }
}
