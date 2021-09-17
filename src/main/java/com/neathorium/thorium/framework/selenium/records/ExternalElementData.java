package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.core.extensions.DecoratedList;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.abstracts.AbstractExternalElementData;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class ExternalElementData extends AbstractExternalElementData<WebElement> {
    public ExternalElementData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<WebElement> found) {
        super(typeKeys, found);
    }
}
