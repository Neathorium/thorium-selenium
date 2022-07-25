package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.SeleniumTypedEnumKeyData;

import java.util.Map;

public record ExecuteCoreData<HandlerType, ReturnType>(
        ExecutorData<HandlerType, String, Boolean, ReturnType> DATA,
        Map<SeleniumTypeKey, DriverFunction<?>> FUNCTION_MAP,
        SeleniumTypedEnumKeyData<ReturnType> NEGATIVE_KEY_DATA
) {}
