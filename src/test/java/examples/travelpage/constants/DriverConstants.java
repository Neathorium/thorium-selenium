package examples.travelpage.constants;

import com.neathorium.thorium.core.constants.project.ProjectPathConstants;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public abstract class DriverConstants {
    public static final Map<String, WebDriver> DRIVERS = new HashMap<>();
    public static final String FF_BROWSER = "firefox-browser";
    public static final String GECKODRIVER_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String GECKODIRVER_PROPERTY_PATH = ProjectPathConstants.PROJECT_ROOT + "/driver/firefox/geckodriver.exe";

    public static final String CHROME_BROWSER = "chrome-browser";
    public static final String CHROMEDRIVER_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROMEDRIVER_PROPERTY_PATH = ProjectPathConstants.PROJECT_ROOT + "/driver/chrome/chromedriver.exe";
}
