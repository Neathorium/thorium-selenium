package com.neathorium.thorium.framework.selenium.records.element.regular;

import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import org.openqa.selenium.By;

import java.util.Objects;
import java.util.function.BiFunction;

public class SendKeysData {
    public final BiFunction<LazyElement, String, DriverFunction<Boolean>> sendKeysLazy;
    public final TriFunction<By, String, SingleGetter, DriverFunction<Boolean>> sendKeysByWithGetter;
    public final BiFunction<By, String, DriverFunction<Boolean>> sendKeysBy;

    public SendKeysData(
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sendKeysLazy,
        TriFunction<By, String, SingleGetter, DriverFunction<Boolean>> sendKeysByWithGetter,
        BiFunction<By, String, DriverFunction<Boolean>> sendKeysBy
    ) {
        this.sendKeysLazy = sendKeysLazy;
        this.sendKeysByWithGetter = sendKeysByWithGetter;
        this.sendKeysBy = sendKeysBy;
    }

    public SendKeysData() {
        this.sendKeysLazy = Element::sendKeys;
        this.sendKeysByWithGetter = Element::sendKeys;
        this.sendKeysBy = Element::sendKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (SendKeysData) o;
        return (
            CoreUtilities.isEqual(sendKeysLazy, that.sendKeysLazy) &&
            CoreUtilities.isEqual(sendKeysByWithGetter, that.sendKeysByWithGetter) &&
            CoreUtilities.isEqual(sendKeysBy, that.sendKeysBy)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendKeysLazy, sendKeysByWithGetter, sendKeysBy);
    }

    @Override
    public String toString() {
        return (
            "SendKeysData{" +
            "sendKeysLazy=" + sendKeysLazy +
            ", sendKeysByWithGetter=" + sendKeysByWithGetter +
            ", sendKeysBy=" + sendKeysBy +
            '}'
        );
    }
}
