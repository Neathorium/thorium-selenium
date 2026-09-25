package com.neathorium.thorium.framework.selenium.repositories.method.records.reflection;

import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodParametersData;

public record InvokeMethodData (
    MethodParametersData PARAMETERS_DATA,
    String NAMEOF
) {}
