package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;

import java.util.function.Function;

public interface SeleniumDataFactory {
    static <T, V extends DecoratedList<?>> Data<DecoratedList<?>> getUnwrapped(Data<V> data) {
        return DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }

    static <T, V extends DecoratedList<T>> Function<Data<V>, Data<DecoratedList<?>>> getUnwrapped() {
        return data -> DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }
}
