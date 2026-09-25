package com.neathorium.thorium.framework.selenium.records.element.is.regular;

import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public record ElementConditionParameters<ReturnType, PredicateType>(
    TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> HANDLER,
    ElementFormatData<ReturnType> FORMAT_DATA,
    Function<LazyElement, DriverFunction<ReturnType>> FUNCTION,
    Function<Predicate<PredicateType>, Predicate<PredicateType>> INVERTER
) implements IElementBooleanValueParameters<ReturnType> {}
