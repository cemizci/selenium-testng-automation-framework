package services.SauceDemo;

import Models.SauceDemo.Item;
import org.openqa.selenium.WebElement;
import pages.SauceDemoPage.ProductsPage;
import utilities.WaitUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class SauceDemoProductService {

    public Map<String, Item> getAllProductsWithImagesFromProductsPage(){
        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle,5);

        Map<String, Item> map = new LinkedHashMap<>();
        for (WebElement card : productsPage.productCards){
            String name = card.findElement(productsPage.itemName).getText().trim();
            String desc = card.findElement(productsPage.itemDesc).getText().trim();
            String price = card.findElement(productsPage.itemPrice).getText().trim();
            String imageSrc = card.findElement(productsPage.itemImage).getAttribute("src").trim();

            map.put(name, new Item(name, desc, price, imageSrc));
        }
        return map;
    }
}
