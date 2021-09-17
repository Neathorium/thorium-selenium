package com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.dynamic;

import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class DynamicLazyElementTests {
    @Test
    public void defaultElementOnProblematicParametersTest() {
        final var element = DynamicLazyElementFactory.getWith(null, null, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void defaultElementOnProblematicParameters2Test() {
        final var element = DynamicLazyElementFactory.getWith(null, 0, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void defaultElementOnProblematicParameters3Test() {
        final var element = DynamicLazyElementFactory.getWith(null, null, null, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void defaultElementOnProblematicParameters4Test() {
        final var element = DynamicLazyElementFactory.getWith(null, null, 0, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void emptyKeyOnBadParametersTest() {
        final var key = DynamicLazyElementFactory.getComplexKey(null, null);
        Assertions.assertTrue(isBlank(key), "Key was: " + key);
    }

    @Test
    public void aKeyOnGoodParametersTest() {
        final var object = new Object();
        final var key = DynamicLazyElementFactory.getComplexKey("Smashing pumpkins", object);
        final var key2 = DynamicLazyElementFactory.getComplexKey("Smashing pumpkins", object);
        Assertions.assertEquals(key2, key, "Key was: " + key + "Key2 was: " + key2);
    }
}
