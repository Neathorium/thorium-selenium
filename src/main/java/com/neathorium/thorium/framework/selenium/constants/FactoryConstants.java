package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.compatibility.NullJavascriptExecutor;
import com.neathorium.thorium.framework.selenium.compatibility.NullSearchContext;
import com.neathorium.thorium.framework.selenium.compatibility.NullTakesScreenshot;
import com.neathorium.thorium.framework.selenium.compatibility.NullTargetLocator;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public abstract class FactoryConstants {
    public static final JavascriptExecutor NULL_JAVASCRIPT_EXECUTOR = new NullJavascriptExecutor();
    public static final TakesScreenshot NULL_TAKES_SCREENSHOT = new NullTakesScreenshot();
    public static final WebDriver.TargetLocator NULL_TARGET_LOCATOR = new NullTargetLocator();
    public static final SearchContext NULL_SEARCH_CONTEXT = new NullSearchContext();

    public static final Data<JavascriptExecutor> NULL_JAVASCRIPT_EXECUTOR_DATA = DataFactoryFunctions.getWith(NULL_JAVASCRIPT_EXECUTOR, false, "Dependency for JavascriptExecutor" + CoreFormatterConstants.WAS_NULL);
    public static final Data<TakesScreenshot> NULL_TAKES_SCREENSHOT_DATA = DataFactoryFunctions.getWith(NULL_TAKES_SCREENSHOT, false, "Dependency for TakesScreenshot" + CoreFormatterConstants.WAS_NULL);
    public static final Data<WebDriver.TargetLocator> NULL_TARGET_LOCATOR_DATA = DataFactoryFunctions.getWith(NULL_TARGET_LOCATOR, false, "Dependency for TargetLocator" + CoreFormatterConstants.WAS_NULL);
    public static final Data<SearchContext> NULL_SEARCH_CONTEXT_DATA = DataFactoryFunctions.getWith(NULL_SEARCH_CONTEXT, false, "Dependency for SearchContext" + CoreFormatterConstants.WAS_NULL);
}
