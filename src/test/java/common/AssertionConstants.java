package common;

import com.neathorium.thorium.core.asserts.namespaces.JUnit5StatusAdapter;
import com.neathorium.thorium.core.data.records.Data;
import org.junit.jupiter.api.Assertions;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AssertionConstants {
    public static final BiConsumer<Boolean, String> assertTrue = Assertions::assertTrue;
    public static final Consumer<Data> assertDataTrue = JUnit5StatusAdapter.doAssert(assertTrue);
}
