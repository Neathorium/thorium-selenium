package com.neathorium.thorium.framework.selenium.repositories.method.constants;

import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.repositories.method.records.MethodData;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public abstract class MethodRepositoryConstants {
    public static final HashMap<String, MethodData> METHODS = new HashMap<>();
    public static final Method STOCK_METHOD = Object.class.getDeclaredMethods()[0];

    public static final List<Class<?>> STOCK_METHOD_PARAMETER_TYPES = List.of(Void.class);
    public static final MethodData STOCK_METHOD_DATA = new MethodData(STOCK_METHOD, MethodRepositoryConstants.STOCK_METHOD_PARAMETER_TYPES.toString(), CoreFormatterConstants.VOID_CLASS_GENERIC);
    public static final Data<MethodData> NULL_METHODDATA = DataFactoryFunctions.getWith(MethodRepositoryConstants.STOCK_METHOD_DATA, false, "nullMethodData", "Null methodData data.");
}
