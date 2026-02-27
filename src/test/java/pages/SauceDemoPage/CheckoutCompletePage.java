package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutCompletePage {
    public CheckoutCompletePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "[data-test='title']")
    public WebElement pageTitle; // "Checkout: Complete!"

    @FindBy(css = "[data-test='complete-header']")
    public WebElement completeHeader; // "Thank you for your order!"

    @FindBy(css = "[data-test='complete-text']")
    public WebElement completeText;

    @FindBy(css = "[data-test='back-to-products']")
    public WebElement btnBackHome;



}
