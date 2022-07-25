package com.neathorium.thorium.framework.selenium.records;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.core.abstracts.AbstractExternalElementData;
import com.neathorium.thorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.thorium.java.extensions.classes.DecoratedList;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class ExternalElementData extends AbstractExternalElementData<WebElement> {
    public ExternalElementData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<WebElement> found) {
        super(typeKeys, found);
    }
}
