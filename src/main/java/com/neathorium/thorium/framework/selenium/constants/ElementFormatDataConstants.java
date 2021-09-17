package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.validators.element.ElementFormatters;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;

public abstract class ElementFormatDataConstants {
    public static final ElementFormatData<Boolean> PRESENT_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementPresent", SeleniumFormatterConstants.PRESENT);
    public static final ElementFormatData<Boolean> DISPLAYED_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementDisplayed", SeleniumFormatterConstants.DISPLAYED);
    public static final ElementFormatData<Boolean> ENABLED_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementEnabled", SeleniumFormatterConstants.ENABLED);
    public static final ElementFormatData<Boolean> CLICKABLE_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementClickable", SeleniumFormatterConstants.CLICKABLE);
    public static final ElementFormatData<Boolean> SELECTED_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementSelected", SeleniumFormatterConstants.SELECTED);

    public static final ElementFormatData<Boolean> ABSENT_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementAbsent", SeleniumFormatterConstants.ABSENT);
    public static final ElementFormatData<Boolean> HIDDEN_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementHidden", SeleniumFormatterConstants.HIDDEN);
    public static final ElementFormatData<Boolean> DISABLED_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementDisabled", SeleniumFormatterConstants.DISABLED);
    public static final ElementFormatData<Boolean> UNCLICKABLE_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementUnclickable", SeleniumFormatterConstants.UNCLICKABLE);
    public static final ElementFormatData<Boolean> UNSELECTED_DATA = new ElementFormatData<>(ElementFormatters::getConditionMessage, "isElementUnselected", SeleniumFormatterConstants.UNSELECTED);

    public static final ElementFormatData<String> TEXT_DATA = new ElementFormatData<>(ElementFormatters::getElementValueMessage, "getElementText", SeleniumFormatterConstants.TEXT);
    public static final ElementFormatData<String> TAGNAME_DATA = new ElementFormatData<>(ElementFormatters::getElementValueMessage, "getElementTagName", SeleniumFormatterConstants.TAGNAME);
    public static final ElementFormatData<String> ATTRIBUTE_DATA = new ElementFormatData<>(ElementFormatters::getElementValueMessage, "getElementAttribute", SeleniumFormatterConstants.ATTRIBUTE);
    public static final ElementFormatData<String> VALUE_ATTRIBUTE_DATA = new ElementFormatData<>(ElementFormatters::getElementValueMessage, "getElementValueAttribute", SeleniumFormatterConstants.VALUE_ATTRIBUTE);
    public static final ElementFormatData<String> CSS_VALUE_DATA = new ElementFormatData<>(ElementFormatters::getElementValueMessage, "getElementCssValue", SeleniumFormatterConstants.CSS_VALUE);
}
