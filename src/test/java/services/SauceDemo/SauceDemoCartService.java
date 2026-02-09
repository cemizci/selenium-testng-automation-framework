package services.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.ProductsPage;
import utilities.WaitUtils;

public class SauceDemoCartService {
    public void addAllProductsToCart(){
        ProductsPage p = new ProductsPage();
        CartPage cartPage = new CartPage();
        SauceDemoCartService cartService = new SauceDemoCartService();
        WaitUtils.waitForVisibility(p.pageTitle,5);

        for (WebElement card : p.productCards){
            WebElement addBtn = p.addToCartButton;

            if (addBtn.getText().trim().equalsIgnoreCase("Add to cart")){
                addBtn.click();
            }
        }
        WaitUtils.waitForVisibility(cartPage.cartBadge, 5);
    }
}
