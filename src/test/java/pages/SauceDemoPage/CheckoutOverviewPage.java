package pages.SauceDemoPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CheckoutOverviewPage {
    public CheckoutOverviewPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".title")
    public WebElement pageTitle;

    @FindBy(css = ".inventory_item_name")
    public WebElement itemName;

    @FindBy(css = ".checkout_summary_container")
    public WebElement summaryContainer;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(css = ".summary_info_label")
    public List<WebElement> summaryLabels;

    @FindBy(css = ".summary_value_label, .summary_subtotal_label, .summary_tax_label, .summary_total_label")
    public List<WebElement> summaryValues;


    @FindBy(css = ".summary_subtotal_label")
    public WebElement itemTotalLabel;

    @FindBy(css = ".summary_tax_label")
    public WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    public WebElement totalLabel;

    @FindBy(css = "[data-test='payment-info-value']")
    public WebElement paymentInfo;

    @FindBy(css = "[data-test='shipping-info-value']")
    public WebElement shippingInfo;

    @FindBy(css = "[data-test='subtotal-label']")
    public WebElement itemTotal;

    @FindBy(css = "[data-test='tax-label']")
    public WebElement tax;

    @FindBy(css = "[data-test='total-label']")
    public WebElement total;

    public void printSummaryDetails() {

        System.out.println("=== SUMMARY ===");

        int pairCount = Math.min(summaryLabels.size(), summaryValues.size());
        for (int i = 0; i < pairCount; i++) {
            System.out.println(summaryLabels.get(i).getText());
            System.out.println(summaryValues.get(i).getText());
        }

        System.out.println(itemTotalLabel.getText());
        System.out.println(taxLabel.getText());
        System.out.println(totalLabel.getText());

        System.out.println("=============");
    }
}
