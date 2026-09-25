package com.neathorium.thorium.framework.selenium.implementations.reflection.message;

import com.neathorium.thorium.framework.selenium.records.reflection.message.InvokeCommonMessageParametersData;

import java.util.function.Function;

public record RegularMessageData(
    Function<InvokeCommonMessageParametersData, Function<Exception, String>> CONSTRUCTOR
) implements Function<InvokeCommonMessageParametersData, Function<Exception, String>>  {
    @Override
    public Function<Exception, String> apply(InvokeCommonMessageParametersData data) {
        return ex -> CONSTRUCTOR.apply(data).apply(ex);
    }
}
