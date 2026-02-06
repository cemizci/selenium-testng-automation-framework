package services.TestOtomasyonu;

import pages.TestOtomasyonuPage.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.WaitUtils;

public class LoginService {
    public static void loginAsValidUser(TestOtomasyonuPage page){
        WaitUtils.waitForClickability(page.accountLinki);
        page.accountLinki.click();

        WaitUtils.waitForVisibility(page.loginPageEmailBox);
        page.loginPageEmailBox.sendKeys(ConfigReader.getProperty("validEmail"));
        page.loginPagePasswordBox.sendKeys(ConfigReader.getProperty("validPassword"));

        WaitUtils.waitForClickability(page.loginPageSubmitButton);
        page.loginPageSubmitButton.click();

    }
}
