package com.neathorium.thorium.framework.selenium.records.element.is;


import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;

public record ElementFormatData<T>(TriFunction<String, String, T, String> FORMATTER, String CONDITION_NAME, String DESCRIPTOR) {}
