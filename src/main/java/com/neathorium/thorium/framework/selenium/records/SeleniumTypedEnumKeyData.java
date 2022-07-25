package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;

public record SeleniumTypedEnumKeyData<T>(SeleniumTypeKey KEY, Class<T> CLAZZ) {}