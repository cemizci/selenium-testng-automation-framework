package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private Driver() {}

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver(){

        if (driverPool.get() == null){
            String browser = ConfigReader.getProperty("browser");
            boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

            WebDriver driver;

            switch (browser.toLowerCase()) {

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    driver = new SafariDriver();
                    break;

                default:
                    ChromeOptions options = new ChromeOptions();

                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080");
                    }

                    options.addArguments("--incognito");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-save-password-bubble");

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);

                    options.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver(options);
        }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

            driverPool.set(driver);
        }

        return driverPool.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverPool.get();

        if (driver != null) {
            driver.quit();
            driverPool.remove();
        }
    }
}


