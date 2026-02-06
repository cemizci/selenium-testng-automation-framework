package services.TestOtomasyonu;

import org.openqa.selenium.Keys;
import pages.TestOtomasyonuPage.TestOtomasyonuPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.WaitUtils;

public class CartService {

    public static String searchAndAddFirstProductToCart(TestOtomasyonuPage page){

        page.searchBox.sendKeys(ConfigReader.getProperty("searchTerm")+ Keys.ENTER);

        WaitUtils.waitForClickability(page.bulunanUrunElementleriList.get(0));
        page.bulunanUrunElementleriList.get(0).click();

        String productName = page.productPageNameElement.getText();

        BrowserUtils.scroolToElement(page.productPageAddTocartButton);
        page.productPageAddTocartButton.click();

        BrowserUtils.scroolToElement(page.productPageYourCartButton);
        page.productPageYourCartButton.click();
        return productName;
    }
}
