package tests.SauceDemo;

import Models.SauceDemo.LoginCase;
import base.BaseTest;
import constants.saucedemo.SauceDemoErrorMessages;
import dataproviders.saucedemo.SauceDemoLoginDataProvider;
import org.testng.annotations.Test;
import pages.SauceDemoPage.LoginPage;
import pages.SauceDemoPage.ProductsPage;
import services.SauceDemo.SauceDemoAuthService;
import utilities.WaitUtils;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginMatrix", dataProviderClass = SauceDemoLoginDataProvider.class)
    public void login_matrix_test(LoginCase c){
        SauceDemoAuthService authService = new SauceDemoAuthService();
        LoginPage loginPage = new LoginPage();

        authService.goToLoginPage();
        authService.login(c.username(), c.password());

        if (c.expectedSuccess()){
            ProductsPage productsPage = new ProductsPage();
            WaitUtils.waitForVisibility(productsPage.pageTitle, 5);
            softAssert.assertTrue(productsPage.pageTitle.isDisplayed(),
                    "Inventory page title should be visible after successful login.");
        } else {
            softAssert.assertTrue(loginPage.isErrorVisible(),
                    "Error message should be visible for invalid login.");

            String actualError = loginPage.getErrorText();
            softAssert.assertTrue(actualError.contains(c.expectedErrorContains()),
                    "Case=" + c.name()
                            + " | Expected error to contain: " + c.expectedErrorContains()
                            + " but was: " + actualError);
        }
    }
}
