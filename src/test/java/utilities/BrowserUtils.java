package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class BrowserUtils {

    public static void scroolToElement(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(element).perform();
    }
}
