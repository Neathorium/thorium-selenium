package com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.dynamic;

import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DynamicLazyElementTests {
    @Test
    void defaultElementOnProblematicParametersTest() {
        final var element = DynamicLazyElementFactory.getWith(null, null, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(StringUtils.isBlank(result), result);
    }

    @Test
    void defaultElementOnProblematicParameters2Test() {
        final var element = DynamicLazyElementFactory.getWith(null, 0, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(StringUtils.isBlank(result), result);
    }

    @Test
    void defaultElementOnProblematicParameters3Test() {
        final var element = DynamicLazyElementFactory.getWith(null, null, null, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(StringUtils.isBlank(result), result);
    }

    @Test
    void defaultElementOnProblematicParameters4Test() {
        final var element = DynamicLazyElementFactory.getWith(null, null, 0, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(StringUtils.isBlank(result), result);
    }

    @Test
    void emptyKeyOnBadParametersTest() {
        final var key = DynamicLazyElementFactory.getComplexKey(null, null);
        Assertions.assertTrue(StringUtils.isBlank(key), "Key was: " + key);
    }

    @Test
    void aKeyOnGoodParametersTest() {
        final var object = new Object();
        final var sharedValue = "Smashing pumpkins";
        final var key = DynamicLazyElementFactory.getComplexKey(sharedValue, object);
        final var key2 = DynamicLazyElementFactory.getComplexKey(sharedValue, object);
        Assertions.assertEquals(key2, key, "Key was: " + key + "Key2 was: " + key2);
    }
}
