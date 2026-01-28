package services;

import org.openqa.selenium.support.ui.Wait;
import pages.TestOtomasyonuPage;
import utilities.BrowserUtils;
import utilities.WaitUtils;

public class CheckoutService {

    public static void goToCheckout(TestOtomasyonuPage page){
        WaitUtils.waitForClickability(page.yourCartCheckoutButton);
        page.yourCartCheckoutButton.click();
    }

    public static void placeOrderCashOnDelivery(TestOtomasyonuPage page){
        WaitUtils.waitForClickability(page.billingAddressCheckmark);
        page.billingAddressCheckmark.click();
        page.sameBillingAddressCheckmark.click();

        BrowserUtils.scroolToElement(page.shippingMethodsCheckmark);

        WaitUtils.waitForClickability(page.shippingMethodsCheckmark);
        page.shippingMethodsCheckmark.click();

        BrowserUtils.scroolToElement(page.cashOnDeliveryPaymentOption);

        WaitUtils.waitForClickability(page.cashOnDeliveryPaymentOption);
        page.cashOnDeliveryPaymentOption.click();

        WaitUtils.waitForClickability(page.acceptTermsCheckbox);
        page.acceptTermsCheckbox.click();

        WaitUtils.waitForClickability(page.placeOrderNowButton);
        page.placeOrderNowButton.click();

//        WaitUtils.waitForVisibility(page.viewOrderButton);
//        page.viewOrderButton.click();
//        WaitUtils.waitForClickability(page.orderDetailsButton);

    }
}
