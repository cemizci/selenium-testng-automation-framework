package tests.saucedemo;

import models.saucedemo.LoginCase;
import base.BaseTest;
import constants.saucedemo.SauceDemoErrorMessages;
import dataproviders.saucedemo.SauceDemoLoginDataProvider;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ProductsPage;
import services.saucedemo.AuthService;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.WaitUtils;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginMatrix", dataProviderClass = SauceDemoLoginDataProvider.class)
    public void login_matrix_test(LoginCase c){
        AuthService authService = new AuthService();
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

    @Test
    public void locked_out_user_should_not_be_able_to_login(){
        AuthService authService = new AuthService();
        LoginPage loginPage = new LoginPage();

        authService.goToLoginPage();
        authService.login(ConfigReader.getProperty("saucedemo.user.locked"), ConfigReader.getProperty("saucedemo.password"));

        softAssert.assertTrue(loginPage.isErrorVisible(),
                "Locked out user should see an error message.");

        String actualErrorMessage = loginPage.getErrorText();
        softAssert.assertTrue(actualErrorMessage.contains(SauceDemoErrorMessages.LOCKED_OUT),
                "Expected locked out error message, but was: " + actualErrorMessage);

        softAssert.assertFalse(Driver.getDriver().getCurrentUrl().contains("inventory.html"),
                "Locked out user should not be redirected to inventory page.");
    }
}
