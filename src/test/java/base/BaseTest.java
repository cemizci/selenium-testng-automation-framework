package base;

import org.slf4j.MDC;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.AllureUtils;
import utilities.Driver;

import java.lang.reflect.Method;


public abstract class BaseTest implements IHookable {

    protected SoftAssert softAssert;

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void beforeAll(){
        Driver.getDriver();
    }

    @BeforeMethod
    public void beforeEach(Method method){
        softAssert = new SoftAssert();
        MDC.put("testName", method.getName());
        MDC.put("testClass", method.getDeclaringClass().getSimpleName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterEach() {
        MDC.clear();
    }

    @AfterClass(alwaysRun = true)
    public void afterAll(){
        Driver.quitDriver();
    }
}
