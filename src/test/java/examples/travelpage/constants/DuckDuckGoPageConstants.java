package examples.travelpage.constants;

import com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.simple.SimpleLazyElementFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;

public abstract class DuckDuckGoPageConstants {
    public static final String NAME = "DuckDuckGo page - ";
    public static final String SEARCH_FIELD_NAME = NAME + "Search Field";
    public static final String SEARCH_FIELD_SELECTOR = "div input[id='searchbox_input']";

    public static final LazyElement SEARCH_FIELD = SimpleLazyElementFactory.getSimple(SEARCH_FIELD_NAME, SeleniumLazyLocatorFactory.getCSSSelector(SEARCH_FIELD_SELECTOR));
}
