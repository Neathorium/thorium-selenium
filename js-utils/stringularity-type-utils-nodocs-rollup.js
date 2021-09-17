'use strict';
const STRINGS = {
        stringTrue: 'true',
        stringFalse: 'false'
    },
    NUMBERS = {
        MINUSONE: -1,
        ZERO: 0,
        ONE: 1,
        TWO: 2,
        THREE: 3,
        FOUR: 4,
        FIVE: 5,
        SIX: 6,
        SEVEN: 7,
        EIGHT: 8,
        NINE: 9,
        TEN: 10,
        NAN: Number.NaN
    },
    BOOLS = {
        TRUE: true,
        FALSE: false
    },
    dontEnumBugObject = {toString: null},
    hasDontEnumBug = !dontEnumBugObject.propertyIsEnumerable('toString'),
    dontEnums = [
        'toString',
        'toLocaleString',
        'valueOf',
        'hasOwnProperty',
        'isPrototypeOf',
        'propertyIsEnumerable',
        'constructor'
    ],
    dontEnumsLength = dontEnums.length,
    STU = {
        hasOwnProperty: Object.prototype.hasOwnProperty,
        definedExclusionsObject: {
            baseDefined: [
                undefined
            ],
            looselyDefined: [
                undefined,
                null
            ],
            strictlyDefined: [
                undefined,
                null,
                BOOLS.FALSE
            ],
            truthyDefined: [
                undefined,
                null,
                BOOLS.FALSE,
                NUMBERS.ZERO,
                NUMBERS.NAN,
                ''
            ]
        }
    };

STRINGS.stringBooleanValues = [
    STRINGS.stringTrue,
    STRINGS.stringFalse
];

STU.isString = function isStringFunction(object) {
    return typeof object === 'string';
};

STU.isType = function isTypeFunction(object, type) {
    return STU.isString(type) ? typeof object === type : BOOLS.FALSE;
};

STU.isBasicObject = function isBasicObjectFunction(object) {
    return STU.isType(object, 'object');
};

STU.isPrimitiveBoolean = function isPrimitiveBooleanFunction(object) {
    return STU.isType(object, 'boolean');
};

STU.isFunction = function isFunctionFunction(object) {
    return STU.isType(object, 'function');
};

STU.isNumber = function isNumberFunction(object) {
    return STU.isType(object, 'number');
};

STU.isRounded = function isRoundedFunction(object) {
    return isFinite(object) && (Math.floor(object) === object);
};

STU.isInteger = function isIntegerFunction(object) {
    return STU.isNumber(object) && STU.isRounded(object);
};

STU.isNotRounded = function isNotRoundedFunction(object) {
    return !STU.isRounded(object);
};

STU.isFloat = function isFloatFunction(object) {
    return STU.isNumber(object) && isFinite(object) && (Math.floor(object) !== object);
};

STU.isArray = function isArrayFunction(object) {
    const isArray = Array.isArray;
    return isArray && isArray(object) ? BOOLS.TRUE : object instanceof Array;
};

STU.isNotArray = function isNotArrayFunction(object) {
    return !STU.isArray(object);
};

STU.isBasicNonArrayObject = function isBasicNonArrayObjectFunction(object) {
    return STU.isNotArray(object) && STU.isBasicObject(object);
};

STU.isNotPrimitiveBoolean = function isNotPrimitiveBooleanFunction(object) {
    return !STU.isPrimitiveBoolean(object);
};

STU.isNotFunction = function isNotFunctionFunction(object) {
    return !STU.isFunction(object);
};

STU.isNotNumber = function isNotNumberFunction(object) {
    return !STU.isNumber(object);
};

STU.isNotInteger = function isNotIntegerFunction(object) {
    return !STU.isInteger(object);
};

STU.isNotFloat = function isNotFloatFunction(object) {
    return !STU.isFloat(object);
};

STU.isNotString = function isNotStringFunction(object) {
    return !STU.isString(object);
};

STU.isEmptyString = function isEmptyStringFunction(object) {
    return STU.isString(object) && (object.length === NUMBERS.ZERO);
};

