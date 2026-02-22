package tests.SauceDemo;

import Models.SauceDemo.Item;
import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import services.TestOtomasyonu.CartService;
import utilities.WaitUtils;

public class ProductsCatalogTest extends BaseTest {

    @Test
    public void products_should_be_displayed_correctly(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle,5);

        Assert.assertEquals(productsPage.productCards.size(),6,"Products sayfasında 6 ürün olmalı");

        SauceDemoCartService cartService = new SauceDemoCartService();
        cartService.getAllProductsFromProductsPage();
        for (Item item : cartService.getAllProductsFromProductsPage().values()) {
            Assert.assertFalse(item.getName().isEmpty());
            Assert.assertFalse(item.getDesc().isEmpty());
            Assert.assertTrue(item.getPrice().startsWith("$"));
            System.out.println(item);
        }
    }
}
