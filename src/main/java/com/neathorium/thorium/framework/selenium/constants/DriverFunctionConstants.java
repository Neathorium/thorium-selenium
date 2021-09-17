package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.enums.SeleniumTypeKey;
import com.neathorium.thorium.framework.selenium.namespaces.element.validators.ElementGetterValidators;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.records.GetElementByData;
import com.neathorium.thorium.framework.selenium.records.SeleniumTypedEnumKeyData;
import com.neathorium.thorium.core.constants.CoreDataConstants;
import com.neathorium.thorium.core.extensions.boilers.StringSet;
import com.neathorium.thorium.core.namespaces.StringUtilities;
import com.neathorium.thorium.core.records.Data;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class DriverFunctionConstants {
    public static final DriverFunction<Void> NULL_VOID = DriverFunctionFactory.get(CoreDataConstants.NULL_VOID);
    public static final DriverFunction<Object> NULL_OBJECT = DriverFunctionFactory.get(CoreDataConstants.NULL_OBJECT);
    public static final DriverFunction<Boolean> NULL_BOOLEAN = DriverFunctionFactory.get(CoreDataConstants.NULL_BOOLEAN);
    public static final DriverFunction<String> NULL_STRING = DriverFunctionFactory.get(CoreDataConstants.NULL_STRING);
    public static final DriverFunction<Integer> NULL_INTEGER = DriverFunctionFactory.get(CoreDataConstants.NULL_INTEGER);
    public static final DriverFunction<StringSet> NULL_STRINGSET = DriverFunctionFactory.get(CoreDataConstants.NULL_STRING_SET);
    public static final DriverFunction<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = DriverFunctionFactory.get(SeleniumDataConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL);
    public static final DriverFunction<Boolean> LAZY_ELEMENT_WAS_NULL = DriverFunctionFactory.get(SeleniumDataConstants.LAZY_ELEMENT_WAS_NULL);
    public static final DriverFunction<WebElement> NULL_WEBELEMENT = DriverFunctionFactory.get(SeleniumDataConstants.NULL_ELEMENT);
    public static final DriverFunction<WebElementList> NULL_LIST = DriverFunctionFactory.get(SeleniumDataConstants.NULL_LIST);

    public static final Map<SeleniumTypeKey, DriverFunction<?>> FUNCTION_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SeleniumTypeKey.BOOLEAN, NULL_BOOLEAN),
                entry(SeleniumTypeKey.INTEGER, NULL_INTEGER),
                entry(SeleniumTypeKey.VOID, NULL_VOID),
                entry(SeleniumTypeKey.WEB_ELEMENT, NULL_WEBELEMENT),
                entry(SeleniumTypeKey.STRING, NULL_STRING),
                entry(SeleniumTypeKey.STRING_SET, NULL_STRINGSET),
                entry(SeleniumTypeKey.WEB_ELEMENT_LIST, NULL_LIST),
                entry(SeleniumTypeKey.OBJECT, NULL_OBJECT)
            )
        )
    );

    public static final SeleniumTypedEnumKeyData<Boolean> BOOLEAN_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.BOOLEAN, Boolean.class);
    public static final SeleniumTypedEnumKeyData<Integer> INTEGER_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.INTEGER, Integer.class);
    public static final SeleniumTypedEnumKeyData<String> STRING_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.STRING, String.class);
    public static final SeleniumTypedEnumKeyData<StringSet> STRING_SET_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.STRING_SET, StringSet.class);
    public static final SeleniumTypedEnumKeyData<Object> OBJECT_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.OBJECT, Object.class);
    public static final SeleniumTypedEnumKeyData<Void> VOID_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.VOID, Void.class);
    public static final SeleniumTypedEnumKeyData<WebElement> WEB_ELEMENT_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.WEB_ELEMENT, WebElement.class);
    public static final SeleniumTypedEnumKeyData<WebElementList> WEB_ELEMENT_LIST_FUNCTION_KEY = new SeleniumTypedEnumKeyData<>(SeleniumTypeKey.WEB_ELEMENT_LIST, WebElementList.class);

    public static final Map<Class<?>, SeleniumTypeKey> TYPE_MAP = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry(String.class, SeleniumTypeKey.STRING),
                entry(Boolean.class, SeleniumTypeKey.BOOLEAN)
            )
        )
    );

    private static WebElement getByIndex(Data<WebElementList> data, int index) {
        return data.object.get(index);
    }

    private static WebElement getByContainedText(Data<WebElementList> data, String text) {
        final var list = data.object;
        final var size = list.size();
        var object = SeleniumCoreConstants.STOCK_ELEMENT;
        var index = 0;
        for (; (index < size); ++index) {
            object = list.get(index);
            if (StringUtilities.contains(object.getText(), text)) {
                break;
            }
        }

        return object;
    }

    public static final GetElementByData<String, WebElement, WebElementList> BY_CONTAINED_TEXT_CONSTANTS = new GetElementByData<>(
        "getElementByContainedText",
        ElementGetterValidators::isInvalidElementByTextParameters,
        DriverFunctionConstants::getByContainedText,
        FrameworkCoreFormatter::getByFilterMessage,
        SeleniumDataConstants.NULL_ELEMENT,
        "Text"
    );

    public static final GetElementByData<Integer, WebElement, WebElementList> BY_INDEX_CONSTANTS = new GetElementByData<>(
        "getElementByIndex",
        ElementGetterValidators::isInvalidElementByIndexParameters,
        DriverFunctionConstants::getByIndex,
        FrameworkCoreFormatter::getByFilterMessage,
        SeleniumDataConstants.NULL_ELEMENT,
        "Index"
    );
}
