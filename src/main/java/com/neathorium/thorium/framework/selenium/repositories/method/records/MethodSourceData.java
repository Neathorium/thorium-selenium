package com.neathorium.thorium.framework.selenium.repositories.method.records;

import com.neathorium.thorium.core.data.records.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public record MethodSourceData(HashMap<String, MethodData> METHOD_MAP, List<Method> LIST, Data<MethodData> DEFAULT_VALUE) {}
