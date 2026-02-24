package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureUtils {

    private AllureUtils() {}

    public static void attachScreenshot(WebDriver driver) {
        if (driver == null) return;
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(png), ".png");
        } catch (Exception ignored) {
            // intentionally swallow: never fail test because of reporting
        }
    }

    public static void attachPageSource(WebDriver driver) {
        if (driver == null) return;
        try {
            String source = driver.getPageSource();
            Allure.addAttachment("Page Source", "text/html",
                    new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), ".html");
        } catch (Exception ignored) {}
    }

    public static void attachText(String name, String text) {
        if (text == null) text = "null";
        Allure.addAttachment(name, "text/plain",
                new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)), ".txt");
    }

    public static void attachUrl(WebDriver driver) {
        if (driver == null) return;
        try {
            attachText("Current URL", driver.getCurrentUrl());
        } catch (Exception ignored) {}
    }
}
