package com.neathorium.thorium.framework.selenium.constants.scripts.element;

public abstract class GetElements {
    /*static DriverFunction<Boolean> isGetElementsExistsData() {
        return ifDriver(
            "isGetElementsExistsData",
            driver -> {
                final var result = Driver.execute(ScrollIntoView.IS_EXISTS).apply(driver);
                return getWithDefaultExceptionData(Boolean.valueOf(result.object.toString()), result.status, result.message);
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(LazyElement data) {
        return isNotNullLazyElement(data) ? scrollIntoViewExecutor(data.get()) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(DriverFunction<WebElement> getter) {
        return ifDriver(
                "scrollIntoViewExecutor",
                isNotNull(getter),
                driver -> {
                    final var parameters = new ScriptParametersData<>(getter.apply(driver), DataPredicates::isValidNonFalse, SeleniumUtilities::unwrapToArray);
                    final var result = Driver.executeSingleParameter(ScrollIntoView.EXECUTE, ScriptExecuteFunctions.handleDataParameter(parameters)).apply(driver);
                    return getWithDefaultExceptionData(isNotNull(result.object), result.status, result.message);
                },
                CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> setScrollIntoView() {
        return ifDriver(
                "setScrollIntoView",
                driver -> {
                    final var result = SeleniumExecutor.conditionalSequence(ExecutorPredicates::isFalseStatus, isScrollIntoViewExistsData(), Driver.execute(ScrollIntoView.SET_FUNCTIONS)).apply(driver);
                    final var status = isValidNonFalse(result);
                    return DataFactoryFunctions.getBoolean(status, SeleniumFormatter.getScrollIntoViewMessage(result.message.getMessage(), status));
                },
                CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoView(LazyElement data) {
        return SeleniumExecutor.execute(Driver.isElementHidden(data), setScrollIntoView(), scrollIntoViewExecutor(data));
    }

    static DriverFunction<Boolean> scrollIntoView(Data<LazyElement> data) {
        return ifDriver("scrollIntoView", isValidNonFalse(data), scrollIntoView(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> scrollIntoView(By locator, SingleGetter getter) {
        return scrollIntoView(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> scrollIntoView(By locator) {
        return scrollIntoView(locator, SingleGetter.DEFAULT);
    }*/


}
