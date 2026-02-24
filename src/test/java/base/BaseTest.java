package base;

import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.AllureUtils;
import utilities.Driver;

import java.lang.reflect.Method;

public abstract class BaseTest {

    protected SoftAssert softAssert;

    private static final String ATTACHMENT_DONE_FLAG = "allure.attachments.done";

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
    public void afterEach(ITestResult result) {
        try {
                if (softAssert != null) {
                softAssert.assertAll();
            }
        } catch (AssertionError ae) {
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(ae);
            throw ae;
        } finally {
            try {
                if (result.getStatus() == ITestResult.FAILURE) {
                    Object flag = result.getAttribute(ATTACHMENT_DONE_FLAG);
                    boolean alreadyDone = flag instanceof Boolean && (Boolean) flag;

                    if (!alreadyDone) {
                        var driver = Driver.getDriver();
                        AllureUtils.attachUrl(driver);
                        AllureUtils.attachScreenshot(driver);
                        AllureUtils.attachPageSource(driver);

                        if (result.getThrowable() != null) {
                            AllureUtils.attachText("Failure Reason", result.getThrowable().toString());
                        }

                        result.setAttribute(ATTACHMENT_DONE_FLAG, true);
                    }
                }
            } finally {
                MDC.clear();
            }
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterAll(){
        Driver.quitDriver();
    }
}
