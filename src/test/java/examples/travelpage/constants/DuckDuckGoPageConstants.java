package examples.travelpage.constants;

import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;

import static com.neathorium.thorium.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory.getID;
import static com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.simple.SimpleLazyElementFactory.getSimple;

public abstract class DuckDuckGoPageConstants {
    public static final String NAME = "DuckDuckGo page - ";
    public static final String SEARCH_FIELD_NAME = NAME + "Search Field";
    public static final String SEARCH_FIELD_SELECTOR = "search_form_input_homepage";

    public static final LazyElement SEARCH_FIELD = getSimple(SEARCH_FIELD_NAME, getID(SEARCH_FIELD_SELECTOR));
}
