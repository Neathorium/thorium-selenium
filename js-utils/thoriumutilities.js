'use strict';
/** THORIUM-UTILITIES */

const TU = {};
TU['CONSTANTS'] = {};
TU['FUNCTIONS'] = {
    'DATA': {},
    'ELEMENT': {
        GETTERS: {},
        CONDITIONS: {},
        UTILITIES: {}
    },
    'FORMATTERS': {
        'ELEMENT': {
            GETTERS: {},
            CONDITIONS: {}
        }
    },
    'UTILITIES': {},
    'BASIC_PREDICATES': {},
    'NUMBER_PREDICATES': {},
    'DATA_PREDICATES': {}
};

TU.CONSTANTS['STRINGS'] = {
    'NO_ELEMENTS_SELECTOR': 'DOESNT_EXIST_SELECTOR',
    'DEFAULT_NULL_ELEMENT_ID': 'DOESNT_EXIST_ID',
    'END_LINE': '.\n'
};

TU.CONSTANTS.STRINGS['NOT_AN_ELEMENT'] = 'Element passed is not an element' + TU.CONSTANTS.STRINGS.END_LINE;

TU.FUNCTIONS.ELEMENT.UTILITIES.GET_DEFAULT_NULL_ELEMENT = function getDefaultNullElementFunction() {
    const ELEMENT = document.createElement('div');
    ELEMENT.id = TU.CONSTANTS.STRINGS.DEFAULT_NULL_ELEMENT_ID;
    return ELEMENT;
};

TU.CONSTANTS.STRINGS['NON_EXCEPTION'] = 'Nonexception message' + TU.CONSTANTS.STRINGS.END_LINE;
TU.CONSTANTS.STRINGS['BASIC_EXCEPTION_OCCURED'] = 'An exception has occurred' + TU.CONSTANTS.STRINGS.END_LINE;

TU.CONSTANTS['TYPE_REFERENCES'] = {
    'OBJECT_TYPE_REFERENCE': typeof {}
};

TU.CONSTANTS['ELEMENT'] = {
    'EMPTY_ELEMENT_LIST': document.querySelectorAll(TU.CONSTANTS.STRINGS.NO_ELEMENTS_SELECTOR),
    'NULL_ELEMENT': TU.FUNCTIONS.ELEMENT.UTILITIES.GET_DEFAULT_NULL_ELEMENT()
};

TU.CONSTANTS['CORE'] = {
    'DEFAULT_NON_EXCEPTION': new Error(TU.CONSTANTS.STRINGS.NON_EXCEPTION)
};

/* PREDICATES */
/* ================================== */
TU.FUNCTIONS.BASIC_PREDICATES.IS_EQUAL = function isEqualFunction(object, expected) {
    return (object == expected);
};

TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_EQUAL = function isStrictEqualFunction(object, expected) {
    return (object === expected);
};

TU.FUNCTIONS.BASIC_PREDICATES.IS_UNEQUAL = function isUnequalFunction(object, expected) {
    return !TU.FUNCTIONS.BASIC_PREDICATES.IS_EQUAL(object, expected);
};

TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL = function isStrictUnequalFunction(object, expected) {
    return !TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_EQUAL(object, expected);
};

TU.FUNCTIONS.UTILITIES.IS_NONEXCEPTION = function isNonException(object) {
    return (object instanceof Error) && TU.FUNCTIONS.BASIC_PREDICATES.IS_EQUAL(object.message, TU.CONSTANTS.STRINGS.NON_EXCEPTION);
};

TU.FUNCTIONS.UTILITIES.IS_OBJECT = function isObjectFunction(object) {
    const type = typeof object;
    const IS_STRICT_EQUAL = TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_EQUAL;
    return (IS_STRICT_EQUAL(type, 'function') || (IS_STRICT_EQUAL(type, 'object') && !!object));
};

TU.FUNCTIONS.UTILITIES.IS_STRING = function isStringFunction(object) {
    return (TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_EQUAL(typeof object, 'string') || (object instanceof String));
};

