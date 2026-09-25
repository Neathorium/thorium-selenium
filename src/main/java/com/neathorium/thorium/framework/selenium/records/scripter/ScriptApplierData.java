package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import org.openqa.selenium.JavascriptExecutor;

public record ScriptApplierData<DataType>(
    DataType DATA,
    TriFunction<JavascriptExecutor, DataType, String, Object> APPLIER
) {}