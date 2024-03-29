package com.neathorium.thorium.framework.selenium.records.expectedcondition.regular;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.DriverFunctionConstants;
import com.neathorium.thorium.framework.selenium.constants.ElementFinderConstants;
import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.thorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.thorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.neathorium.thorium.framework.selenium.records.ActionWhenData;
import com.neathorium.thorium.framework.selenium.records.element.ElementFunctionsData;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElementWaitParameters;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore.ifDriver;

public class ElementAlternative {
    private final ElementFunctionsData functionData;

    public ElementAlternative(ElementFunctionsData functionData) {
        this.functionData = functionData;
    }

    public ElementAlternative() {
        this.functionData = new ElementFunctionsData();
    }

    public DriverFunction<Boolean> clickWhenCore(LazyElement data, DriverFunction<Boolean> condition) {
        return Element.actionWhenCore(new ActionWhenData<>(condition, functionData.clickData.clickLazy.apply(data)));
    }

    public DriverFunction<Boolean> clearWhenCore(LazyElement data, DriverFunction<Boolean> condition) {
        return Element.actionWhenCore(new ActionWhenData<>(condition, functionData.clearData.clearLazy.apply(data)));
    }

    public DriverFunction<Boolean> clickWhenCore(Data<LazyElement> data, DriverFunction<Boolean> condition) {
        return ExecutionCore.ifDriver("clickWhenCore", DataPredicates.isValidNonFalse(data), clickWhenCore(data.OBJECT(), condition), CoreDataConstants.NULL_BOOLEAN);
    }

    public DriverFunction<Boolean> clearWhenCore(Data<LazyElement> data, DriverFunction<Boolean> condition) {
        return ExecutionCore.ifDriver("clearWhenCore", DataPredicates.isValidNonFalse(data), clearWhenCore(data.OBJECT(), condition), CoreDataConstants.NULL_BOOLEAN);
    }

    public DriverFunction<Boolean> clickWhenCore(By locator, Function<By, DriverFunction<Boolean>> condition, SingleGetter getter) {
        return clickWhenCore(LocatorRepository.getIfContains(locator, getter), condition.apply(locator));
    }

    public DriverFunction<Boolean> clearWhenCore(By locator, Function<By, DriverFunction<Boolean>> condition, SingleGetter getter) {
        return clearWhenCore(LocatorRepository.getIfContains(locator, getter), condition.apply(locator));
    }

    public DriverFunction<Boolean> clickWhenCore(By locator, Function<By, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(new ActionWhenData<>(condition.apply(locator), functionData.clickData.clickBy.apply(locator)));
    }

    public DriverFunction<Boolean> clearWhenCore(By locator, BiFunction<By, DriverFunction<WebElement>, DriverFunction<Boolean>> condition, SingleGetter getter) {
        return Element.actionWhenCore(new ActionWhenData<>(condition.apply(locator, DriverFunctionFactory.getFunction(ElementFinderConstants.singleGetterMap.get(getter).apply(SeleniumUtilities.getLazyLocatorList(locator)))), functionData.clearData.clearByWithGetter.apply(locator, getter)));
    }

    public DriverFunction<Boolean> clearWhenCore(By locator, Function<By, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(new ActionWhenData<>(condition.apply(locator), functionData.clearData.clearBy.apply(locator)));
    }

    public DriverFunction<Boolean> inputWhenCore(ActionWhenData<Boolean, Boolean> data) {
        return Element.actionWhenCore(data);
    }