TU.FUNCTIONS.UTILITIES.IS_NOT_UNDEFINED_OR_NULL = function isNotUndefinedOrNullFunction(object) {
    return ((typeof object !== 'undefined') && object);
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN = function isBiggerThanFunction(value, expected) {
    return (value > expected);
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_SMALLER_THAN = function isSmallerThanFunction(value, expected) {
    return TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN(expected, value);
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_ZERO = function isZeroFunction(value) {
    const IS_BIGGER_THAN = TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN;
    return (IS_BIGGER_THAN(1, value) && IS_BIGGER_THAN(value, -1));
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_NONZERO = function isZeroFunction(value) {
    return !TU.FUNCTIONS.NUMBER_PREDICATES.IS_ZERO(value);
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO = function isPositiveNonZeroFunction(value) {
    const IS_BIGGER_THAN = TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN;
    const IS_NONZERO = TU.FUNCTIONS.NUMBER_PREDICATES.IS_NONZERO;
    return IS_NONZERO(value) && IS_BIGGER_THAN(value, 0);
};

TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE = function isPositiveFunction(value) {
    return TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN(value, -1);
};

TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING = function isNonBlankStringFunction(object) {
    return (
        TU.FUNCTIONS.UTILITIES.IS_STRING(object) &&
        TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(object.length) &&
        TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(object.trim().length)
    );
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES = {};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT = function isValidElementFunction(element) {
    return (element instanceof Element) && TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL(element, TU.CONSTANTS.ELEMENT.NULL_ELEMENT);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_NULL_OR_FALSE_ELEMENT = function isNullOrFalseElementFunction(element) {
    return ((typeof element) === 'undefined') || (element == null) || !(element instanceof Element) || !TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL(element, TU.CONSTANTS.ELEMENT.NULL_ELEMENT);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST = function isValidElementListFunction(list) {
    return (list instanceof NodeList) && TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL(list, TU.CONSTANTS.ELEMENT.EMPTY_ELEMENT_LIST);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_DATA = function isValidElementDataFunction(data) {
    return (
        TU.FUNCTIONS.DATA_PREDICATES.IS_VALID_NON_FALSE_DATA(data) &&
        TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(data.object)
    );
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST_DATA = function isValidElementListDataFunction(data) {
    return (
        TU.FUNCTIONS.DATA_PREDICATES.IS_VALID_NON_FALSE_DATA(data) &&
        TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST(data.object)
    );
};

/* DATA FACTORY FUNCTIONS */
/* ================================== */
TU.FUNCTIONS.DATA.CREATE_DATA = function createDataFunction(
    object,
    status = false,
    name = 'createData',
    message = '',
    exception = TU.CONSTANTS.CORE.DEFAULT_NON_EXCEPTION,
    exceptionMessage = TU.CONSTANTS.STRINGS.NON_EXCEPTION
) {
    return {
        object,
        status,
        name,
        message,
        exception,
        exceptionMessage
    };
};

TU.FUNCTIONS.DATA.CREATE_INVALID_DATA = function createInvalidDataFunction(object, name, message, exception = TU.CONSTANTS.CORE.DEFAULT_NON_EXCEPTION, exceptionMessage = TU.CONSTANTS.STRINGS.NON_EXCEPTION) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(object, false, name, message, exception, exceptionMessage);
};

TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA = function createBooleanDataFunction(status, name, message, exception = TU.CONSTANTS.CORE.DEFAULT_NON_EXCEPTION, exceptionMessage = TU.CONSTANTS.STRINGS.NON_EXCEPTION) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(status, status, name, message, exception, exceptionMessage);
};
/* ================================== */
/* DATA FACTORY FUNCTIONS END */

/* DATA PREDICATES */
/* ================================== */
TU.FUNCTIONS.DATA_PREDICATES.IS_VALID_DATA = function isValidDataFunction(data) {
    const IS_OBJECT = TU.FUNCTIONS.UTILITIES.IS_OBJECT(data);
    if (!IS_OBJECT) {
        return false;
    }

    const hasObject = data.hasOwnProperty('object');
    const hasStatus = data.hasOwnProperty('status');
    const hasName = data.hasOwnProperty('name');
    const hasMessage = data.hasOwnProperty('message');
    const hasException = data.hasOwnProperty('exception');
    const hasExceptionMessage = data.hasOwnProperty('exceptionMessage');
    if (
        !hasObject ||
        !hasStatus ||
        !hasName ||
        !hasMessage ||
        !hasException ||
        !hasExceptionMessage
    ) {
        return false;
    }

    const OBJECT_NOT_DEFINED_OR_NULL = TU.FUNCTIONS.UTILITIES.IS_NOT_UNDEFINED_OR_NULL(data.object);
    const IS_STATUS_BOOLEAN = Boolean(data.status);
    const IS_NAME_STRING = TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(data.name);
    const IS_MESSAGE_STRING = TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(data.message);
    const IS_EXCEPTION_EXCEPTION = (data.exception instanceof Error);
    const IS_EXCEPTION_MESSAGE_STRING = TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(data.exceptionMessage);

    return (
        OBJECT_NOT_DEFINED_OR_NULL &&
        IS_STATUS_BOOLEAN &&
        IS_NAME_STRING &&
        IS_MESSAGE_STRING &&
        IS_EXCEPTION_EXCEPTION &&
        IS_EXCEPTION_MESSAGE_STRING
    );
};

TU.FUNCTIONS.DATA_PREDICATES.IS_VALID_NON_FALSE_DATA = function isValidNonFalseDataFunction(data) {
    return TU.FUNCTIONS.DATA_PREDICATES.IS_VALID_DATA(data) && data.status;
};

/* ================================== */
/* DATA PREDICATES END */
/* ================================== */
/* PREDICATES END */


/* DATA REPLACE UTILITY FUNCTIONS */
/* ================================== */
TU.FUNCTIONS.DATA.UTILITIES = {};
TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME = function replaceNameFunction(data, name) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(data.object, data.status, name, data.message, data.exception, data.exceptionMessage);
};

TU.FUNCTIONS.DATA.UTILITIES.REPLACE_MESSAGE = function replaceMessageFunction(data, message) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(data.object, data.status, data.name, message, data.exception, data.exceptionMessage);
};

TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME_AND_MESSAGE = function replaceNameAndMessageFunction(data, name, message) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(data.object, data.status, name, message, data.exception, data.exceptionMessage);
};

TU.FUNCTIONS.DATA.UTILITIES.REPLACE_OBJECT = function replaceObjectFunction(data, object) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(object, data.status, data.name, data.message, data.exception, data.exceptionMessage);
};

TU.FUNCTIONS.DATA.UTILITIES.REPLACE_OBJECT_NAME_AND_MESSAGE = function replaceObjectNameAndMessageFunction(data, object, name = data.name, message = data.message) {
    return TU.FUNCTIONS.DATA.CREATE_DATA(object, data.status, name, message, data.exception, data.exceptionMessage);
};
/* ================================== */
/* DATA REPLACE UTILITY FUNCTIONS END */

/* DATA CONSTANTS */
/* ================================== */
TU.CONSTANTS['DATA'] = {
    'ELEMENT_NOT_ELEMENT': TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(false, 'isDefaultNonElement', TU.CONSTANTS.STRINGS.NOT_AN_ELEMENT)
};
/* ================================== */
/* DATA CONSTANTS END */

TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA = function createConditionMessageDataFuction(functionName, conditionName, conditionFunction, invert = false, defaultValue = TU.CONSTANTS.DATA.ELEMENT_NOT_ELEMENT) {
    return {
        functionName,
        conditionName,
        conditionFunction,
        invert,
        defaultValue
    };
};

TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_ELEMENTS_MESSAGE = function getElementsMessageFunction(status, name, object, selector) {
    if (!status) {
        return TU.CONSTANTS.STRINGS.BASIC_EXCEPTION_OCCURED;
    }

    const length = object && object.length;
    const FOUND_ANY = TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(length);
    return (FOUND_ANY ? 'Elements("' + length + '")' : 'No elements') + ' were found with selector: ' + selector;
};

TU.FUNCTIONS.FORMATTERS.ELEMENT.CONDITIONS.IS_CONDITION_MESSAGE = function isConditionMessageFunction(name = '', condition = ' element condition', status = false, invert = false) {
    let lStatus = status;
    if (invert) {
        lStatus = !lStatus;
    }

    return name + ' Element was' + (lStatus ? '' : 'n\'t') + " " + condition + TU.CONSTANTS.STRINGS.END_LINE;
};

TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENTS = function getElementsFunction(name, selector) {
    let list = TU.CONSTANTS.ELEMENT.EMPTY_ELEMENT_LIST;
    let exception = TU.CONSTANTS.CORE.DEFAULT_NON_EXCEPTION;
    try {
        list = document.querySelectorAll(selector);
    } catch (error) {
        exception = error;
    }

    const status = TU.FUNCTIONS.UTILITIES.IS_NONEXCEPTION(exception);
    const message = TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_ELEMENTS_MESSAGE(status, name, list, selector);
    return TU.FUNCTIONS.DATA.CREATE_DATA(list, status, 'GET_ELEMENTS', message, exception);
};

TU.FUNCTIONS.ELEMENT.GETTERS.TEXT_CONTAINED = function textContainedFunction(listData, expected) {
    const NAME = 'textContained';
    let object = TU.CONSTANTS.ELEMENT.NULL_ELEMENT;
    if (
        !TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(expected) ||
        !TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST_DATA(listData)
    ) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA(object, NAME, 'There were parameter issues' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    const list = listData.object;
    const length = list.length;
    if (!TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(length)) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA(object, NAME, 'Text filtered element(\'' + expected + '\') not in empty list(\'' + length + '\')' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    let index = 0;
    for (; (index < length); ++index) {
        object = list[index];
        if (
            TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(object) &&
            TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN(object.textContent.indexOf(expected), -1)
        ) {
            break;
        }
    }

    const status = (
        TU.FUNCTIONS.NUMBER_PREDICATES.IS_BIGGER_THAN(length, index) &&
        TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(object)
    );
    return TU.FUNCTIONS.DATA.CREATE_DATA(object, status, NAME, 'Text filtered element(\'' + expected + '\') was' + (status ? '' : 'n\'t') + ' found in list' + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.ELEMENT.GETTERS.INDEX_EXISTS = function indexExistsFunction(listData, index) {
    const NAME = 'indexExists';
    let object = TU.CONSTANTS.ELEMENT.NULL_ELEMENT;
    if (
        !TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE(index) ||
        !TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST_DATA(listData)
    ) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA(object, NAME, 'There were parameter issues' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    const list = listData.object;
    const length = list.length;
    if (!TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(length)) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA(object, NAME, 'Indexed element(\'' + index + '\') not in empty list(\'' + length + '\')' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    object = list[index];
    const status = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(object);
    if (!status) {
        object = TU.CONSTANTS.ELEMENT.NULL_ELEMENT;
    }

    return TU.FUNCTIONS.DATA.CREATE_DATA(object, status, NAME, 'Indexed element(\'' + index + '\') was' + (status ? '' : 'n\'t') + ' found in list' + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_FILTERED = function getElementFilteredFunction(name, selector, filterFunction, filterValue) {
    const ELEMENTS = TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENTS(name, selector);
    if (!TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_LIST_DATA(ELEMENTS)) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA(TU.CONSTANTS.ELEMENT.NULL_ELEMENT, 'getElementFiltered', 'There were parameter issues' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    return filterFunction(ELEMENTS, filterValue);
};

TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_BY_INDEX = function getElementByIndexFunction(name, selector, index = 0) {
    return TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_FILTERED(name, selector, TU.FUNCTIONS.ELEMENT.GETTERS.INDEX_EXISTS, index);
};

TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT = function getElementFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_BY_INDEX(name, selector);
};

TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_BY_TEXT = function getElementByTextFunction(name, selector, text) {
    return TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_FILTERED(name, selector, TU.FUNCTIONS.ELEMENT.GETTERS.TEXT_CONTAINED, text);
};


TU.FUNCTIONS.ELEMENT.CLICK = function clickFunction(elementData) {
    const NAME = 'click';
    if (!elementData.status) {
        return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(elementData.status, NAME, 'Couldn\'t click element' + TU.CONSTANTS.STRINGS.END_LINE  + 'Reason: ' + elementData.message, elementData.exception, elementData.exceptionMessage);
    }

    const OBJECT = elementData.object;
    OBJECT.focus();
    OBJECT.click();
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(true, NAME, 'Element was clicked' + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.ELEMENT.SET_VALUE = function setValueFunction(elementData, value) {
    const NAME = 'setValue';
    if (!elementData.status) {
        return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(elementData.status, NAME, 'Element value wasn\'t set to (\'' + value + '\')' + TU.CONSTANTS.STRINGS.END_LINE  + 'Reason: ' + elementData.message, elementData.exception, elementData.exceptionMessage);
    }

    elementData.object.value = value;
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(elementData.status, NAME, 'Element value was set to (\'' + value + '\')' + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER = function getCoreFormatterFunction(status, name, value) {
    return 'Element ' + name + (status ? '(\'' + value + '\') was' : ' not') + ' found' + TU.CONSTANTS.STRINGS.END_LINE;
};

TU.FUNCTIONS.ELEMENT.GET_VALUE_CORE = function getValueCoreFunction(object) {
    return object.hasOwnProperty('value') ? object.value : '';
};

TU.FUNCTIONS.ELEMENT.GET_TEXT_CORE = function getTextCoreFunction(object) {
    return (object instanceof Node) ? object.textContent : '';
};

TU.FUNCTIONS.ELEMENT.GET_INNER_TEXT_CORE = function getTextCoreFunction(object) {
    return (object instanceof HTMLElement) ? object.innerText : '';
};

TU.FUNCTIONS.ELEMENT.GET_ATTRIBUTE_CORE = function getAttributeCoreFunction(object, attribute) {
    return object.hasAttribute(attribute) ? object.getAttribute(attribute) : '';
};

TU.FUNCTIONS.ELEMENT.GET_CSS_VALUE_CORE = function getCSSValueCoreFunction(object, value) {
    const STYLES = getComputedStyle(object);
    return styles[value] !== undefined ? styles[value] : '';
};

TU.FUNCTIONS.ELEMENT._GET_ATTRIBUTE = function getAttributeClosureFunction(attribute) {
    return TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(attribute) ? function getAttributeObjectFunction(object) {
        return TU.FUNCTIONS.ELEMENT.GET_ATTRIBUTE_CORE(object, attribute);
    } : function getAttributeNegativeFunction(object) {
        return '';
    };
};

TU.FUNCTIONS.ELEMENT._GET_CSS_VALUE = function getCSSValueClosureFunction(value) {
    return TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(value) ? function getCSSValueObjectFunction(object) {
        return TU.FUNCTIONS.ELEMENT.GET_CSS_VALUE_CORE(object, value);
    } : function getCSSValueNegativeFunction(object) {
        return '';
    };
};

TU.FUNCTIONS.ELEMENT.GET_CORE = function getCoreFunction(name, elementData, typeName, getter, formatterFunction) {
    const NAME = TU.FUNCTIONS.UTILITIES.IS_NON_BLANK_STRING(name) ? name : 'getCore';
    if (!elementData.status) {
        return TU.FUNCTIONS.DATA.CREATE_INVALID_DATA('', NAME, TU.CONSTANTS.STRINGS['NOT_AN_ELEMENT']  + 'Reason: ' + elementData.message, elementData.exception, elementData.exceptionMessage);
    }

    const VALUE = getter(elementData.object);
    return TU.FUNCTIONS.DATA.CREATE_DATA(VALUE, true, formatterFunction(true, typeName, VALUE), elementData.exception, elementData.exceptionMessage);
};

TU.FUNCTIONS.ELEMENT.GET_VALUE = function getValueFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.GET_CORE('getValue', elementData, 'value', TU.FUNCTIONS.ELEMENT.GET_VALUE_CORE, TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER);
};

TU.FUNCTIONS.ELEMENT.GET_ATTRIBUTE = function getAttributeFunction(elementData, attribute) {
    return TU.FUNCTIONS.ELEMENT.GET_CORE('getAttribute', elementData, 'attribute', TU.FUNCTIONS.ELEMENT._GET_ATTRIBUTE(attribute), TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER);
};

TU.FUNCTIONS.ELEMENT.GET_CSS_VALUE = function getCSSValueFunction(elementData, cssValue) {
    return TU.FUNCTIONS.ELEMENT.GET_CORE('getCSSValue', elementData, 'CSS Value', TU.FUNCTIONS.ELEMENT._GET_CSS_VALUE(attribute), TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER);
};

TU.FUNCTIONS.ELEMENT.GET_TEXT = function getTextFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.GET_CORE('getText', elementData, 'text', TU.FUNCTIONS.ELEMENT.GET_TEXT_CORE, TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER);
};

TU.FUNCTIONS.ELEMENT.GET_INNER_TEXT = function getInnerTextFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.GET_CORE('getInnerText', elementData, 'text', TU.FUNCTIONS.ELEMENT.GET_INNER_TEXT_CORE, TU.FUNCTIONS.FORMATTERS.ELEMENT.GETTERS.GET_CORE_FORMATTER);
};

TU.FUNCTIONS.ELEMENT.SET_ATTRIBUTE = function setAttributeFunction(elementData, attribute, value) {
    const NAME = 'setAttribute';
    if (!elementData.status) {
        return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(elementData.status, NAME, elementData.message, elementData.exception, elementData.exceptionMessage);
    }

    const OBJECT = elementData.object;
    OBJECT.setAttribute(attribute, value);
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(elementData.status, NAME, 'Element attribute(\'' + attribute + '\') set(\'' + value + '\')' +  + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_DISPLAYED_CONDITION = function isDisplayedConditionFunction(element) {
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(element);
    if (!IS_ELEMENT) {
        return false;
    }

    const STYLE = getComputedStyle(element);
    const DISPLAY_NOT_NONE = TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL(STYLE.display, 'none');
    const VISIBILITY_NOT_HIDDEN = TU.FUNCTIONS.BASIC_PREDICATES.IS_STRICT_UNEQUAL(STYLE.visibility, 'hidden');
    const CLIENT_HEIGHT_NOT_ZERO = TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(element.clientHeight);
    const RECTS_LENGTH_NOT_ZERO = TU.FUNCTIONS.NUMBER_PREDICATES.IS_POSITIVE_NONZERO(element.getClientRects().length);
    return status = (
        DISPLAY_NOT_NONE &&
        VISIBILITY_NOT_HIDDEN &&
        CLIENT_HEIGHT_NOT_ZERO &&
        RECTS_LENGTH_NOT_ZERO
    );
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_ENABLED_CONDITION = function isEnabledConditionFunction(element) {
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT(element);
    if (!IS_ELEMENT) {
        return false;
    }

    const CONTAINS_ENABLED = element.className.includes('enabled');
    const CONTAINS_DISABLED = element.className.includes('disabled');
    const HAS_ATTRIBUTE_ENABLED = Boolean(element.getAttribute('enabled'));
    const HAS_ATTRIBUTE_DISABLED = Boolean(element.getAttribute('disabled'));
    return status = (
        (CONTAINS_ENABLED && !CONTAINS_DISABLED) ||
        (HAS_ATTRIBUTE_ENABLED && !HAS_ATTRIBUTE_DISABLED) ||
        !(HAS_ATTRIBUTE_DISABLED || CONTAINS_DISABLED)
    );
};

TU.CONSTANTS.DATA['IS_PRESENT'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isPresent', 'present', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT);
TU.CONSTANTS.DATA['IS_DISPLAYED'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isDisplayed', 'displayed', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_DISPLAYED_CONDITION);
TU.CONSTANTS.DATA['IS_ENABLED'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isEnabled', 'enabled', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_ENABLED_CONDITION);
TU.CONSTANTS.DATA['IS_ABSENT'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isAbsent', 'absent', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_NULL_OR_FALSE_ELEMENT);
TU.CONSTANTS.DATA['IS_HIDDEN'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isHidden', 'hidden', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_DISPLAYED_CONDITION, true);
TU.CONSTANTS.DATA['IS_DISABLED'] = TU.FUNCTIONS.DATA.CREATE_CONDITION_MESSAGE_DATA('isDisabled', 'disabled', TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_ENABLED_CONDITION, true);

TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_ABSENT_BASE_CONDITION = function isConditionFunction(conditionData, elementData) {
    const element = !!elementData ? elementData.object : undefined;
    const status = conditionData.conditionFunction(element);
    const message = TU.FUNCTIONS.FORMATTERS.ELEMENT.CONDITIONS.IS_CONDITION_MESSAGE('', conditionData.conditionName, status, conditionData.invert);
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(conditionData.invert ? !status : status, conditionData.functionName, message);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_EXISTS_CONDITION = function isConditionFunction(conditionData, elementData) {
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_DATA(elementData);
    if (!IS_ELEMENT) {
        return TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME(conditionData.defaultValue, conditionData.functionName);
    }

    const element = elementData.object;
    const status = conditionData.conditionFunction(element);
    const message = TU.FUNCTIONS.FORMATTERS.ELEMENT.CONDITIONS.IS_CONDITION_MESSAGE('', conditionData.conditionName, status, conditionData.invert);
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(conditionData.invert ? !status : status, conditionData.functionName, message);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_CONDITION = function isConditionFunction(conditionData, elementData) {
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_DATA(elementData);
    if (!IS_ELEMENT) {
        return TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME(conditionData.defaultValue, conditionData.functionName);
    }

    const element = elementData.object;
    const status = conditionData.conditionFunction(element);
    const message = TU.FUNCTIONS.FORMATTERS.ELEMENT.CONDITIONS.IS_CONDITION_MESSAGE('', conditionData.conditionName, status, conditionData.invert);
    return TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA(conditionData.invert ? !status : status, conditionData.functionName, message);
};

/* ELEMENT CONDITION FUNCTIONS */
/* ================================== */
TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_PRESENT = function isPresentFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_EXISTS_CONDITION(TU.CONSTANTS.DATA.IS_PRESENT, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ABSENT = function isAbsentFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_ABSENT_BASE_CONDITION(TU.CONSTANTS.DATA.IS_ABSENT, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISPLAYED = function isDisplayedFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_CONDITION(TU.CONSTANTS.DATA.IS_DISPLAYED, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_HIDDEN = function isHiddenFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_CONDITION(TU.CONSTANTS.DATA.IS_HIDDEN, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ENABLED = function isEnabledFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_CONDITION(TU.CONSTANTS.DATA.IS_ENABLED, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISABLED = function isDisabledFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS._IS_CONDITION(TU.CONSTANTS.DATA.IS_DISABLED, elementData);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_CLICKABLE = function isClickableFunction(elementData) {
    const name = "isClickable";
    const CREATE_BOOLEAN_DATA =  TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA;
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_DATA(elementData);
    if (!IS_ELEMENT) {
        return CREATE_BOOLEAN_DATA(false, name, 'Element passed isn\'t an element' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    const IS_DISPLAYED = TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISPLAYED(elementData);
    if (!IS_DISPLAYED.status) {
        return TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME_AND_MESSAGE(IS_DISPLAYED, name, 'Element wasn\'t clickable' + TU.CONSTANTS.STRINGS.END_LINE + 'Reason: ' + IS_DISPLAYED.name + ': ' + IS_DISPLAYED.message);
    }

    const IS_ENABLED = TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ENABLED(elementData);
    if (!IS_DISPLAYED.status) {
        return TU.FUNCTIONS.DATA.UTILITIES.REPLACE_NAME_AND_MESSAGE(IS_ENABLED, name, 'Element wasn\'t clickable' + TU.CONSTANTS.STRINGS.END_LINE + 'Reason: ' + IS_ENABLED.name + ': ' + IS_ENABLED.message);
    }

    return CREATE_BOOLEAN_DATA(true, name, 'Element was clickable' + TU.CONSTANTS.STRINGS.END_LINE);
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_UNCLICKABLE = function isUnclickableFunction(elementData) {
    const name = "isUnclickable";
    const CREATE_BOOLEAN_DATA =  TU.FUNCTIONS.DATA.CREATE_BOOLEAN_DATA;
    const IS_ELEMENT = TU.FUNCTIONS.ELEMENT.CONDITIONS.UTILITIES.IS_VALID_ELEMENT_DATA(elementData);
    if (!IS_ELEMENT) {
        return CREATE_BOOLEAN_DATA(false, name, 'Element passed isn\'t an element' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    const element = elementData.object;
    const IS_DISPLAYED = TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISPLAYED(elementData);
    if (!IS_DISPLAYED.status) {
        return CREATE_BOOLEAN_DATA(true, name, 'Element was unclickable' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    const IS_ENABLED = TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ENABLED(elementData);
    if (!IS_DISPLAYED.status) {
        return CREATE_BOOLEAN_DATA(true, name, 'Element was unclickable' + TU.CONSTANTS.STRINGS.END_LINE);
    }

    return CREATE_BOOLEAN_DATA(false, name, 'Element wasn\'t unclickable' + TU.CONSTANTS.STRINGS.END_LINE);
};

/* ================================== */
/* ELEMENT CONDITION FUNCTIONS END */

/* SELECTOR FUNCTIONS START */
/* ================================== */
TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_PRESENT = function isPresentSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_PRESENT(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_ABSENT = function isAbsentSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ABSENT(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_DISPLAYED = function isDisplayedSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISPLAYED(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_HIDDEN = function isHiddenSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_HIDDEN(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_ENABLED = function isEnabledSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_ENABLED(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_DISABLED = function isDisabledSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_DISABLED(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_CLICKABLE = function isClickableSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_CLICKABLE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_UNCLICKABLE = function isUnclickableSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_UNCLICKABLE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.CLICK_SELECTOR = function clickSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.CLICK(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.GET_VALUE_SELECTOR = function getValueSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.GET_VALUE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.GET_CSS_VALUE_SELECTOR = function getCSSValueSelectorFunction(name, selector) {
    return TU.FUNCTIONS.ELEMENT.GET_CSS_VALUE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.GET_ATTRIBUTE_SELECTOR = function getAttributeSelectorFunction(name, selector, attribute) {
    return TU.FUNCTIONS.ELEMENT.GET_ATTRIBUTE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector), attribute);
};

TU.FUNCTIONS.ELEMENT.SET_VALUE_SELECTOR = function setValueSelectorFunction(name, selector, value) {
    return TU.FUNCTIONS.ELEMENT.SET_VALUE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector), value);
};

/*TU.FUNCTIONS.ELEMENT.SET_CSS_VALUE_SELECTOR = function setCSSValueSelectorFunction(name, selector, value) {
    return TU.FUNCTIONS.ELEMENT.SET_CSS_VALUE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector), value);
};*/

TU.FUNCTIONS.ELEMENT.SET_ATTRIBUTE_SELECTOR = function setAttributeSelectorFunction(name, selector, attribute, value) {
    return TU.FUNCTIONS.ELEMENT.SET_ATTRIBUTE(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector), attribute, value);
};

TU.FUNCTIONS.ELEMENT.GET_TEXT_SELECTOR = function getTextSelectorFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.GET_TEXT(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.FUNCTIONS.ELEMENT.GET_INNER_TEXT_SELECTOR = function getInnerTextSelectorFunction(elementData) {
    return TU.FUNCTIONS.ELEMENT.GET_INNER_TEXT(TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT(name, selector));
};

TU.GE = TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT;
TU.GES = TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENTS;
TU.GEBI = TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_BY_INDEX;
TU.GEBT = TU.FUNCTIONS.ELEMENT.GETTERS.GET_ELEMENT_BY_TEXT;

/* ================================== */
/* SELECTOR FUNCTIONS END */
