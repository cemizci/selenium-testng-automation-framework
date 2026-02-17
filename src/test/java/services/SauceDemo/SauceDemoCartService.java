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
            WebElement addBtn = p.addToCartButton;

            if (addBtn.getText().trim().equalsIgnoreCase("Add to cart")){
                addBtn.click();
            }
        }

    }

    public void removeAllProductsFromProductPage(){

        ProductsPage p = new ProductsPage();

        for (WebElement card : p.productCards){
            WebElement removeBtn = p.removeButtons;

            removeBtn.click();
        }
    }


    public Map<String, Item> getAllProductsFromProductsPage(){
        ProductsPage productsPage = new ProductsPage();
        Map<String, Item> map = new LinkedHashMap<>();

        for (WebElement card : productsPage.productCards){
            String name = card.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            String desc = card.findElement(By.cssSelector(".inventory_item_desc")).getText().trim();
            String price = card.findElement(By.cssSelector(".inventory_item_price")).getText().trim();

            map.put(name, new Item(name, desc, price));
        }

        return map;
    }

    public Map<String, Item> getAllItemsFromCartPage(){
        CartPage cartPage = new CartPage();
        Map<String, Item> map = new LinkedHashMap<>();

        for (WebElement item : cartPage.cartItems) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            String desc = item.findElement(By.cssSelector(".inventory_item_desc")).getText().trim();
            String price = item.findElement(By.cssSelector(".inventory_item_price")).getText().trim();

            map.put(name, new Item(name, desc, price));
        }

        return map;
    }

}
