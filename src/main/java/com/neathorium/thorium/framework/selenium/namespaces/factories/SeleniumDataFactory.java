package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.java.extensions.classes.DecoratedList;

import java.util.function.Function;

public interface SeleniumDataFactory {
    static <T, V extends DecoratedList<?>> Data<DecoratedList<?>> getUnwrapped(Data<V> data) {
        return DataFactoryFunctions.getWith(data.OBJECT().get(), data.STATUS(), data.MESSAGE(), data.EXCEPTION(), data.EXCEPTION_MESSAGE());
    }

    static <T, V extends DecoratedList<T>> Function<Data<V>, Data<DecoratedList<?>>> getUnwrapped() {
        return data -> DataFactoryFunctions.getWith(data.OBJECT().get(), data.STATUS(), data.MESSAGE(), data.EXCEPTION(), data.EXCEPTION_MESSAGE());
    }
}
