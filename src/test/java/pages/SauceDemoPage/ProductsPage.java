package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

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
}
