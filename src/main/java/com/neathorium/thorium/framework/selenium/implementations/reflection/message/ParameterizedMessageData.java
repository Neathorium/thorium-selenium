package com.neathorium.thorium.framework.selenium.implementations.reflection.message;

import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeCommonMessageParametersData;
import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeParameterizedMessageData;
import java.util.function.Function;

public record ParameterizedMessageData(
    String PARAMETER,
    Function<InvokeParameterizedMessageData, Function<Exception, String>> CONSTRUCTOR
) implements Function<InvokeCommonMessageParametersData, Function<Exception, String>> {

    @Override
    public Function<Exception, String> apply(InvokeCommonMessageParametersData data) {
        return ex -> CONSTRUCTOR.apply(new InvokeParameterizedMessageData(data.MESSAGE(), data.RETURN_TYPE(), data.PARAMETER_TYPES(), PARAMETER)).apply(ex);
    }
}
