package examples.travelpage.namespaces;

import com.neathorium.thorium.framework.selenium.namespaces.Driver;
import examples.travelpage.constants.DriverConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public interface FFDriverFunctions {
    static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        //System.setProperty(DriverConstants.GECKODRIVER_PROPERTY_NAME, DriverConstants.GECKODIRVER_PROPERTY_PATH);

        final var driver = new FirefoxDriver();
        DriverConstants.DRIVERS.putIfAbsent(DriverConstants.FF_BROWSER, driver);
        return DriverConstants.DRIVERS.get(DriverConstants.FF_BROWSER);
    }

    static WebDriver get() {
        return DriverConstants.DRIVERS.containsKey(DriverConstants.FF_BROWSER) ? DriverConstants.DRIVERS.get(DriverConstants.FF_BROWSER) : getFirefoxDriver();
    }

    static void unregister(String name) {
        final var driver = DriverConstants.DRIVERS.get(name);
        Driver.quitDriver().apply(driver);
        DriverConstants.DRIVERS.remove(name);
    }

    static void unregister() {
        unregister(DriverConstants.FF_BROWSER);
    }
}
