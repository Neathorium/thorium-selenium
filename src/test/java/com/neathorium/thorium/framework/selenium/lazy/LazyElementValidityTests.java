package com.neathorium.thorium.framework.selenium.lazy;

import com.neathorium.thorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;
import com.neathorium.thorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.thorium.framework.core.records.lazy.LazyLocator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class LazyElementValidityTests {
    private static final Predicate<String> blank = StringUtils::isBlank;
    private static final Predicate<String> notBlank = StringUtils::isNotBlank;

    @Test
    public void elementValidityTest() {
        final var data = LazyElementFactory.getWithFilterParameters("Whatever", new LazyLocator("X", "id"));
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(data);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void elementInvalidityTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LazyElementFactory.getWith(null, null, null));
    }

    public static Stream<Arguments> elementProvider() {
        return Stream.of(
            Arguments.of("Basic valid Lazy Element - a name, a lazy locator", LazyElementFactory.getWithFilterParameters("Whatever", new LazyLocator("X", "id")), blank, true, CoreFormatterConstants.EMPTY)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("elementProvider")
    public void parameterizedElementValidityTests(String name, LazyElement element, Predicate<CharSequence> test, boolean expected, String expectedMessage) {
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        Assertions.assertTrue(Objects.equals(test.test(result), expected) && Objects.equals(result, expectedMessage), result);
    }
}
