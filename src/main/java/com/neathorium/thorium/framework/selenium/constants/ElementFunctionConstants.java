package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.framework.selenium.namespaces.driver.invoke.ElementInvokeFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.validators.SeleniumDataValidators;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementConditionParameters;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementParameterizedValueParameters;
import com.neathorium.thorium.framework.selenium.records.element.is.regular.ElementStringValueParameters;
import com.neathorium.thorium.core.extensions.namespaces.CardinalitiesFunctions;

public abstract class ElementFunctionConstants {
    public static final ElementConditionParameters<Boolean> PRESENT = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.PRESENT_DATA, SeleniumDataValidators::isValidElement, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> DISPLAYED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.DISPLAYED_DATA, ElementInvokeFunctions::isDisplayed, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> ENABLED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.ENABLED_DATA, ElementInvokeFunctions::isEnabled, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> CLICKABLE = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.CLICKABLE_DATA, ElementInvokeFunctions::isClickable, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> SELECTED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.SELECTED_DATA, ElementInvokeFunctions::isSelected, CardinalitiesFunctions::noopBoolean);

    public static final ElementConditionParameters<Boolean> ABSENT = new ElementConditionParameters<>(ExecutionCore::nonNullChain, ElementFormatDataConstants.ABSENT_DATA, SeleniumDataValidators::isNotNull, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> HIDDEN = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.HIDDEN_DATA, ElementInvokeFunctions::isDisplayed, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> DISABLED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.DISABLED_DATA, ElementInvokeFunctions::isEnabled, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> UNCLICKABLE = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.UNCLICKABLE_DATA, ElementInvokeFunctions::isClickable, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> UNSELECTED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.UNSELECTED_DATA, ElementInvokeFunctions::isSelected, CardinalitiesFunctions::invertBoolean);

    public static final ElementStringValueParameters<String> TEXT = new ElementStringValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.TEXT_DATA, ElementInvokeFunctions::getText);
    public static final ElementStringValueParameters<String> TAGNAME = new ElementStringValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.TAGNAME_DATA, ElementInvokeFunctions::getTagname);
    public static final ElementParameterizedValueParameters<String> ATTRIBUTE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.ATTRIBUTE_DATA, ElementInvokeFunctions::getAttribute);
    public static final ElementParameterizedValueParameters<String> VALUE_ATTRIBUTE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.VALUE_ATTRIBUTE_DATA, ElementInvokeFunctions::getAttribute);
    public static final ElementParameterizedValueParameters<String> CSS_VALUE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.CSS_VALUE_DATA, ElementInvokeFunctions::getCssValue);
}
