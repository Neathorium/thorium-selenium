package com.neathorium.thorium.framework.selenium.namespaces.validators;

import com.neathorium.thorium.framework.selenium.constants.SeleniumCoreConstants;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface MessageFormatter {
    static Data<WebElement> getInvalidIndexMessageFunction(int index) {
        return DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, "Index(\"" + index +"\") was negative" + CoreFormatterConstants.END_LINE);
    }

    static Function<Data<WebElementList>, Data<WebElement>> getElementIndexWasNegative(int index) {
        return listData -> MessageFormatter.getInvalidIndexMessageFunction(index);
    }

    static Data<WebElement> getInvalidTextMessageFunction(String message) {
        return DataFactoryFunctions.getWith(SeleniumCoreConstants.STOCK_ELEMENT, false, "Text(\"" + message + "\") was blank" + CoreFormatterConstants.END_LINE);
    }
}
