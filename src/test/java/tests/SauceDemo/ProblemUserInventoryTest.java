package tests.SauceDemo;

import Models.SauceDemo.Item;
import base.BaseTest;
import org.testng.annotations.Test;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import services.SauceDemo.SauceDemoProductService;
import utilities.ConfigReader;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ProblemUserInventoryTest extends BaseTest {

    @Test
    public void problem_user_inventory_images_should_be_unique(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password"));

        SauceDemoProductService productService = new SauceDemoProductService();

        Map<String, Item> products = productService.getAllProductsWithImagesFromProductsPage();

        Set<String> uniqueImageSources = new LinkedHashSet<>();
        StringBuilder imageReport = new StringBuilder("\nProduct -> Image Src\n");

        for (Map.Entry<String, Item> entry : products.entrySet()){
            String productName = entry.getKey();
            Item item = entry.getValue();

            softAssert.assertNotNull(
                    item.getImage(),
                    ""
            );

            softAssert.assertFalse(item.getImage().isBlank(),
                    "Image src should not be blank for product: " + productName);

            uniqueImageSources.add(item.getImage());

            imageReport.append(productName)
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
}
