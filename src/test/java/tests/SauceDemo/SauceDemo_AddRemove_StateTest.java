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
        }

        softAssert.assertEquals(productsPage.shoppingCartBadge.getText().trim(), "6", "Badge 6 olmalı");
        System.out.println(productsPage.shoppingCartBadge.getText());

        for (WebElement btn : productsPage.inventoryButtons){
            btn.click();
            softAssert.assertEquals(btn.getText().trim(), "Add to cart" , "Tüm Butonlar Add To Cart olmalı");
        }





        softAssert.assertAll();
    }
}
