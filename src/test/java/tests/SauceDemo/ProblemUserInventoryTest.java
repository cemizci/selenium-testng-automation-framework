package tests.saucedemo;

import models.saucedemo.Item;
import base.BaseTest;
import org.testng.annotations.Test;
import services.saucedemo.AuthService;
import services.saucedemo.ProductService;
import utilities.ConfigReader;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProblemUserInventoryTest extends BaseTest {

    @Test
    public void problem_user_inventory_images_should_be_unique(){
        AuthService authService = new AuthService();
        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password"));

        ProductService productService = new ProductService();

        List<Item> products = productService.getAllProductsFromInventory();

        Set<String> uniqueImageSources = new LinkedHashSet<>();
        StringBuilder imageReport = new StringBuilder("\nProduct -> Image Src\n");

        for (Item item : products){

            softAssert.assertNotNull(
                    item.getImage(),
                    "Image src should not be null for product: " + item.getName()
            );

            softAssert.assertFalse(item.getImage().isBlank(),
                    "Image src should not be blank for product: " + item.getName());

            uniqueImageSources.add(item.getImage());

            imageReport.append(item.getName())
                    .append(" -> ")
                    .append(item.getImage())
                    .append("\n");

        }

        softAssert.assertEquals(
                uniqueImageSources.size(),
                products.size(),
                "Each inventory product should have a unique image. " +
                        "Total products: " + products.size() +
                        ", unique image count: " + uniqueImageSources.size() +
                        "\n\nImage usage report:" + imageReport

        );
    }

    @Test
    public void all_inventory_products_should_match_pdp(){
        AuthService authService = new AuthService();
        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password")
        );

        ProductService productService = new ProductService();

        List<Item> inventoryItems = productService.getAllProductsFromInventory();
        List<Item> pdpItems = productService.getAllProductsFromPdpPages();

        for (int i = 0; i < inventoryItems.size(); i++){
           Item inv = inventoryItems.get(i);
           Item pdp = pdpItems.get(i);

           softAssert.assertEquals(
                   pdp.getName(),
                   inv.getName(),
                   "Name mismatch for product at index " + i
           );

           softAssert.assertEquals(
                   pdp.getDesc(),
                   inv.getDesc(),
                   "Description mismatch for product: " + inv.getName()
           );

           softAssert.assertEquals(
                   pdp.getPrice(),
                   inv.getPrice(),
                   "Price mismatch for product: " + inv.getName()
           );

           softAssert.assertEquals(
                   pdp.getImage(),
                   inv.getImage(),
                   "Price mismatch for product: " + inv.getName()
           );

        }
    }
}
