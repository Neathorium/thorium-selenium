package com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable;

import com.neathorium.thorium.framework.selenium.constants.scripts.injectable.JSInitializerConstants;

public interface JSInitializerScripts {
    static String getJSInitializer(String stringularityCdnUrl, String thoriumUtilitiesCdnUrl) {
        return (
            "(function loadJSDependenciesFunction() {" +
            "    if ((typeof STU) === 'undefined') {" +
            "        var stype_util_script = document.createElement('script');" +
            "        stype_util_script.type = 'application/javascript';" +
            "        stype_util_script.src = '" + stringularityCdnUrl + "';" +
            "        document.head.appendChild(stype_util_script);" +
            "    }" +
            "    if ((typeof TU) === 'undefined') {" +
            "        var type_util_script = document.createElement('script');" +
            "        type_util_script.type = 'application/javascript';" +
            "        type_util_script.src = '" + thoriumUtilitiesCdnUrl + "';" +
            "        document.head.appendChild(type_util_script);" +
            "    }" +
            "    return true;" +
            "})()"
        );
    }

    static String getDefaultJSInitializer() {
        return getJSInitializer(JSInitializerConstants.STRINGULARITY_TYPE_UTILS, JSInitializerConstants.THORIUM_UTILITIES);
    }
}
