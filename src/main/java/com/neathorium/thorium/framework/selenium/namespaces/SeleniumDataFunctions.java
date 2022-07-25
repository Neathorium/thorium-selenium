package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.java.extensions.namespaces.predicates.DecoratedListPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.function.Predicate;


public interface SeleniumDataFunctions {
    private static <T> boolean isOfTypeNonEmpty(Data<WebElementList> listData, Class<T> clazz) {
        return (
            DataPredicates.isValidNonFalse(listData) &&
            NullablePredicates.isNotNull(clazz) &&
            DecoratedListPredicates.isOfTypeNonEmpty(listData.OBJECT(), clazz)
        );
    }

    static <T> Predicate<Data<WebElementList>> isOfTypeNonEmpty(Class<T> clazz) {
        return list -> isOfTypeNonEmpty(list, clazz);
    }
}
