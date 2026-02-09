package tests.SauceDemo;

import Models.SauceDemo.Item;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.CheckoutCompletePage;
import pages.SauceDemoPage.CheckoutOverviewPage;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import services.SauceDemo.SauceDemoCheckoutService;
import utilities.Driver;
import utilities.WaitUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class SauceDemoE2ECheckoutTest extends BaseTest {

    @Test
    public void e2e_login_addToCart_checkout_success(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        SauceDemoCheckoutService checkoutService = new SauceDemoCheckoutService();
        SauceDemoCartService cartServices = new SauceDemoCartService();
        CartPage cart = new CartPage();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        System.out.println("Products count: " + productsPage.productCards.size());
        softAssert.assertEquals(productsPage.productCards.size(), 6, "Products sayfasında 6 ürün olmalı");

        Map<String, Item> expectedByName = new LinkedHashMap<>();
        for (WebElement card : productsPage.productCards){
            String name = card.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            String desc = card.findElement(By.cssSelector(".inventory_item_desc")).getText().trim();
            String price = card.findElement(By.cssSelector(".inventory_item_price")).getText().trim();

            expectedByName.put(name, new Item(name, desc, price));
        }

        cartServices.addAllProductsToCart();
        softAssert.assertEquals(cart.cartBadge.getText().trim(), "6", "Cart badge 6 olmalı");

        productsPage.shoppingCartIcon.click();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        Map<String, Item> actualByName = new LinkedHashMap<>();
        for (WebElement item : cart.cartItems){
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            String desc = item.findElement(By.cssSelector(".inventory_item_desc")).getText().trim();
            String price = item.findElement(By.cssSelector(".inventory_item_price")).getText().trim();

            actualByName.put(name, new Item(name, desc, price));
        }

        System.out.println(expectedByName);
        System.out.println(actualByName);
        softAssert.assertAll();
    }
}
