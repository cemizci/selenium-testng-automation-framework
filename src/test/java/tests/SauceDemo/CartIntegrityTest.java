package tests.SauceDemo;

import Models.SauceDemo.Item;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCartService;
import utilities.WaitUtils;

import java.util.Map;

public class CartIntegrityTest extends BaseTest {

    @Test
    public void CartItemsShouldMatchProductsTest(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        SauceDemoCartService cartServices = new SauceDemoCartService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        Map<String, Item> expectedByName = cartServices.getAllProductsFromProductsPage();


        Map<String, Item> actualByName = cartServices.getAllItemsFromCartPage();

        softAssert.assertEquals(actualByName.size(), expectedByName.size(),
                "Cart'taki ürün sayısı products ile aynı olmalı");

        for (String name : expectedByName.keySet()){
            softAssert.assertTrue(actualByName.containsKey(name), "Cart'ta ürün bulunmalı: " + name);

            Item exp = expectedByName.get(name);
            Item act = actualByName.get(name);

            if (act!= null){
                softAssert.assertEquals(act.getDesc(), exp.getDesc(), "Desc aynı olmalı: " + name);
                softAssert.assertEquals(act.getPrice(), exp.getPrice(), "Price aynı olmalı: " + name);
            }
        }

        System.out.println(expectedByName);
        System.out.println(actualByName);
    }


}
