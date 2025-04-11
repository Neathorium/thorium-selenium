package com.neathorium.thorium.framework.selenium.compatibility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public final class NullTargetLocator implements WebDriver.TargetLocator {
    public NullTargetLocator() {}

    @Override
    public WebDriver frame(int i) {
        return null;
    }

    @Override
    public WebDriver frame(String s) {
        return null;
    }

    @Override
    public WebDriver frame(WebElement webElement) {
        return null;
    }

    @Override
    public WebDriver parentFrame() {
        return null;
    }

    @Override
    public WebDriver window(String s) {
        return null;
    }

    @Override
    public WebDriver newWindow(WindowType typeHint) {
        return null;
    }

    @Override
    public WebDriver defaultContent() {
        return null;
    }

    @Override
    public WebElement activeElement() {
        return null;
    }

    @Override
    public Alert alert() {
        return null;
    }
}
