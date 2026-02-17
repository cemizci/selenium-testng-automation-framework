package tests.SauceDemo;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import utilities.WaitUtils;

public class SauceDemo_AddRemove_StateTest extends BaseTest {

    @Test
    public void add_to_cart_and_remove_should_update_ui_state(){
        SauceDemoAuthService auth = new SauceDemoAuthService();
        SauceDemoCartService cartService = new SauceDemoCartService();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        auth.goToLoginPage();
        auth.loginWithValidUser();

        cartService.addAllProductsToCart();

        softAssert.assertEquals(productsPage.inventoryButtons.size(), 6, "6 ürün için 6 buton olmalı");

        for (WebElement btn : productsPage.inventoryButtons){
            softAssert.assertEquals(btn.getText().trim(), "Remove", "Tüm butonlar Remove olmalı");
            System.out.println(btn.getText());
        }

        softAssert.assertFalse(cartPage.cartBadge.isEmpty(), "Badge görünmeli");
        softAssert.assertEquals(cartPage.cartBadge.get(0).getText().trim(), "6", "Badge 6 olmalı");
        System.out.println(cartPage.cartBadge.get(0).getText());

        productsPage.shoppingCartIcon.click();

        cartPage = new CartPage();
        WaitUtils.waitForVisibility(cartPage.pageTitle, 5);
        softAssert.assertEquals(cartPage.cartItems.size(), 6, "6 adet ürün olmalı");

        cartPage.continueShoppingButton.click();

        ProductsPage refreshed = new ProductsPage();

        cartService.removeAllProductsFromProductPage();
        softAssert.assertTrue(refreshed.cartBadges.isEmpty(), "Remove sonrası badge kaybolmalı");

        refreshed.shoppingCartIcon.click();
        CartPage cartPage2 = new CartPage();
        WaitUtils.waitForVisibility(cartPage2.pageTitle, 5);

        softAssert.assertTrue(cartPage2.cartItems.isEmpty(), "liste boş olmalı");
        System.out.println(cartPage2.cartItems.size());

        softAssert.assertAll();
    }
}
