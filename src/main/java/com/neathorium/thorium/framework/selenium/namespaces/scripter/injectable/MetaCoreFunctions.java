package com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable;

import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.framework.selenium.constants.scripter.injectable.MetaCoreConstants;
import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;

public interface MetaCoreFunctions {
    static String get(String base, LazyElement element, String optional, String end) {
        final var selector =  LazyElementUtilities.getCSSSelectorFromElement(element);
        final var filterData = LazyElementUtilities.getIndexedData(element);
        var elementString = filterData.MESSAGE().MESSAGE() + "('" + element.NAME + "', '" + selector;
        if (DataPredicates.isValidNonFalse(filterData)) {
            elementString += "', " + (EqualsPredicates.isEqual(filterData.MESSAGE().MESSAGE(), "TU.GETI") ? filterData.OBJECT() : "'" + filterData.OBJECT() + "'") + ")";
        } else {
            elementString = "')";
        }
        return base + "(" + elementString + optional + end;
    }

    static String getBoolean(String base, LazyElement element) {
        return MetaCoreFunctions.get(base, element, CoreFormatterConstants.EMPTY, MetaCoreConstants.BOOLEAN_COMMAND_END);
    }

    static String getBoolean(String base, LazyElement element, String valueSource) {
        return MetaCoreFunctions.get(base, element, SeleniumFormatterConstants.COLON_SINGLE_QUOTE + valueSource + "'", MetaCoreConstants.BOOLEAN_COMMAND_END);
    }

    static String getBoolean(String base, LazyElement element, String valueSource, String assignedValue) {
        return MetaCoreFunctions.get(base, element, SeleniumFormatterConstants.COLON_SINGLE_QUOTE + valueSource  + SeleniumFormatterConstants.COLON_SINGLE_QUOTE + assignedValue + "'", MetaCoreConstants.BOOLEAN_COMMAND_END);
    }

    static String getString(String base, LazyElement element) {
        return MetaCoreFunctions.get(base, element, CoreFormatterConstants.EMPTY, MetaCoreConstants.STRING_COMMAND_END);
    }

    static String getString(String base, LazyElement element, String valueSource) {
        return MetaCoreFunctions.get(base, element, SeleniumFormatterConstants.COLON_SINGLE_QUOTE + valueSource + "'", MetaCoreConstants.STRING_COMMAND_END);
    }
}
