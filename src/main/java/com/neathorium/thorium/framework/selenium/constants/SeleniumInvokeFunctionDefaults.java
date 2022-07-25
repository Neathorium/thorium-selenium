package com.neathorium.thorium.framework.selenium.constants;

import com.neathorium.thorium.framework.selenium.implementations.reflection.InvokerParameterizedData;
import com.neathorium.thorium.framework.selenium.implementations.reflection.InvokerRegularData;
import com.neathorium.thorium.framework.selenium.namespaces.InvokerFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.SeleniumExceptionHandlers;
import com.neathorium.thorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.thorium.core.constants.CastDataConstants;
import com.neathorium.thorium.core.records.reflection.InvokeParametersFieldDefaultsData;


import com.neathorium.thorium.framework.selenium.records.reflection.ParameterizedInvokerDefaultsData;
import com.neathorium.thorium.framework.selenium.records.reflection.RegularInvokerDefaultsData;
import com.neathorium.thorium.java.extensions.namespaces.predicates.AmountPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public abstract class SeleniumInvokeFunctionDefaults {
    public static final InvokeParametersFieldDefaultsData<WebElement> SINGLE_PARAMETER = new InvokeParametersFieldDefaultsData<>(AmountPredicates::isSingle, InvokerFunctions::invokeWithParameters);
    public static final InvokeParametersFieldDefaultsData<SearchContext> SEARCH_CONTEXT_SINGLE_PARAMETER = new InvokeParametersFieldDefaultsData<>(AmountPredicates::isSingle, InvokerFunctions::invokeWithParameters);
    public static final InvokeParametersFieldDefaultsData<WebElement> PARAMETERS = new InvokeParametersFieldDefaultsData<>(AmountPredicates::isNonZero, InvokerFunctions::invokeWithParameters);

    public static final ParameterizedInvokerDefaultsData<WebElement, Object> OBJECT_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        CastDataConstants.OBJECT,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final ParameterizedInvokerDefaultsData<WebElement, WebElement> WEB_ELEMENT_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        SeleniumCastDataConstants.WEB_ELEMENT,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final ParameterizedInvokerDefaultsData<SearchContext, WebElement> SEARCH_CONTEXT_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        SeleniumCastDataConstants.WEB_ELEMENT,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final ParameterizedInvokerDefaultsData<WebElement, String> STRING_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        CastDataConstants.STRING,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final ParameterizedInvokerDefaultsData<WebElement, Boolean> BOOLEAN_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        CastDataConstants.BOOLEAN,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final ParameterizedInvokerDefaultsData<WebElement, Void> VOID_PARAMETERS = new ParameterizedInvokerDefaultsData<>(
        InvokerParameterizedData::new,
        ScriptExecutions::isValidInvokerParameterizedData,
        CastDataConstants.VOID,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final RegularInvokerDefaultsData<WebElement, Object> OBJECT_REGULAR = new RegularInvokerDefaultsData<>(
        InvokerRegularData::new,
        NullablePredicates::isNotNull,
        CastDataConstants.OBJECT,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final RegularInvokerDefaultsData<WebElement, String> STRING_REGULAR = new RegularInvokerDefaultsData<>(
        InvokerRegularData::new,
        NullablePredicates::isNotNull,
        CastDataConstants.STRING,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final RegularInvokerDefaultsData<WebElement, Boolean> BOOLEAN_REGULAR = new RegularInvokerDefaultsData<>(
        InvokerRegularData::new,
        NullablePredicates::isNotNull,
        CastDataConstants.BOOLEAN,
        SeleniumExceptionHandlers::invokeHandler
    );
    public static final RegularInvokerDefaultsData<WebElement, Void> VOID_REGULAR = new RegularInvokerDefaultsData<>(
        InvokerRegularData::new,
        NullablePredicates::isNotNull,
        CastDataConstants.VOID,
        SeleniumExceptionHandlers::invokeHandler
    );
}
