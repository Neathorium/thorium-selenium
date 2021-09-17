package com.neathorium.thorium.framework.selenium.drivers;

import com.neathorium.thorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;


public class DriverFactory {
    private static final HashMap<String, HashMap<String, Data<WebDriver>>> driverMap = new HashMap<>();
    private static WebDriver driver = null;

    public static final String defaultBrowser = "FIREFOX",
        defaultId = "0";

    private static WebDriver getDriverObject(Data<WebDriver> data) {
        return (data.status ? data : SeleniumDataConstants.NULL_DRIVER).object;
    }

    public static WebDriver get() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        if (NullableFunctions.isNull(driver)) {
            driver = new ChromeDriver();
        }

        return driver;
    }
}
