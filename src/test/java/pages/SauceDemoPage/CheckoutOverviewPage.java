package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutOverviewPage {
    public CheckoutOverviewPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy(css = ".inventory_item_name")
    public WebElement itemName;

    @FindBy(id = "finish")
    public WebElement finishButton;
}
