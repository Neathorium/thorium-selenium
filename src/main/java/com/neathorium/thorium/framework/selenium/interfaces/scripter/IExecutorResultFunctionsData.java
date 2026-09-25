package com.neathorium.thorium.framework.selenium.interfaces.scripter;

import com.neathorium.thorium.core.data.records.Data;

import java.util.function.Function;

public record IExecutorResultFunctionsData<ParameterType, MessageParameterType, ReturnType>(
    Function<MessageParameterType, String> MESSAGE_HANDLER,
    Function<ParameterType, Data<ReturnType>> CAST_HANDLER
) {}
