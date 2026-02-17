package pages.SauceDemoPage;

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

    @FindBy(css = ".cart_item")
    public List<WebElement> cartItems;
}
