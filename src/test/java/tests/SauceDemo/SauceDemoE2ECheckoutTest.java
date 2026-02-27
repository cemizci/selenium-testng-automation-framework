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


        cartServices.addAllProductsToCart();
        softAssert.assertFalse(productsPage.cartBadges.isEmpty(),
                "Cart badge görünmeli");


        productsPage.shoppingCartIcon.click();

        SauceDemoCheckoutService checkoutService = new SauceDemoCheckoutService();
        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("a","b","3");

        checkoutService.finishCheckout();
        softAssert.assertAll();
    }

    @Test
    public void shouldNotAllowCheckoutWhenCartIsEmpty(){
        SauceDemoAuthService authService = new SauceDemoAuthService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);

        productsPage.shoppingCartIcon.click();

        CartPage cartPage = new CartPage();
        int itemCount = cartPage.cartItems.size();
        System.out.println(itemCount);
        boolean checkoutEnabled = cartPage.checkoutButton.isEnabled();
        softAssert.assertFalse(cartPage.cartItems.isEmpty(),"Cartta ürün yok");

        SauceDemoCheckoutService checkoutService = new SauceDemoCheckoutService();
        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("a","b","3");
        checkoutService.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage();
        System.out.printf(completePage.completeText.getText());
        softAssert.assertFalse(completePage.completeHeader.isDisplayed(),"Sepette ürün yokken sipariş tamamlandı!");
    }
}
