package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
    }

    public static void waitForVisibility(WebElement element, int seconds){
        getWait(5).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element){
        getWait(5).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUrlContains(String text) {
        getWait(5).until(ExpectedConditions.urlContains(text));
    }
}
