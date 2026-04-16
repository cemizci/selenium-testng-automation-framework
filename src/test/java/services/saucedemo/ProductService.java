package services.saucedemo;

import models.saucedemo.Item;
import org.openqa.selenium.WebElement;
import pages.saucedemo.ProductDetailPage;
import pages.saucedemo.ProductsPage;
import utilities.Driver;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Item> getAllProductsFromInventory(){
        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle,5);

        List<Item> items = new ArrayList<>();

        for (WebElement card : productsPage.productCards){
            String name = card.findElement(productsPage.itemName).getText().trim();
            String desc = card.findElement(productsPage.itemDesc).getText().trim();
            String price = card.findElement(productsPage.itemPrice).getText().trim();
            String imageSrc = card.findElement(productsPage.itemImage).getAttribute("src").trim();

            items.add(new Item(name, desc, price, imageSrc));
        }
        return items;
    }

    public List<Item> getAllProductsFromPdpPages() {
        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        List<Item> pdpItems = new ArrayList<>();
        ProductDetailPage pdpPage = new ProductDetailPage();

        int totalProducts = productsPage.productCards.size();

        for (int i = 0; i < totalProducts; i++) {
            WebElement card = productsPage.productCards.get(i);
            card.findElement(productsPage.itemName).click();
            WaitUtils.waitForVisibility(pdpPage.productName, 5);

            String name = pdpPage.productName.getText().trim();
            String desc = pdpPage.productDescription.getText().trim();
            String price = pdpPage.productPrice.getText().trim();
            String imageSrc = pdpPage.productImage.getAttribute("src").trim();

            pdpItems.add(new Item(name, desc, price, imageSrc));

            Driver.getDriver().navigate().back();
            WaitUtils.waitForVisibility(productsPage.pageTitle, 5);
        }
        return pdpItems;
    }

}
