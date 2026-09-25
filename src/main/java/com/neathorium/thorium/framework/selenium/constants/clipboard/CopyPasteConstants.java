package com.neathorium.thorium.framework.selenium.constants.clipboard;


import com.neathorium.thorium.core.platform.namespaces.systemidentity.BasicSystemIdentityFunctions;
import org.openqa.selenium.Keys;

public abstract class CopyPasteConstants {
    public static final Keys PASTE_CONTROL = BasicSystemIdentityFunctions.isMac() ? Keys.COMMAND : Keys.CONTROL;
}
