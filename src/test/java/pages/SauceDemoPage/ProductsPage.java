package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ProductsPage {
    public ProductsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy (xpath = "//button[contains(text(), 'Add to cart')]")
    public WebElement addToCartButton;

    @FindBy (xpath = "//*[@class='shopping_cart_link']")
    public WebElement shoppingCartIcon;

    @FindBy (xpath = "//span[@class='shopping_cart_badge']")
    public WebElement shoppingCartBadge;

    @FindBy(css = ".inventory_item")
    public List<WebElement> productCards;

    @FindBy(css = ".inventory_list")
    public WebElement inventoryList;

    @FindBy(css = ".inventory_item button.btn_inventory")
    public List<WebElement> inventoryButtons;
}
