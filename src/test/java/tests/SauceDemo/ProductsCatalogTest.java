package tests.saucedemo;

import models.saucedemo.Item;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saucedemo.ProductsPage;
import services.saucedemo.AuthService;
import services.saucedemo.CartService;
import utilities.WaitUtils;

import java.util.List;

public class ProductsCatalogTest extends BaseTest {

    @Test
    public void products_should_be_displayed_correctly(){
        AuthService authService = new AuthService();
        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle,5);

        Assert.assertEquals(productsPage.productCards.size(),6,"Products sayfasında 6 ürün olmalı");

        CartService cartService = new CartService();
        cartService.getAllProductsFromProductsPage();
        for (Item item : cartService.getAllProductsFromProductsPage().values()) {
            Assert.assertFalse(item.getName().isEmpty());
            Assert.assertFalse(item.getDesc().isEmpty());
            Assert.assertFalse(item.getPrice().isEmpty());
            Assert.assertTrue(item.getPrice().startsWith("$"));

            System.out.println(item);
            System.out.println(item.getPrice());
        }

        List<String> badTitles = productsPage.getBadTitleThatLookLikeCode();
        List<String> badDescs = productsPage.getBadDescriptionsThatLookLikeCode();

        softAssert.assertTrue(badTitles.isEmpty(),
                "Kod benzeri ürün başlıkları bulundu: " + badTitles);

        softAssert.assertTrue(badDescs.isEmpty(),
                "Kod benzeri ürün açıklamaları bulundu: " + badDescs);

        softAssert.assertAll();
    }
}
