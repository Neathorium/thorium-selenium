package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;

import java.util.List;
import java.util.function.BiFunction;
public record ExecuteParameterizedData(
    ScriptFunction<BiFunction<String, Object[], Object>> HANDLER,
    List<Object> PARAMETERS
) {}