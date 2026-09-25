package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.core.records.caster.CastData;
import org.openqa.selenium.WebElement;

public abstract class SeleniumCastDataConstants {
    public static final CastData<WebElement, WebElement> WEB_ELEMENT = new CastData<>(SeleniumCoreConstants.STOCK_ELEMENT, SeleniumCoreConstants.WEB_ELEMENT_CASTER_FUNCTION);
}
