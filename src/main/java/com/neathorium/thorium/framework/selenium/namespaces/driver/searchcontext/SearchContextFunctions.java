package com.neathorium.thorium.framework.selenium.namespaces.driver.searchcontext;

import com.neathorium.thorium.framework.selenium.constants.FactoryConstants;
import com.neathorium.thorium.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import com.neathorium.thorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SearchContextFunctions {
    private static <T extends SearchContext> SearchContext getSearchContextOf(T object) {
        return object;
    }

    static <T extends SearchContext> Data<SearchContext> getSearchContextOf(String dependencyName, Data<T> data) {
        return DataPredicates.isValidNonFalse(data) ? (
            DriverTypeConversionFunctions.getSubtypeOf(dependencyName, data.object, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT)
        ) : FactoryConstants.NULL_SEARCH_CONTEXT_DATA;
    }

    static Data<SearchContext> getSearchContext(WebElement element) {
        return DriverTypeConversionFunctions.getSubtypeOf("Search Context (Element)", element, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }

    static Data<SearchContext> getSearchContext(WebDriver driver) {
        return DriverTypeConversionFunctions.getSubtypeOf("Search Context (Driver)", driver, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }
}
