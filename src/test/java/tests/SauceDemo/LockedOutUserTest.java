package tests.saucedemo;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import services.saucedemo.AuthService;
import utilities.ConfigReader;
import utilities.Driver;

public class LockedOutUserTest extends BaseTest {

    @Test(description = "Locked out user should not be able to login")
    public void lockedOutUserShouldNotLogin(){
        AuthService authService = new AuthService();
        authService.goToLoginPage();

        authService.login(
                ConfigReader.getProperty("saucedemo.user.locked"),
                ConfigReader.getProperty("saucedemo.password")
        );

        LoginPage loginPage = new LoginPage();

        String errorMessage = loginPage.errorMessage.getText();

        softAssert.assertTrue(
                errorMessage.contains("locked out"),
                "Error message should indicate locked out user"
        );

        softAssert.assertTrue(
                Driver.getDriver().getCurrentUrl().contains("saucedemo.com"),
                "User should not be redirected to inventory page"
        );
    }
}
