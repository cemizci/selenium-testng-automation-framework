package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.Driver;

public abstract class BaseTest {

    protected SoftAssert softAssert;

    @BeforeClass
    public void beforeAll(){
        Driver.getDriver();
    }

    @BeforeMethod
    public void beforeEach(){
        softAssert = new SoftAssert();
    }

//    @AfterMethod(alwaysRun = true)
//    public void afterEach(){
//        softAssert.assertAll();
//    }

    @AfterClass(alwaysRun = true)
    public void afterAll(){
        Driver.quitDriver();
    }
}