STU.isNonEmptyString = function isNonEmptyStringFunction(object) {
    return STU.isString(object) && (object.length > NUMBERS.ZERO);
};

STU.isNotBlankString = function isNotBlankStringFunction(object) {
    return STU.isNonEmptyString(object) && object.trim();
};

STU.isBlankString = function isBlankStringFunction(object) {
    return STU.isNonEmptyString(object) && !object.trim();
}

STU.isDefinedCore = function isDefinedCoreFunction(object, defineType, defineExclusionsObject) {
    var localDefineExclusionsObject = STU.isBasicNonArrayObject(defineExclusionsObject) ? defineExclusionsObject : STU.definedExclusionsObject,
        localDefineType = STU.isNonEmptyString(defineType) ? defineType : 'looselyDefined';

    if (!STU.hasOwnProperty.call(localDefineExclusionsObject, localDefineType)) {
        return BOOLS.FALSE;
    }

    return localDefineExclusionsObject[localDefineType].indexOf(object) === NUMBERS.MINUSONE;
};

STU.isBaseDefined = function isBaseDefinedFunction(object) {
    return STU.isDefinedCore(object, 'baseDefined');   
};

STU.isLooselyDefined = function isLooselyDefinedFunction(object) {
    return STU.isDefinedCore(object, 'looselyDefined');
};

STU.isStrictlyDefined = function isStrictlyDefinedFunction(object) {
    return STU.isDefinedCore(object, 'strictlyDefined');
};

STU.isTruthyDefined = function isTruthyDefinedFunction(object) {
    return STU.isDefinedCore(object, 'truthyDefined');
};

STU.isNotNonEmptyString = function isNotNonEmptyStringFunction(object) {
    return !STU.isNonEmptyString(object);
};

STU.isNonEmptyArray = function isNonEmptyArrayFunction(object) {
    return STU.isArray(object) && (object.length > NUMBERS.ZERO);
};

STU.isNotNonEmptyArray = function isNotNonEmptyArrayFunction(object) {
    return !STU.isNonEmptyArray(object);
};

STU.isEmptyArray = function isEmptyArrayFunction(object) {
    return STU.isArray(object) && (object.length === NUMBERS.ZERO);
};

STU.isSingleElementArray = function isSingleElementArrayFunction(object) {
    return STU.isNonEmptyArray(object) && (object.length === NUMBERS.ONE);
};

STU.isSingleNonEmptyStringArray = function isSingleNonEmptyStringArrayFunction(object) {
    return STU.isSingleElementArray(object) && STU.isNonEmptyString(object[NUMBERS.ZERO]);
};

STU.getSingleNonEmptyStringFromArray = function getSingleNonEmptyStringFromArrayFunction(object) {
    return STU.isSingleNonEmptyStringArray(object) ? object[NUMBERS.ZERO] : '';
};

STU.invertBoolean = function invertBooleanFunction(object) {
    return (
        STU.isFunction(object)
    ) ? function partialInvertBoolFunction(_object) {
        var localExpression = object(_object);
        return STU.isPrimitiveBoolean(localExpression) ? !localExpression : BOOLS.FALSE;
    } : function partialTotalInvertBoolFunction() {
        return STU.isPrimitiveBoolean(object) ? !object : BOOLS.FALSE;
    };
};

STU.cardinalityObject = {
    any: {
        guardValue: BOOLS.TRUE,
        finalValue: BOOLS.FALSE
    },
    all: {
        conditionModifier: STU.invertBoolean,
        guardValue: BOOLS.FALSE,
        finalValue: BOOLS.TRUE
    },
    none: {
        guardValue: BOOLS.FALSE,
        finalValue: BOOLS.TRUE
    }
};

STU.isUndefinedCore = function isUndefinedCoreFunction(object, defineType, defineExclusionsObject) {
    return !STU.isDefinedCore(object, defineType, defineExclusionsObject);
};

STU.isUndefined = function isUndefinedFunction(object) {
    return STU.isUndefinedCore(object, 'looselyDefined');
};

