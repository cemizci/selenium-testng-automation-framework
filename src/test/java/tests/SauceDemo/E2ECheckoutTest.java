package tests.saucedemo;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.saucedemo.CartPage;
import pages.saucedemo.CheckoutCompletePage;
import pages.saucedemo.CheckoutOverviewPage;
import pages.saucedemo.ProductsPage;
import services.saucedemo.AuthService;
import services.saucedemo.CartService;
import services.saucedemo.CheckoutService;
import utilities.WaitUtils;

public class E2ECheckoutTest extends BaseTest {

    @Test
    public void e2e_login_addToCart_checkout_success(){
        AuthService authService = new AuthService();
        CartService cartServices = new CartService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);


        cartServices.addAllProductsToCart();
        softAssert.assertFalse(productsPage.cartBadges.isEmpty(),
                "Cart badge görünmeli");


        productsPage.shoppingCartIcon.click();

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("a","b","3");

        checkoutService.finishCheckout();
        softAssert.assertAll();
    }

    @Test
    public void shouldNotAllowCheckoutWhenCartIsEmpty(){
        AuthService authService = new AuthService();

        authService.goToLoginPage();
        authService.loginWithValidUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.shoppingCartIcon.click();

        CartPage cartPage = new CartPage();
        int itemCount = cartPage.cartItems.size();
        System.out.println("Number of products on the cart : " + itemCount);

        softAssert.assertEquals(cartPage.cartItems.size(),0, "Precondition failed: Cart should be empty.");
        softAssert.assertFalse(cartPage.checkoutButton.isEnabled(), "Checkout button should be disabled when the cart is empty.");

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.proceedToCheckout();
        checkoutService.fillCheckoutInfoAndContinue("a","b","3");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        overviewPage.printSummaryDetails();
        overviewPage.finishButton.click();

        CheckoutCompletePage completePage = new CheckoutCompletePage();
        System.out.println(completePage.completeHeader.getText());
        System.out.printf(completePage.completeText.getText());
        softAssert.assertFalse(completePage.completeHeader.isDisplayed(),"The purchase was complete when the cart was empty.");
    }
}
