package com.neathorium.thorium.framework.selenium.records.scripter;

import java.util.function.Function;
import java.util.function.Predicate;

public record ScriptParametersData<T> (
    T PARAMETERS,
    Predicate<T> VALIDATOR,
    Function<T, Object[]> CONVERTER
){}
