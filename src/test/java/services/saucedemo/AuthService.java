package services.saucedemo;

import io.qameta.allure.Step;
import pages.saucedemo.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;


public class AuthService {

    @Step("Open SauceDemo login page")
    public void goToLoginPage(){
        Driver.getDriver().get(ConfigReader.getProperty("saucedemo.url"));
    }

    @Step("Login with valid user")
    public void loginWithValidUser(){
        login(
                ConfigReader.getProperty("saucedemo.username"),
                ConfigReader.getProperty("saucedemo.password")
        );
    }

    @Step("Login attempt with username: {username}")
    public void login(String username, String password){
        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.clear();
        loginPage.passwordInput.clear();
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }
}
