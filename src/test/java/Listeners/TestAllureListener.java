package listeners;

import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.AllureUtils;
import utilities.Driver;

public class TestAllureListener implements ITestListener {
    private static final String ATTACHMENT_DONE_FLAG = "allure.attachments.done";

    @Override
    public void onTestFailure(ITestResult result) {
        Object flag = result.getAttribute(ATTACHMENT_DONE_FLAG);
        if (flag instanceof Boolean && (Boolean) flag) return;

        result.setAttribute(ATTACHMENT_DONE_FLAG, true);

        var driver = Driver.getDriver();
        AllureUtils.attachUrl(driver);
        AllureUtils.attachScreenshot(driver);
        AllureUtils.attachPageSource(driver);

        if (result.getThrowable() != null) {
            AllureUtils.attachText("Failure Reason", result.getThrowable().toString());
        }

        MDC.clear();
    }

    @Override
    public void onTestSuccess(ITestResult context) {
        MDC.clear();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MDC.clear();
    }
}
