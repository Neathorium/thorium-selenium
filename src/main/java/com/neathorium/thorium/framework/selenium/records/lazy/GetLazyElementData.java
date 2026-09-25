package com.neathorium.thorium.framework.selenium.records.lazy;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.enums.ManyGetter;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.ExternalElementData;
import com.neathorium.thorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.thorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.thorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.thorium.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.neathorium.thorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import org.openqa.selenium.WebDriver;

import java.util.function.Predicate;

public record GetLazyElementData<ReturnType, ListType> (
    TriFunction<Data<ReturnType>, Integer, Integer, Boolean> EXIT_CONDITION,
    TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> CACHE_FUNCTION,
    TriFunction<
        BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>,
        LazyLocatorList,
        String,
        DriverFunction<ReturnType>
    > GETTER,
    Predicate<Data<ReturnType>> INVALIDATOR,
    Data<ReturnType> DEFAULT_VALUE
) {}
