package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v140.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutInfoPage {
    public CheckoutInfoPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy(id = "first-name")
    public WebElement firstNameInput;

    @FindBy(id = "last-name")
    public WebElement lastNameInput;

    @FindBy(id = "postal-code")
    public WebElement postalCodeInput;

    @FindBy(id = "continue")
    public WebElement continueButton;

    //@FindBy(css = "[data-test='title']")
    //public WebElement pageTitle; // "Checkout: Your Information"

    @FindBy(css = "[data-test='firstName']")
    public WebElement firstName;

    @FindBy(css = "[data-test='lastName']")
    public WebElement lastName;

    @FindBy(css = "[data-test='postalCode']")
    public WebElement postalCode;

    @FindBy(css = "[data-test='continue']")
    public WebElement btnContinue;

    @FindBy(css = "[data-test='cancel']")
    public WebElement btnCancel;
}
