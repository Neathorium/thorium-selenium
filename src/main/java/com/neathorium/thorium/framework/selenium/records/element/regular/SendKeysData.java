package com.neathorium.thorium.framework.selenium.records.element.regular;

import com.neathorium.thorium.framework.selenium.enums.SingleGetter;
import com.neathorium.thorium.framework.selenium.namespaces.element.Element;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.openqa.selenium.By;

import java.util.Objects;
import java.util.function.BiFunction;

public class SendKeysData {
    public final BiFunction<LazyElement, String, DriverFunction<Boolean>> SEND_KEYS_LAZY;
    public final TriFunction<By, String, SingleGetter, DriverFunction<Boolean>> SEND_KEYS_BY_WITH_GETTER;
    public final BiFunction<By, String, DriverFunction<Boolean>> SEND_KEYS_BY;

    public SendKeysData(
        BiFunction<LazyElement, String, DriverFunction<Boolean>> sendKeysLazy,
        TriFunction<By, String, SingleGetter, DriverFunction<Boolean>> sendKeysByWithGetter,
        BiFunction<By, String, DriverFunction<Boolean>> sendKeysBy
    ) {
        this.SEND_KEYS_LAZY = sendKeysLazy;
        this.SEND_KEYS_BY_WITH_GETTER = sendKeysByWithGetter;
        this.SEND_KEYS_BY = sendKeysBy;
    }

    public SendKeysData() {
        this.SEND_KEYS_LAZY = Element::sendKeys;
        this.SEND_KEYS_BY_WITH_GETTER = Element::sendKeys;
        this.SEND_KEYS_BY = Element::sendKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (NullablePredicates.isNull(o) || EqualsPredicates.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (SendKeysData) o;
        return (
            EqualsPredicates.isEqual(SEND_KEYS_LAZY, that.SEND_KEYS_LAZY) &&
            EqualsPredicates.isEqual(SEND_KEYS_BY_WITH_GETTER, that.SEND_KEYS_BY_WITH_GETTER) &&
            EqualsPredicates.isEqual(SEND_KEYS_BY, that.SEND_KEYS_BY)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(SEND_KEYS_LAZY, SEND_KEYS_BY_WITH_GETTER, SEND_KEYS_BY);
    }

    @Override
    public String toString() {
        return (
            "SendKeysData{" +
            "SEND_KEYS_LAZY=" + SEND_KEYS_LAZY +
            ", SEND_KEYS_BY_WITH_GETTER=" + SEND_KEYS_BY_WITH_GETTER +
            ", SEND_KEYS_BY=" + SEND_KEYS_BY +
            '}'
        );
    }
}
