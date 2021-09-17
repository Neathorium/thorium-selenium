package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumTypeMethods;
import com.neathorium.thorium.core.records.MethodParametersData;

public abstract class SeleniumMethodDefaults {
    public static final MethodParametersData FIND_ELEMENT = new MethodParametersData(ElementMethodNameConstants.FIND_ELEMENT, SeleniumTypeMethods::isWebElementMethod, SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS);
}
