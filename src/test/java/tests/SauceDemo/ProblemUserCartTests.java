package tests.SauceDemo;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import services.SauceDemo.SauceDemoProductService;
import utilities.AllureUtils;
import utilities.ConfigReader;
import utilities.WaitUtils;

import java.io.ByteArrayInputStream;

public class ProblemUserCartTests extends BaseTest {

    @Test
    public void problem_user_should_be_able_to_add_all_inventory_items_to_cart(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        ProductsPage productsPage = new ProductsPage();

        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password")
        );

        WaitUtils.waitForVisibility(productsPage.pageTitle,5);

        int totalProducts = productsPage.productCards.size();
        int successCount = 0;

        StringBuilder failedProductsReport = new StringBuilder("\nFailed add-to-cart products:\n");

        for (WebElement card : productsPage.productCards) {
            String productsName = card.findElement(productsPage.itemName).getText().trim();

            int badgeBefore = 0;
            try{
                badgeBefore = Integer.parseInt(productsPage.cartBadge.getText().trim());
            } catch (Exception ignored){

            }

            if (!card.findElements(productsPage.addBtn).isEmpty()) {
                card.findElement(productsPage.addBtn).click();
            }

            boolean removeDisplayed = !card.findElements(productsPage.btnRemove).isEmpty();

            int badgeAfter = 0;
            try {
                badgeAfter =Integer.parseInt(productsPage.cartBadge.getText().trim());
            } catch (Exception ignored){

            }

            if (removeDisplayed && badgeAfter == badgeBefore + 1){
                successCount++;
            }else {
                failedProductsReport.append(productsName)
                        .append(" | badgeBefore=").append(badgeBefore)
                        .append(" | badgeAfter=").append(badgeAfter)
                        .append(" | removeDisplayed=").append(removeDisplayed)
                        .append("\n");
            }

            softAssert.assertTrue(
                    removeDisplayed,
                    "Remove button should be displayed after clicking add-to-cart for product: " + productsName
            );

            softAssert.assertEquals(
                    badgeAfter,
                    badgeBefore +1,
                    "Cart badge should increase by 1 after adding product: " + productsName
            );
        }

        softAssert.assertEquals(
                successCount,
                totalProducts,
                "All inventory products should be addable to cart." +
                        "\nTotal products: " + totalProducts +
                        "\nSuccessfully added: " + successCount +
                        failedProductsReport
        );
    }

    @Test
    public void problem_user_should_be_able_to_remove_added_items_from_inventory(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        ProductsPage productsPage = new ProductsPage();

        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password")
        );

        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        int initiallyAddedCount = 0;

        for (WebElement card : productsPage.productCards){
            if (!card.findElements(productsPage.addBtn).isEmpty()){
                card.findElement(productsPage.addBtn).click();
            }
        }

        for (WebElement card : productsPage.productCards){
            if (!card.findElements(productsPage.btnRemove).isEmpty()) {
                initiallyAddedCount++;
            }
        }

        int successRemoveCount = 0;
        StringBuilder failedProductsReport = new StringBuilder("\nFailed remove products:\n");

        for (WebElement card : productsPage.productCards) {
            if (card.findElements(productsPage.btnRemove).isEmpty()){
                continue;
            }

            String productName = card.findElement(productsPage.itemName).getText().trim();

            int badgeBefore = 0;
            try {
                badgeBefore = Integer.parseInt(productsPage.cartBadge.getText().trim());
            } catch (Exception ignored){

            }

            card.findElement(productsPage.btnRemove).click();

            boolean addDisplayed = !card.findElements(productsPage.addBtn).isEmpty();

            int badgeAfter = 0;
            try {
                badgeAfter = Integer.parseInt(productsPage.cartBadge.getText().trim());
            } catch (Exception ignored){

            }

            if (addDisplayed && badgeAfter == badgeBefore - 1){
                successRemoveCount++;
            }else {
                failedProductsReport.append(productName)
                        .append(" | badgeBefore=").append(badgeBefore)
                        .append(" | badgeAfter=").append(badgeAfter)
                        .append(" | addDisplayed=").append(addDisplayed)
                        .append("\n");
            }

            softAssert.assertTrue(
                    addDisplayed,
                    "Add to cart button should be displayed after removing product: " + productName
            );

            softAssert.assertEquals(
                    badgeAfter,
                    badgeBefore - 1,
                    "Cart badge should decrease by 1 after removing product: " + productName
            );
        }

        softAssert.assertEquals(
                successRemoveCount,
                initiallyAddedCount,
                "All added products should be removable from inventory page." +
                        "\nInitially added products: " + initiallyAddedCount +
                        "\nSuccessfully removed: " + successRemoveCount +
                        failedProductsReport
        );

    }

    @Test(description = "Problem user should be able to add all products to cart from PDP pages")
    public void problemUserShouldAddAllProductsToCartFromPdp(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.problem"),
                ConfigReader.getProperty("saucedemo.password")
        );

        SauceDemoCartService cartService = new SauceDemoCartService();
        SauceDemoProductService productService = new SauceDemoProductService();

        int totalProducts = productService.getAllProductsFromInventory().size();
        int addedCount = cartService.addAllProductsToCartFromPdp();
        int cartBadge = cartService.getCartBadgeCount();

        softAssert.assertEquals(addedCount,totalProducts, "Not all products were added from PDP");
        softAssert.assertEquals(cartBadge, totalProducts, "Cart badge count mismatch");
    }
}
