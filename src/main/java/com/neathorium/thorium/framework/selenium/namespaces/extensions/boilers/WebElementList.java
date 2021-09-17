package com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.thorium.core.extensions.DecoratedList;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementList extends DecoratedList<WebElement> {
    public WebElementList(List<WebElement> list) {
        super(list, WebElement.class.getTypeName());
    }

    public WebElementList subList(int fromIndex, int toIndex) {
        return new WebElementList(super.subList(fromIndex, toIndex));
    }

    public WebElementList tail() {
        return new WebElementList(super.tail());
    }

    public WebElementList initials() {
        return new WebElementList(super.initials());
    }
}
