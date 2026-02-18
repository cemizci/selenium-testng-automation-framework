package services.SauceDemo;

import Models.SauceDemo.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.ProductsPage;
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

}
