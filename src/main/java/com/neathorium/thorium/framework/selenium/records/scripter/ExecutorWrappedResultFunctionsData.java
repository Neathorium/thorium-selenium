package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.core.data.records.Data;

import java.util.function.Function;

public class ExecutorWrappedResultFunctionsData<ParameterType, MessageParameterType, ReturnType> extends ExecutorResultFunctionsData<ParameterType, MessageParameterType, Data<ReturnType>> {
    public ExecutorWrappedResultFunctionsData(Function<MessageParameterType, String> messageHandler, Function<ParameterType, Data<ReturnType>> castHandler) {
        super(messageHandler, castHandler);
    }
}
