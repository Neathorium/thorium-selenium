package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.core.data.namespaces.DataFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

public interface WaitPredicateFunctions {
    static <T> boolean isFalsyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isInvalidOrFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNullLazyElement((LazyElement)object)))
        ) || BooleanUtilities.isFalse(object);
    }

    static <T> boolean isTruthyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isValidNonFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNotNullLazyElement((LazyElement)object)))
        ) || BooleanUtilities.isTrue(object);
    }

    static <T> boolean isTruthyAndObjectTruthyData(T object) {
        if ((object instanceof Data<?> data)) {
            return DataPredicates.isValidNonFalse(data) && BooleanUtilities.isTrue(DataFunctions.getObject(data));
        }
        return (
            ((object instanceof LazyElement) && (SeleniumUtilities.isNotNullLazyElement((LazyElement)object)))
        ) || BooleanUtilities.isTrue(object);
    }
}
