package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.java.extensions.classes.DecoratedList;
import com.neathorium.thorium.java.extensions.namespaces.factories.DecoratedListFactory;

import java.util.Arrays;

public abstract class SeleniumGetOrderConstants {
    public static final DecoratedList<String> ID_CSSSELECTOR = DecoratedListFactory.getWith(Arrays.asList("id", "cssSelector", "nested", "class", "xpath"), String.class);
    public static final DecoratedList<String> CSSSELECTOR_ID = DecoratedListFactory.getWith(Arrays.asList("cssSelector", "id", "nested", "class", "xpath"), String.class);
    public static final DecoratedList<String> DEFAULT = ID_CSSSELECTOR;
}
