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

    private Driver(){

    }

    public static WebDriver driver;


    public static WebDriver getDriver(){

        String useToBrowser = ConfigReader.getProperty("browser");

        if (driver == null){

            switch (useToBrowser){
                case "safari" :
                    driver = new SafariDriver();
                    break;
                case "firefox" :
                    driver = new FirefoxDriver();
                    break;
                case "edge" :
                    driver = new EdgeDriver();
                    break;
                default:

                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--incognito");
                    options.addArguments("--guest");

                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-features=PasswordManagerOnboarding");
                    options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");


                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);

                    options.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }

        return driver;
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver= null;
        }
    }

}


