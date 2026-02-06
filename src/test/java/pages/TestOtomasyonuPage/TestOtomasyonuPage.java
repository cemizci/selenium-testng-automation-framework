package pages.TestOtomasyonuPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TestOtomasyonuPage {

    public TestOtomasyonuPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "global-search")
    public WebElement searchBox;

    @FindBy(className = "product-count-text")
    public WebElement aramaSonucYaziElementi;

    @FindBy(className = "prod-img")
    public List<WebElement> bulunanUrunElementleriList;

    @FindBy(xpath = "//*[@class=' heading-sm mb-4']")
    public WebElement productPageNameElement;

    @FindBy(id = "priceproduct")
    public WebElement productPagePriceElement;

    @FindBy(xpath = "(//span[.='Account'])[1]")
    public WebElement accountLinki;

    @FindBy(id = "email")
    public WebElement loginPageEmailBox;

    @FindBy(id="password")
    public WebElement loginPagePasswordBox;

    @FindBy(id="submitlogin")
    public WebElement loginPageSubmitButton;

    @FindBy(xpath = "//span[.='Logout']")
    public WebElement logoutButton;

    @FindBy (className = "add-to-cart")
    public WebElement productPageAddTocartButton;

    @FindBy (xpath = "(//*[@class='e-cart'])[2]")
    public WebElement productPageYourCartButton;

    @FindBy (xpath = "//*[@class='product-title text-center']")
    public WebElement yourCartPageProductName;

    @FindBy (xpath = "//*[.='Checkout']")
    public WebElement yourCartCheckoutButton;

    @FindBy (xpath = "(//button[@class='btn-add-address'])[1]")
    public WebElement addBillingAdressBtn;

    @FindBy (xpath = "//input[@id='name']")
    public WebElement formControlNameInput;

    @FindBy (xpath = "//select[@id='country_id']")
    public WebElement formControlCountryId;

    @FindBy (xpath = "//input[@id='city']")
    public WebElement inputCity;

    @FindBy (xpath = "//input[@id='postcode']")
    public WebElement inputPostCode;

    @FindBy (xpath = "//p[@class='addressItem622']")
    public WebElement addressBox;

    @FindBy (xpath = "//select[@id='add_address_state']")
    public WebElement optionCity;

    @FindBy (xpath = "//button[.='Add Address']")
    public  WebElement addAdressBtn;

    @FindBy (xpath = "(//label[@class='check-label'])[1]")
    public WebElement billingAddressCheckmark;

    @FindBy (xpath = "(//label[@class='check-label'])[2]")
    public  WebElement sameBillingAddressCheckmark;

    @FindBy (xpath = "(//label[@class='check-label'])[3]")
    public WebElement shippingMethodsCheckmark;

    @FindBy (xpath = "//span[.='Cash On Delivery']")
    public WebElement cashOnDeliveryPaymentOption;

    @FindBy (xpath = "//input[@name='accept_terms']")
    public WebElement acceptTermsCheckbox;

    @FindBy (xpath = "//button[.='Place Order Now ']")
    public WebElement placeOrderNowButton;

    @FindBy (xpath = "//h2[.='Your order is successfully done!']")
    public WebElement orderSuccesfullyText;

    @FindBy (xpath = "//a[.='View Order ']")
    public WebElement viewOrderButton;

    @FindBy (xpath = "(//a[.='Order Details'])[1]")
    public WebElement orderDetailsButton;

}
