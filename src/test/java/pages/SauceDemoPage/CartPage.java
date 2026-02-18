package pages.SauceDemoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CartPage {
    public CartPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy(css = ".inventory_item_name")
    public WebElement firstItemName;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy (id = "continue-shopping")
    public  WebElement continueShoppingButton;

    @FindBy(css = ".cart_item .inventory_item_name")
    public WebElement cartItemName;

    @FindBy(css = ".cart_item .inventory_item_desc")
    public WebElement cartItemDesc;

    @FindBy(css = ".cart_item .inventory_item_price")
    public WebElement cartItemPrice;

    @FindBy(css = ".cart_item .cart_quantity")
    public WebElement cartItemPiece;

    @FindBy(css = ".shopping_cart_badge")
    public List<WebElement> cartBadge;

    @FindBy(css = "[data-test='title']")
    public WebElement pageTitle2;

    @FindBy(css = "[data-test='inventory-item']")
    public List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    public WebElement btnCheckout;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement cartBadge2;

    @FindBy(css = ".cart_item")
    public List<WebElement> cartItems2;

    public By itemName  = By.cssSelector("[data-test='inventory-item-name']");
    public By itemDesc  = By.cssSelector("[data-test='inventory-item-desc']");
    public By itemPrice = By.cssSelector("[data-test='inventory-item-price']");

}
