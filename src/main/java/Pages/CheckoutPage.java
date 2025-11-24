package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    // =====================
    // Locators
    // =====================
    // Your Personal Details Locators:
    private final By guestCheckoutRadioButton = By.id("input-guest");
    private final By firstNameInput = By.id("input-firstname");
    private final By lastNameInput = By.id("input-lastname");
    private final By emailInput = By.id("input-email");
    // Shipping Address Locators:
    private final By address1Input = By.id("input-shipping-address-1");
    private final By cityInput = By.id("input-shipping-city");
    private final By postCodeInput = By.id("input-shipping-postcode");
    private final By countryDropdown = By.id("input-shipping-country");
    private final By regionDropdown = By.id("input-shipping-zone");
    private final By newsletterCheckbox = By.id("input-newsletter");
    private final By ContinueButton = By.id("button-register");
    // Shipping Method Locators:
    private final By chooseShippingMethodButton = By.id("button-shipping-methods");
    private final By shippingMethodOptionsButton = By.id("button-shipping-method");
    // Payment Method Locators:
    private final By choosePaymentMethodButton = By.id("button-payment-methods");
    private final By paymentMethodOptionsButton = By.id("button-payment-method");
    // Confirm Order Locators:
    private final By confirmOrderButton = By.id("button-confirm");
    private final By orderConfirmationText = By.cssSelector("#content h1");
    private final By orderContinueButton = By.linkText("Continue");

    // =====================
    // Constructor
    // =====================
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    // Your Personal Details Actions:
    public void selectGuestCheckout() {
        driver.findElement(guestCheckoutRadioButton).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Shipping Address Actions:
    public void enterAddress1(String address1) {
        driver.findElement(address1Input).sendKeys(address1);
    }

    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void enterPostCode(String postCode) {
        driver.findElement(postCodeInput).sendKeys(postCode);
    }

    public void selectCountry(String country) { // 63 EGYPT
        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByValue(country);
    }

    public void selectRegion(String region) { // 1011 AlQahira
        Select regionSelect = new Select(driver.findElement(regionDropdown));
        regionSelect.selectByValue(region);
    }

    public void subscribeToNewsletter() {
        driver.findElement(newsletterCheckbox).click();
    }

    public void clickContinue() {
        driver.findElement(ContinueButton).click();
    }

    public void fillGuestInfo(String firstName, String lastName, String email,
                              String address1, String city, String postcode,
                              String country, String region) {

        selectGuestCheckout();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterAddress1(address1);
        enterCity(city);
        enterPostCode(postcode);
        selectCountry(country);
        // Wait for regions to load after selecting country
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(regionDropdown));
        selectRegion(region);
        subscribeToNewsletter();
        clickContinue();
    }

    // Shipping Method Actions:
    public void chooseShippingMethod() {
        driver.findElement(chooseShippingMethodButton).click();
    }

    public void selectShippingMethodOption() {
        driver.findElement(shippingMethodOptionsButton).click();
    }

    // Payment Method Actions:
    public void choosePaymentMethod() {
        driver.findElement(choosePaymentMethodButton).click();
    }

    public void selectPaymentMethodOption() {
        driver.findElement(paymentMethodOptionsButton).click();
    }

    // Confirm Order Action:
    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }

    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmationText).getText();
    }

    public void clickOrderContinue() {
        driver.findElement(orderContinueButton).click();
    }
}
