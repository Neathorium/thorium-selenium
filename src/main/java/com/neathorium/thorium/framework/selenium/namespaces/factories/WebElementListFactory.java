package com.neathorium.thorium.framework.selenium.namespaces.factories;

import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface WebElementListFactory {
    static WebElementList getWith(List<WebElement> list) {
        return new WebElementList(list);
    }

    static WebElementList getWithEmptyList() {
        return getWith(new ArrayList<>());
    }

    static WebElementList getWithSingleItem(WebElement element) {
        return new WebElementList(Collections.singletonList(element));
    }
}
