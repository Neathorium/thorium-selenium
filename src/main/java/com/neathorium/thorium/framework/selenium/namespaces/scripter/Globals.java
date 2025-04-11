package com.neathorium.thorium.framework.selenium.namespaces.scripter;

import com.neathorium.thorium.framework.selenium.constants.scripts.general.GeneralSnippets;

public interface Globals {
    static String set(String functionGroup, String name) {
        return String.join("\n", GeneralSnippets.STRICT, initialize(functionGroup), GeneralSnippets.RETURN_TRUE);
    }

    static String function(String functionGroup, String name, String body, String returnMessage) {
        return String.join("\n", GeneralSnippets.STRICT, body, General.RETURN(returnMessage));
    }

    static String initialize(String functionGroup) {
        return (
            "document['" + functionGroup + "'] = {};" +
            "const GLOBALS = document['" + functionGroup + "'];"
        );
    }

    static String isGroupExists(String functionGroup) {
        return General.IF_FALSE_RETURN_FALSE("document.hasOwnProperty('" + functionGroup + "')");
    }

    static String isFunctionExists(String functionGroup, String name) {
        return "const GLOBALS = document['" + functionGroup + "'];" + General.IF_FALSE_RETURN_FALSE("GLOBALS.hasOwnProperty('" + name + "')");
    }

    static String isExists(String functionGroup, String function) {
        return isGroupExists(functionGroup) + isFunctionExists(functionGroup, function);
    }

    static String hasAndIsFunctionCondition(String name) {
        return "(GLOBALS && (\nGLOBALS.hasOwnProperty(\"" + name + "\")) && (GLOBALS[\"" + name + "\"] === 'function')\n)";
    }

    static String getFunctionExists(String functionGroup, String name) {
        return (
            GeneralSnippets.STRICT +
            "const GLOBALS = document['" + functionGroup + "'];\n" +
            "var globalsCondition = true && (typeof GLOBALS !== 'undefined');\n" +
            "if (globalsCondition === false) {\n" +
            "    return false;\n" +
            "}\n" +
            "var globalsHasOwn = GLOBALS.hasOwnProperty('" + name + "');\n" +
            "if (globalsHasOwn === false) {\n" +
            "    return false;\n" +
            "}\n" +
            "var globalsIsFunction = GLOBALS['" + name + "'] === 'function';\n" +
            "if (globalsIsFunction === false) {\n" +
            "    return false;\n" +
            "}\n" +
            "var endCondition = globalsCondition && globalsHasOwn && globalsIsFunction;\n" +
            General.RETURN("condition")
        );
    }

}
