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
import services.TestOtomasyonu.CheckoutService;
import utilities.Driver;
import utilities.WaitUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class SauceDemoE2ECheckoutTest extends BaseTest {

    @Test
    public void e2e_login_addToCart_checkout_success(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        SauceDemoCartService cartServices = new SauceDemoCartService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        System.out.println("Products count: " + productsPage.productCards.size());
        softAssert.assertEquals(productsPage.productCards.size(), 6, "Products sayfasında 6 ürün olmalı");

        cartServices.addAllProductsToCart();
        softAssert.assertFalse(productsPage.cartBadges.isEmpty(),
                "Cart badge görünmeli");

        softAssert.assertEquals(productsPage.cartBadges.get(0).getText().trim(),
                "6", "Cart badge 6 olmalı");

        productsPage.shoppingCartIcon.click();

        CartPage cartPage = new CartPage();
        WaitUtils.waitForVisibility(cartPage.pageTitle, 5);


        SauceDemoCheckoutService checkoutService = new SauceDemoCheckoutService();
        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("a","b","3");
        System.out.println(cartPage.cartItems.size());

        checkoutService.finishCheckout();
        softAssert.assertAll();
    }
}
