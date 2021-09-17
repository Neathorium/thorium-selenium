package com.neathorium.thorium.framework.selenium.constants.scripts.injectable;

public abstract class JSInitializerConstants {
    private static final String BASE = "https://cdn.jsdelivr.net/gh/Neathorium/thorium-selenium@2dc4336c55732c8adf43821ca8c8694b09269d50/js-utils/";
    public static final String STRINGULARITY_TYPE_UTILS = BASE + "stringularity-type-utils-nodocs-rollup.js";
    public static final String THORIUM_UTILITIES = BASE + "thoriumutilities.js";

    public static final String DEPENDENCY_EXISTENCE_CHECKER = (
        "var isStu = typeof STU === 'object';" +
        "var isTu = typeof TU === 'object';" +
        "var objectType = typeof {} === 'object';" +
        "isStu && isTu && objectType;"
    );
}
