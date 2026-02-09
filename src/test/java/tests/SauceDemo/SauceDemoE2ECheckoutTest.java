package tests.SauceDemo;

import base.BaseTest;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.CheckoutCompletePage;
import pages.SauceDemoPage.CheckoutOverviewPage;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import services.SauceDemo.SauceDemoCheckoutService;
import utilities.Driver;
import utilities.WaitUtils;

public class SauceDemoE2ECheckoutTest extends BaseTest {

    @Test
    public void e2e_login_addToCart_checkout_success(){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        SauceDemoCheckoutService checkoutService = new SauceDemoCheckoutService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        softAssert.assertTrue(productsPage.pageTitle.isDisplayed(), "Products page title görünmeli");
        softAssert.assertTrue(productsPage.pageTitle.getText().contains("Products"), "Title 'Products' içermeli");

        checkoutService.addBackpackToCartAndGoToCart();

        CartPage cartPage = new CartPage();
        productsPage.shoppingCartIcon.click();
        softAssert.assertTrue(cartPage.pageTitle.getText().contains("Your Cart"), "Cart sayfasına gelinmeli");
        softAssert.assertTrue(cartPage.firstItemName.getText().toLowerCase().contains("backpack"), "Sepette Backpack olmalı");

        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("Cem" , "İzci", "34000");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        WaitUtils.waitForVisibility(overviewPage.summaryContainer, 5);
        overviewPage.printSummaryDetails();

        checkoutService.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage();
        softAssert.assertTrue(completePage.pageTitle.getText().contains("Checkout: Complete"), "Checkout complete sayfası gelmeli");
        softAssert.assertTrue(completePage.completeHeader.getText().toUpperCase().contains("THANK YOU"),"Thank you mesajı görünmeli");

        softAssert.assertAll();
    }
}
