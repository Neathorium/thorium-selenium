package com.neathorium.thorium.framework.selenium.namespaces.scripter.elements;

public interface ElementsFunctions {
    /*private static DriverFunction<Boolean> isDisplayedCore(Data<WebElement> data) {
        return ifDriver(
                "isDisplayed",
                isNotNullWebElement(data),
                driver -> {
                    final var parameter = Execute.handleDataParameterDefault(data);
                    if(isInvalidOrFalse(parameter)) {
                        return replaceMessage(CoreDataConstants.NULL_BOOLEAN, parameter.message.toString());
                    }

                    final var result = Driver.executeSingleParameter(Displayed.IS_DISPLAYED_DISPATCHER, parameter.object).apply(driver);
                    return isValidNonFalse(result) ? (
                            getWithDefaultExceptionData(CoreUtilities.castToBoolean(result.object), result.status, result.message)
                    ) : replaceMessage(CoreDataConstants.NULL_BOOLEAN, result.message.toString());
                },
                CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> isDisplayed(LazyElement element) {
        return isNotNullLazyElement(element) ? (
                isDisplayed(element.get())
        ) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_BOOLEAN, "isDisplayed", "LazyElement element" + CoreFormatterConstants.WAS_NULL));
    }

    static DriverFunction<Boolean> isDisplayed(Data<LazyElement> data) {
        return ifDriver("isDisplayed", isValidNonFalse(data), isDisplayed(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isDisplayed(By locator, SingleGetter getter) {
        return isDisplayed(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> isDisplayed(By locator) {
        return isDisplayed(locator, SingleGetter.DEFAULT);
    }*/
}
