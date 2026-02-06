package services.SauceDemo;

import pages.SauceDemoPage.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class SauceDemoAuthService {
    public void goToLoginPage(){
        Driver.getDriver().get(ConfigReader.getProperty("saucedemo.url"));
    }

    public void loginWithValidUser(){
        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(ConfigReader.getProperty("saucedemo.username"));
        loginPage.passwordInput.sendKeys(ConfigReader.getProperty("saucedemo.password"));
        loginPage.loginButton.click();
    }

    public void login(String username, String password){
        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }
}
