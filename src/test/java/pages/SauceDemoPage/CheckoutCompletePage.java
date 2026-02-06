package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutCompletePage {
    public CheckoutCompletePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy(css = ".complete-header")
    public WebElement completeHeader;
}
