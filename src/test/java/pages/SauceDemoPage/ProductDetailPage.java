package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductDetailPage {
    public ProductDetailPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "[data-test='inventory-item-name']")
    public WebElement productName;

    @FindBy(css = "[data-test='inventory-item-desc']")
    public WebElement productDescription;

    @FindBy(css = "[data-test='inventory-item-price']")
    public WebElement productPrice;

    @FindBy(css = ".inventory_details_img")
    public WebElement productImage;
}