STU.isObject = function isObjectFunction(object) {
    return STU.isBasicNonArrayObject(object) && STU.isStrictlyDefined(object);
};

STU.isArgumentsObject = function isArgumentsObjectFunction(object) {
    return (
        STU.isBasicNonArrayObject(object) &&
        STU.isStrictlyDefined(object) &&
        (object.toString().indexOf('Arguments') > NUMBERS.MINUSONE)
    );
};

STU.isSingleElementObjectArray = function isSingleElementObjectArrayFunction(object) {
    return STU.isSingleElementArray(object) && STU.isObject(object[NUMBERS.ZERO]);
};

STU.objectKeys = Object.keys || function objectKeysFunction(object) {
    var hasOwnProperty = STU.hasOwnProperty,
        currentDontEnum,
        property,
        array,
        index;

    if (!(
        STU.isStrictlyDefined(object) &&
        (
            STU.isObject(object) ||
            STU.isFunction(object)
        )
    )) {
        throw new TypeError('Object.keys called on non-object');
    }

    array = [];

    for (property in object) {
        if (hasOwnProperty.call(object, property)) {
            array.push(property);
        }
    }

    if (!hasDontEnumBug) {
        return array;
    }

    for (index = 0; index < dontEnumsLength; ++index) {
        currentDontEnum = dontEnums[index];
        if (hasOwnProperty.call(object, currentDontEnum)) {
            array.push(currentDontEnum);
        }
    }

    return array;
};

STU.isEmptyObject = function isEmptyObjectFunction(object) {
    return STU.isObject(object) && (STU.objectKeys(object).length === NUMBERS.ZERO);
};

STU.isNonEmptyObject = function isNonEmptyObjectFunction(object) {
    return STU.isObject(object) && (STU.objectKeys(object).length > NUMBERS.ZERO);
};

STU.isNotNonEmptyObject = function isNotNonEmptyObjectFunction(object) {
    return !STU.isNonEmptyObject(object);
};

STU.isNotObject = function isNotObjectFunction(object) {
    return !STU.isObject(object);
};

STU.isObjectKey = function isObjectKeyFunction(object, key) {
    return (
        STU.isNonEmptyString(key) &&
        STU.isObject(object) &&
        STU.hasOwnProperty.call(object, key)
    );
};

STU.isDefinedObjectKey = function isDefinedObjectKeyFunction(object, key) {
    return STU.isObjectKey(object, key) && STU.isLooselyDefined(object[key]);
};

STU.isNotDefinedObjectKey = function isNotDefinedObjectKeyFunction(object, key) {
    return !STU.isDefinedObjectKey(object, key);
};

STU.areConditionGuard = function areConditionGuardFunction(args, conditionFunction, cardinality) {
    var condition,
        length;

    if (STU.isUndefined(args) || STU.isNotDefinedObjectKey(STU.cardinalityObject, cardinality)) {
        return BOOLS.FALSE;
    }
    condition = STU.isFunction(conditionFunction);
    if (STU.isArray(args) || STU.isArgumentsObject(args)) {
        length = args.length;
        condition = condition && (length === length) && (length > NUMBERS.MINUSONE);
    }

    return condition;
};

STU.areCondition = function areConditionFunction(args, conditionFunction, cardinality) {
    var localCardinality = !(
            STU.isStrictlyDefined(cardinality) || STU.isNonEmptyString(cardinality)
        ) ? 'all' : cardinality,
        conditionFunctionLocal,
        cardinalityObject,
        length,
        index;

    if (!STU.areConditionGuard(args, conditionFunction, localCardinality)) {
        return BOOLS.FALSE;
    }

    cardinalityObject = STU.cardinalityObject[localCardinality];
    if (STU.isNotDefinedObjectKey(cardinalityObject, 'conditionModifier')) {
        conditionFunctionLocal = conditionFunction;
    } else {
        conditionFunctionLocal = cardinalityObject.conditionModifier(conditionFunction);
    }

    length = args.length;
    if (length === NUMBERS.ONE) {
        return cardinalityObject[(conditionFunctionLocal(args[NUMBERS.ZERO]) ? 'guard' : 'final') + 'Value'];
    }

    for (index = 0; index < length; ++index) {
        if (conditionFunctionLocal(args[index])) {
            return cardinalityObject.guardValue;
        }
    }

    return cardinalityObject.finalValue;
};

