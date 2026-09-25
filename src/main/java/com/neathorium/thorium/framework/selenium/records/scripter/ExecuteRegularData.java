package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;

import java.util.function.Function;

public record ExecuteRegularData (
    ScriptFunction<Function<String, Object>> HANDLER
) {}
