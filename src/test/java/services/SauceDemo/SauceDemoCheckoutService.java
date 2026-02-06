package services.SauceDemo;

import pages.SauceDemoPage.CartPage;
import pages.SauceDemoPage.ProductsPage;
import utilities.WaitUtils;

public class SauceDemoCheckoutService {
    public void addBackpackToCartAndGoToCart(){
        ProductsPage productsPage = new ProductsPage();
        WaitUtils.waitForVisibility(productsPage.pageTitle, 5);
        productsPage.addToCartButton.click();
        WaitUtils.waitForVisibility(productsPage.shoppingCartBadge, 5);
        productsPage.shoppingCartIcon.click();
    }

    public void proceedToCheckout(){
        CartPage cartPage = new CartPage();
        WaitUtils.waitForVisibility(cartPage.pageTitle, 5);
        cartPage.checkoutButton.click();
    }
}
