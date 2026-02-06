package tests.TestOtomasyonu;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage.TestOtomasyonuPage;
import services.TestOtomasyonu.CartService;
import services.TestOtomasyonu.CheckoutService;
import services.TestOtomasyonu.LoginService;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.WaitUtils;

public class ShoppingCartTest extends BaseTest {

 @Test
 public void userShouldPlaceOrderSuccesfully(){

     Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
     TestOtomasyonuPage page = new TestOtomasyonuPage();

     LoginService.loginAsValidUser(page);
     softAssert.assertTrue(page.logoutButton.isDisplayed(),"Logout button gorunmedi!");
     Reporter.log("Profil sayfasina basariyla giris yapildi!", true);

     String expectedName = CartService.searchAndAddFirstProductToCart(page);
     WaitUtils.waitForVisibility(page.yourCartPageProductName, 5);
     String actualName = page.yourCartPageProductName.getText();

     softAssert.assertEquals(actualName, expectedName, "Sepetteki ürün ismi farklı!");

     CheckoutService.goToCheckout(page);
     CheckoutService.placeOrderCashOnDelivery(page);

     String msg = page.orderSuccesfullyText.getText().trim();
     softAssert.assertTrue(msg.contains("Your order is successfully done!"));
     Reporter.log("Your order is successfully done!",true);


     softAssert.assertAll();
 }


}
