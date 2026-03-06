package dataproviders.saucedemo;

import Models.SauceDemo.LoginCase;
import constants.saucedemo.SauceDemoErrorMessages;
import org.testng.annotations.DataProvider;
import utilities.ConfigReader;

public class SauceDemoLoginDataProvider {
    @DataProvider(name = "loginMatrix")
    public static Object[][] loginMatrix(){
        String standardUser = ConfigReader.getProperty("saucedemo.username");
        String validPass = ConfigReader.getProperty("saucedemo.password");

        String invalidUser = ConfigReader.getProperty("saucedemo.user.invalid");
        String invalidPass = ConfigReader.getProperty("saucedemo.pass.invalid");

        return new Object[][]{
                {new LoginCase("valid_user_valid_pass", standardUser, validPass, true, null)},

                {new LoginCase("valid_user_invalid_pass", standardUser, invalidPass, false, SauceDemoErrorMessages.INVALID_CREDENTIALS)},
                {new LoginCase("invalid_user_valid_pass", invalidUser, validPass, false, SauceDemoErrorMessages.INVALID_CREDENTIALS)},
                {new LoginCase("invalid_user_invalid_pass", invalidUser, invalidPass, false, SauceDemoErrorMessages.INVALID_CREDENTIALS)},

                {new LoginCase("empty_user_valid_pass", "", validPass, false, SauceDemoErrorMessages.USERNAME_REQUIRED)},
                {new LoginCase("valid_user_empty_pass", standardUser, "", false, SauceDemoErrorMessages.PASSWORD_REQUIRED)},
                {new LoginCase("empty_user_empty_pass", "", "", false, SauceDemoErrorMessages.USERNAME_REQUIRED)},

                {new LoginCase("locked_out_user", "locked_out_user", validPass, false, SauceDemoErrorMessages.LOCKED_OUT)}
        };
    }
}