STU.areAllCondition = function areAllConditionFunction(args, conditionFunction) {
    return STU.areCondition(args, conditionFunction, 'all');
};

STU.areAnyCondition = function areAnyConditionFunction(args, conditionFunction) {
    return STU.areCondition(args, conditionFunction, 'any');
};

STU.areNoneCondition = function areNoneConditionFunction(args, conditionFunction) {
    return STU.areCondition(args, conditionFunction, 'none');
};

STU.isStringBoolean = function isStringBooleanFunction(object) {
    var stringBooleans = STRINGS.stringBooleanValues;
    return (
        STU.isNonEmptyString(object) &&
        STU.isNonEmptyArray(stringBooleans) &&
        (stringBooleans.indexOf(object) !== NUMBERS.MINUSONE)
    );
};

STU.isConstructedBoolean = function isConstructedBooleanFunction(object) {
    return STU.isBasicObject(object) && STU.isPrimitiveBoolean(object.valueOf());
};

STU.isBoolean = function isBooleanFunction(object) {
    return (
        STU.isStringBoolean(object) ||
        STU.isPrimitiveBoolean(object) ||
        STU.isConstructedBoolean(object)
    );
};

STU.areStrings = function areStringsFunction() {
    return STU.areAllCondition(arguments, STU.isString);
};

STU.areEmptyStrings = function areEmptyStringsFunction() {
    return STU.areAllCondition(arguments, STU.isEmptyString);
};

STU.areAnyEmptyStrings = function areAnyEmptyStringsFunction() {
    return STU.areAnyCondition(arguments, STU.isEmptyString);
};

STU.areNonEmptyStrings = function areNonEmptyStringsFunction() {
    return STU.areAllCondition(arguments, STU.isNonEmptyString);
};

STU.areNoneStrings = function areNoneStringsFunction() {
    return STU.areNoneCondition(arguments, STU.isString);
};

STU.areAnyStrings = function areAnyStringsFunction() {
    return STU.areAnyCondition(arguments, STU.isString);
};

STU.areArrays = function areArraysFunction() {
    return STU.areCondition(arguments, STU.isArray);
};

STU.areNonEmptyArrays = function areNonEmptyArraysFunction() {
    return STU.areCondition(arguments, STU.isNonEmptyArray);
};

STU.areEmptyArrays = function areEmptyArraysFunction() {
    return STU.areAllCondition(arguments, STU.isEmptyArray);
};

STU.areAnyNotFunctions = function areAnyNotFunctionsFunction() {
    return STU.areAnyCondition(arguments, STU.isNotFunction);
};

STU.areDefined = function areDefinedFunction() {
    return STU.areCondition(arguments, STU.isLooselyDefined);
};

STU.getValueFromNestedObject = function getValueFromNestedObjectFunction(object, keyArray) {
    var undefinedValue = void 0,
        isNotDefinedObjectKey,
        currentKey,
        element,
        length,
        index;

    if (!(STU.isObject(object) && STU.isNonEmptyArray(keyArray))) {
        return undefinedValue;
    }

    if (STU.isSingleNonEmptyStringArray(keyArray)) {
        currentKey = keyArray[NUMBERS.ZERO];
        return STU.isDefinedObjectKey(object, currentKey) ? object[currentKey] : undefinedValue;
    }

    isNotDefinedObjectKey = STU.isNotDefinedObjectKey;
    element = object;
    length = keyArray.length;
    index = 0;
    for (; index < length; ++index) {
        currentKey = keyArray[index];
        if (isNotDefinedObjectKey(element, currentKey)) {
            break;
        }

        element = element[currentKey];
    }

    return element;
};