    private DriverFunction<Boolean> inputWhenCore(Data<LazyElement> data, String input, Function<LazyElement, DriverFunction<Boolean>> condition, BiFunction<LazyElement, String, DriverFunction<Boolean>> sender) {
        return ExecutionCore.ifDriver(
            "inputWhenCore",
            DataPredicates.isValidNonFalse(data),
            inputWhenCore(new ActionWhenData<>(condition.apply(data.OBJECT()), sender.apply(data.OBJECT(), input))),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    public DriverFunction<Boolean> inputWhenCore(
        By locator,
        SingleGetter getter,
        String input,
        Function<LazyElement, DriverFunction<Boolean>> condition,
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sender
    ) {
        return inputWhenCore(LocatorRepository.getIfContains(locator, getter), input, condition, sender);
    }

    public DriverFunction<Boolean> inputWhenCore(
        By locator,
        String input,
        Function<LazyElement, DriverFunction<Boolean>> condition,
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sender
    ) {
        return inputWhenCore(LocatorRepository.getIfContains(locator), input, condition, sender);
    }

    public DriverFunction<Boolean> inputWhenCore(
        By locator,
        SingleGetter getter,
        String input,
        BiFunction<By, SingleGetter, DriverFunction<Boolean>> condition,
        TriFunction<By, SingleGetter, String, DriverFunction<Boolean>> sender
    ) {
        return inputWhenCore(new ActionWhenData<>(condition.apply(locator, getter), sender.apply(locator, getter, input)));
    }

    public DriverFunction<Boolean> clickWhenCore(LazyElement data, Function<LazyElement, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(data, functionData.clickData.clickLazy, condition);
    }

    public DriverFunction<Boolean> clearWhenCore(LazyElement data, Function<LazyElement, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(data, functionData.clearData.clearLazy, condition);
    }

    public DriverFunction<Boolean> inputWhenCore(
        LazyElement data,
        String input,
        Function<LazyElement, DriverFunction<Boolean>> condition,
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sender
    ) {
        return DriverFunctionFactory.prependMessage(inputWhenCore(new ActionWhenData<>(condition.apply(data), sender.apply(data, input))), CoreFormatterConstants.ELEMENT + data.NAME);
    }

    public DriverFunction<Boolean> clickWhenCore(LazyElementWaitParameters data, Function<LazyElementWaitParameters, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(data, functionData.clickData.clickLazy, condition);
    }

    public DriverFunction<Boolean> clearWhenCore(LazyElementWaitParameters data, Function<LazyElementWaitParameters, DriverFunction<Boolean>> condition) {
        return Element.actionWhenCore(data, functionData.clearData.clearLazy, condition);
    }

    public DriverFunction<Boolean> inputWhenCore(
        LazyElementWaitParameters data,
        String input,
        Function<LazyElementWaitParameters, DriverFunction<Boolean>> action,
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sender
    ) {
        if (SeleniumUtilities.isNullLazyElementWaitParametersData(data)) {
            return DriverFunctionConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL;
        }

        final var object = data.object;
        return DriverFunctionFactory.prependMessage(Element.actionWhenCore(new ActionWhenData<>(action.apply(data), sender.apply(object, input))), CoreFormatterConstants.ELEMENT + object.NAME);
    }


    public DriverFunction<Boolean> clickWhenPresent(By locator, SingleGetter getter) {
        return clickWhenCore(locator, Element::waitPresent, getter);
    }

    public DriverFunction<Boolean> clickWhenDisplayed(By locator, SingleGetter getter) {
        return clickWhenCore(locator, Element::waitDisplayed, getter);
    }

    public DriverFunction<Boolean> clickWhenEnabled(By locator, SingleGetter getter) {
        return clickWhenCore(locator, Element::waitEnabled, getter);
    }

    public DriverFunction<Boolean> clickWhenSelected(By locator, SingleGetter getter) {
        return clickWhenCore(locator, Element::waitSelected, getter);
    }

    public DriverFunction<Boolean> clickWhenClickable(By locator, SingleGetter getter) {
        return clickWhenCore(locator, Element::waitClickable, getter);
    }

    public DriverFunction<Boolean> clickWhenPresent(By locator) {
        return clickWhenCore(locator, Element::waitPresent);
    }

    public DriverFunction<Boolean> clickWhenDisplayed(By locator) {
        return clickWhenCore(locator, Element::waitDisplayed);
    }

    public DriverFunction<Boolean> clickWhenEnabled(By locator) {
        return clickWhenCore(locator, Element::waitEnabled);
    }

    public DriverFunction<Boolean> clickWhenSelected(By locator) {
        return clickWhenCore(locator, Element::waitSelected);
    }

    public DriverFunction<Boolean> clickWhenClickable(By locator) {
        return clickWhenCore(locator, Element::waitClickable);
    }

    public DriverFunction<Boolean> clearWhenPresent(By locator, SingleGetter getter) {
        return clearWhenCore(locator, Element::waitPresent, getter);
    }

    public DriverFunction<Boolean> clearWhenDisplayed(By locator, SingleGetter getter) {
        return clearWhenCore(locator, Element::waitDisplayed, getter);
    }

    public DriverFunction<Boolean> clearWhenEnabled(By locator, SingleGetter getter) {
        return clearWhenCore(locator, Element::waitEnabled, getter);
    }

    public DriverFunction<Boolean> clearWhenSelected(By locator, SingleGetter getter) {
        return clearWhenCore(locator, Element::waitSelected, getter);
    }

    public DriverFunction<Boolean> clearWhenClickable(By locator, SingleGetter getter) {
        return clearWhenCore(locator, Element::waitClickable, getter);
    }

    public DriverFunction<Boolean> clearWhenPresent(By locator) {
        return clearWhenCore(locator, Element::waitPresent);
    }

    public DriverFunction<Boolean> clearWhenDisplayed(By locator) {
        return clearWhenCore(locator, Element::waitDisplayed);
    }

    public DriverFunction<Boolean> clearWhenEnabled(By locator) {
        return clearWhenCore(locator, Element::waitEnabled);
    }

    public DriverFunction<Boolean> clearWhenSelected(By locator) {
        return clearWhenCore(locator, Element::waitSelected);
    }

    public DriverFunction<Boolean> clearWhenClickable(By locator) {
        return clearWhenCore(locator, Element::waitClickable);
    }

    public DriverFunction<Boolean> inputWhenPresent(By locator, String input, SingleGetter getter) {
        return inputWhenCore(new ActionWhenData<>(clickWhenPresent(locator, getter), functionData.sendKeysData.SEND_KEYS_BY_WITH_GETTER.apply(locator, input, getter)));
    }

    public DriverFunction<Boolean> inputWhenDisplayed(By locator, String input, SingleGetter getter) {
        return inputWhenCore(new ActionWhenData<>(clickWhenDisplayed(locator, getter), functionData.sendKeysData.SEND_KEYS_BY_WITH_GETTER.apply(locator, input, getter)));
    }

    public DriverFunction<Boolean> inputWhenEnabled(By locator, String input, SingleGetter getter) {
        return inputWhenCore(new ActionWhenData<>(clickWhenEnabled(locator, getter), functionData.sendKeysData.SEND_KEYS_BY_WITH_GETTER.apply(locator, input, getter)));
    }

    public DriverFunction<Boolean> inputWhenSelected(By locator, String input, SingleGetter getter) {
        return inputWhenCore(new ActionWhenData<>(clickWhenSelected(locator, getter), functionData.sendKeysData.SEND_KEYS_BY_WITH_GETTER.apply(locator, input, getter)));
    }

    public DriverFunction<Boolean> inputWhenClickable(By locator, String input, SingleGetter getter) {
        return inputWhenCore(new ActionWhenData<>(clickWhenClickable(locator, getter), functionData.sendKeysData.SEND_KEYS_BY_WITH_GETTER.apply(locator, input, getter)));
    }

    public DriverFunction<Boolean> inputWhenPresent(By locator, String input) {
        return inputWhenCore(new ActionWhenData<>(clickWhenPresent(locator), functionData.sendKeysData.SEND_KEYS_BY.apply(locator, input)));
    }

    public DriverFunction<Boolean> inputWhenDisplayed(By locator, String input) {
        return inputWhenCore(new ActionWhenData<>(clickWhenDisplayed(locator), functionData.sendKeysData.SEND_KEYS_BY.apply(locator, input)));
    }

    public DriverFunction<Boolean> inputWhenEnabled(By locator, String input) {
        return inputWhenCore(new ActionWhenData<>(clickWhenEnabled(locator), functionData.sendKeysData.SEND_KEYS_BY.apply(locator, input)));
    }

    public DriverFunction<Boolean> inputWhenSelected(By locator, String input) {
        return inputWhenCore(new ActionWhenData<>(clickWhenSelected(locator), functionData.sendKeysData.SEND_KEYS_BY.apply(locator, input)));
    }

    public DriverFunction<Boolean> inputWhenClickable(By locator, String input) {
        return inputWhenCore(new ActionWhenData<>(clickWhenClickable(locator), functionData.sendKeysData.SEND_KEYS_BY.apply(locator, input)));
    }

    public DriverFunction<Boolean> clickWhenPresent(LazyElement element) {
        return clickWhenCore(element, Element::waitPresentDefaults);
    }

    public DriverFunction<Boolean> clickWhenDisplayed(LazyElement element) {
        return clickWhenCore(element, Element::waitDisplayedDefaults);
    }

    public DriverFunction<Boolean> clickWhenEnabled(LazyElement element) {
        return clickWhenCore(element, Element::waitEnabledDefaults);
    }

    public DriverFunction<Boolean> clickWhenSelected(LazyElement element) {
        return clickWhenCore(element, Element::waitSelectedDefaults);
    }

    public DriverFunction<Boolean> clickWhenClickable(LazyElement element) {
        return clickWhenCore(element, Element::waitClickableDefaults);
    }

    public DriverFunction<Boolean> inputWhenPresent(LazyElement data, String input) {
        return inputWhenCore(data, input, this::clickWhenPresent, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenDisplayed(LazyElement data, String input) {
        return inputWhenCore(data, input, this::clickWhenDisplayed, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenEnabled(LazyElement data, String input) {
        return inputWhenCore(data, input, this::clickWhenEnabled, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenSelected(LazyElement data, String input) {
        return inputWhenCore(data, input, this::clickWhenSelected, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenClickable(LazyElement data, String input) {
        return inputWhenCore(data, input, this::clickWhenClickable, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> clearWhenPresent(LazyElement data) {
        return clearWhenCore(data, Element::waitPresentDefaults);
    }

    public DriverFunction<Boolean> clearWhenDisplayed(LazyElement data) {
        return clearWhenCore(data, Element::waitDisplayedDefaults);
    }

    public DriverFunction<Boolean> clearWhenEnabled(LazyElement data) {
        return clearWhenCore(data, Element::waitEnabledDefaults);
    }

    public DriverFunction<Boolean> clearWhenSelected(LazyElement data) {
        return clearWhenCore(data, Element::waitSelectedDefaults);
    }

    public DriverFunction<Boolean> clearWhenClickable(LazyElement data) {
        return clearWhenCore(data, Element::waitClickableDefaults);
    }

    public DriverFunction<Boolean> clickWhenPresent(LazyElementWaitParameters data) {
        return clickWhenCore(data, Element::waitPresent);
    }

    public DriverFunction<Boolean> clickWhenDisplayed(LazyElementWaitParameters data) {
        return clickWhenCore(data, Element::waitDisplayed);
    }

    public DriverFunction<Boolean> clickWhenEnabled(LazyElementWaitParameters data) {
        return clickWhenCore(data, Element::waitEnabled);
    }

    public DriverFunction<Boolean> clickWhenSelected(LazyElementWaitParameters data) {
        return clickWhenCore(data, Element::waitSelected);
    }

    public DriverFunction<Boolean> clickWhenClickable(LazyElementWaitParameters data) {
        return clickWhenCore(data, Element::waitClickable);
    }

    public DriverFunction<Boolean> inputWhenPresent(LazyElementWaitParameters data, String input) {
        return inputWhenCore(data, input, this::clickWhenPresent, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenDisplayed(LazyElementWaitParameters data, String input) {
        return inputWhenCore(data, input, this::clickWhenDisplayed, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenEnabled(LazyElementWaitParameters data, String input) {
        return inputWhenCore(data, input, this::clickWhenEnabled, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenSelected(LazyElementWaitParameters data, String input) {
        return inputWhenCore(data, input, this::clickWhenSelected, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> inputWhenClickable(LazyElementWaitParameters data, String input) {
        return inputWhenCore(data, input, this::clickWhenClickable, functionData.sendKeysData.SEND_KEYS_LAZY);
    }

    public DriverFunction<Boolean> clearWhenPresent(LazyElementWaitParameters data) {
        return clearWhenCore(data, Element::waitPresent);
    }

    public DriverFunction<Boolean> clearWhenDisplayed(LazyElementWaitParameters data) {
        return clearWhenCore(data, Element::waitDisplayed);
    }

    public DriverFunction<Boolean> clearWhenEnabled(LazyElementWaitParameters data) {
        return clearWhenCore(data, Element::waitEnabled);
    }

    public DriverFunction<Boolean> clearWhenSelected(LazyElementWaitParameters data) {
        return clearWhenCore(data, Element::waitSelected);
    }

    public DriverFunction<Boolean> clearWhenClickable(LazyElementWaitParameters data) {
        return clearWhenCore(data, Element::waitClickable);
    }
}
