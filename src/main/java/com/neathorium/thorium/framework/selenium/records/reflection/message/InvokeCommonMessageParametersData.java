package com.neathorium.thorium.framework.selenium.records.reflection.message;

import com.neathorium.thorium.framework.selenium.interfaces.reflection.InvokeBaseMessageData;

public record InvokeCommonMessageParametersData (
    String MESSAGE,
    String RETURN_TYPE,
    String PARAMETER_TYPES
) implements InvokeBaseMessageData {}