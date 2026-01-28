package tests;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import services.CartService;
import services.CheckoutService;
import services.LoginService;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Print;
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
     WaitUtils.waitForVisibility(page.yourCartPageProductName);
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
