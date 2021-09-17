package com.neathorium.thorium.framework.selenium.namespaces;

import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;

public interface WaitPredicateFunctions {
    static <T> boolean isFalsyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isInvalidOrFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNullLazyElement((LazyElement)object)))
        ) || CoreUtilities.isFalse(object);
    }

    static <T> boolean isTruthyData(T object) {
        return (
            ((object instanceof Data) && (DataPredicates.isValidNonFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNotNullLazyElement((LazyElement)object)))
        ) || CoreUtilities.isTrue(object);
    }
}
