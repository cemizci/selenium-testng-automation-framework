package tests.saucedemo;

import base.BaseTest;
import models.saucedemo.Item;
import org.testng.annotations.Test;
import services.saucedemo.AuthService;
import services.saucedemo.CartService;
import services.saucedemo.ProductService;
import utilities.ConfigReader;

import java.util.List;

public class PerformanceGlitchUserTest extends BaseTest {

    @Test(description = "Performance glitch user should be able to add products under slow conditions")
    public void performanceGlitchUserShouldAddProducts(){

        AuthService authService = new AuthService();
        authService.goToLoginPage();
        authService.login(
                ConfigReader.getProperty("saucedemo.user.glitch"),
                ConfigReader.getProperty("saucedemo.password")
        );

        CartService cartService = new CartService();
        ProductService productService = new ProductService();

        List<Item> products = productService.getAllProductsFromInventory();

        int expectedCount = products.size();

        int actualAddedCount = cartService.addAllProductsToCart();

        int cardBadge = cartService.getCartBadgeCount();

        softAssert.assertEquals(
                cardBadge,
                expectedCount,
                "Cart badge mismatch under performance glitch user"
        );

        softAssert.assertTrue(
                actualAddedCount > 0,
                "No products were added under performance glitch user"
        );
    }
}
