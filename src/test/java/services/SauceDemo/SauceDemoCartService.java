package services.SauceDemo;

import Models.SauceDemo.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.ProductDetailPage;
import pages.SauceDemoPage.ProductsPage;
import utilities.Driver;
import utilities.WaitUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SauceDemoCartService {

    public void addAllProductsToCart(){

        ProductsPage p = new ProductsPage();
        WaitUtils.waitForVisibility(p.pageTitle,5);

        for (WebElement card : p.productCards){

            if (!card.findElements(p.addBtn).isEmpty()) {
                card.findElement(p.addBtn).click();
            }
        }

    }

    public void removeAllProductsFromProductPage(){

        ProductsPage p = new ProductsPage();
        WaitUtils.waitForVisibility(p.pageTitle, 5);

        for (WebElement card : p.productCards) {
            if (!card.findElements(p.btnRemove).isEmpty()) {
                card.findElement(p.btnRemove).click();
            }
        }
    }

    public Map<String, Item> getAllProductsFromProductsPage(){
        ProductsPage p = new ProductsPage();
        WaitUtils.waitForVisibility(p.pageTitle, 5);

        Map<String, Item> map = new LinkedHashMap<>();

        for (WebElement card : p.productCards){
            String name = card.findElement(p.itemName).getText().trim();
            String desc = card.findElement(p.itemDesc).getText().trim();
            String price = card.findElement(p.itemPrice).getText().trim();

            map.put(name, new Item(name, desc, price));
        }
        return map;
    }

    public Map<String, Item> getAllItemsFromCartPage(){
        CartPage cartPage = new CartPage();
        WaitUtils.waitForVisibility(cartPage.pageTitle, 5);

        Map<String, Item> map = new LinkedHashMap<>();

        for (WebElement item : cartPage.cartItems) {
            String name = item.findElement(cartPage.itemName).getText().trim();
            String desc = item.findElement(cartPage.itemDesc).getText().trim();
            String price = item.findElement(cartPage.itemPrice).getText().trim();

            map.put(name, new Item(name, desc, price));
        }
        return map;
    }

    public int addAllProductsToCartFromPdp() {
        ProductsPage productsPage = new ProductsPage();
        ProductDetailPage pdp = new ProductDetailPage();

        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        int addedCount = 0;
        int totalProducts = productsPage.productCards.size();

        for (int i = 0; i < totalProducts; i++){
            WebElement card = productsPage.productCards.get(i);
            String productName = card.findElement(productsPage.itemName).getText().trim();
            card.findElement(productsPage.itemName).click();

            WaitUtils.waitForVisibility(pdp.addToCartBtn, 5);

            pdp.addToCartBtn.click();
            addedCount++;

            Driver.getDriver().navigate().back();
            WaitUtils.waitForVisibility(productsPage.pageTitle, 5);
        }
        return addedCount;
    }

    public int getCartBadgeCount(){
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        if (cartPage.cartBadge2.isDisplayed()){
            return Integer.parseInt(cartPage.cartBadge2.getText());
        }
        return 0;
    }



}
