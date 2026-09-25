package com.neathorium.thorium.framework.selenium.records.reflection.message;

import com.neathorium.thorium.framework.selenium.interfaces.reflection.InvokeBaseMessageData;

public record InvokeParameterizedMessageData(
    String MESSAGE,
    String RETURN_TYPE,
    String PARAMETER_TYPES,
    String PARAMETER
) implements InvokeBaseMessageData {

}
