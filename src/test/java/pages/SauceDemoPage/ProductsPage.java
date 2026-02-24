package pages.SauceDemoPage;

import Models.SauceDemo.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ContentSanity;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    public ProductsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy (xpath = "//button[contains(text(), 'Add to cart')]")
    public WebElement addToCartButton;

    @FindBy(xpath = "//button[normalize-space()='Remove']")
    public WebElement removeButtons;

    @FindBy (xpath = "//*[@class='shopping_cart_link']")
    public WebElement shoppingCartIcon;

    @FindBy (xpath = "//span[@class='shopping_cart_badge']")
    public WebElement shoppingCartBadge;

    @FindBy(css = ".shopping_cart_badge")
    public List<WebElement> cartBadges;


    @FindBy(css = ".inventory_item")
    public List<WebElement> productCards;

    @FindBy(css = ".inventory_list")
    public WebElement inventoryList;

    @FindBy(css = ".inventory_item button.btn_inventory")
    public List<WebElement> inventoryButtons;

    @FindBy(css = "[data-test='title']")
    public WebElement pageTitle2; // "Products"

    @FindBy(css = "[data-test='inventory-item']")
    public List<WebElement> productCards2;

    @FindBy(css = "[data-test='shopping-cart-link']")
    public WebElement cartIcon;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement cartBadge;

    public By addBtn = By.cssSelector("button[data-test^='add-to-cart']");
    public By btnRemove = By.cssSelector("button[data-test^='remove']");
    public By itemName  = By.cssSelector("[data-test='inventory-item-name']");
    public By itemDesc  = By.cssSelector("[data-test='inventory-item-desc']");
    public By itemPrice = By.cssSelector("[data-test='inventory-item-price']");


    public List<String> getBadTitleThatLookLikeCode(){
        List<String> bad = new ArrayList<>();

        for (WebElement card : productCards){
            String title = card.findElement(itemName).getText().trim();
            if (ContentSanity.looksLikeCode(title)){
                bad.add(title);
            }
        }
        return  bad;
    }


    public List<String> getBadDescriptionsThatLookLikeCode(){
        List<String> bad = new ArrayList<>();
        for (WebElement card : productCards){
            String desc = card.findElement(itemDesc).getText().trim();
            if (ContentSanity.looksLikeCode(desc)){
                bad.add(desc);
            }
        }
        return bad;
    }

}
