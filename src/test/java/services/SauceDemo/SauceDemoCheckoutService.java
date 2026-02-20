package services.SauceDemo;

import org.openqa.selenium.support.ui.Wait;
import pages.SauceDemoPage.*;
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

    public void fillCheckoutInfoAndContinue(String firstname, String lastname, String zip){
        CheckoutInfoPage infoPage = new CheckoutInfoPage();
        WaitUtils.waitForVisibility(infoPage.pageTitle, 5);

        infoPage.firstNameInput.sendKeys(firstname);
        infoPage.lastNameInput.sendKeys(lastname);
        infoPage.postalCodeInput.sendKeys(zip);

        infoPage.continueButton.click();
    }

    public void finishCheckout(){
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        WaitUtils.waitForVisibility(overviewPage.finishButton, 5);
        overviewPage.finishButton.click();

        CheckoutCompletePage complete = new CheckoutCompletePage();
        WaitUtils.waitForVisibility(complete.pageTitle, 5);
        System.out.println(complete.pageTitle.getText());
    }

    public void completeCheckout(String firstName, String lastName, String zip) {
        fillCheckoutInfoAndContinue(firstName, lastName, zip);
        finishCheckout();
    }
}
