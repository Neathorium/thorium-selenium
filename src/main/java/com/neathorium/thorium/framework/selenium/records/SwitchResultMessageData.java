package com.neathorium.thorium.framework.selenium.records;

public record SwitchResultMessageData<T> (
    T TARGET,
    String TYPE,
    String NAMEOF
) {}
