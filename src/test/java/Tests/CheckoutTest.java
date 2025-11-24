package Tests;

import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest {
    WebDriver driver;
    HomePage homePage;
    CheckoutPage checkoutPage;
    SearchPage searchPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject/");

        homePage = new HomePage(driver);
        checkoutPage = new CheckoutPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test(description = "GP-71 | Verify that User can checkout as a guest user")
    public void verifyGuestCheckoutProcess() {
        // Search for a product and add it to the cart:
        homePage.searchForProduct("iPhone");
        // Wait until Search Results page loads
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("product-list")));
        // Add the product to the cart from Search Results page:
        //! searchPage.addSearchedProductToCart();
        // Open Checkout:
        homePage.openCheckout();
        // Fill-in checkout details as a guest:
        checkoutPage.fillGuestInfo("Hazem", "Elsayed", "hazem.saied00@gmail.com", "123 Main St", "Cairo", "12345", "63", "1011");
        // Shipping Methods:
        checkoutPage.chooseShippingMethod();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-shipping")));
        checkoutPage.selectShippingMethodOption();
        // Payment Methods:
        checkoutPage.choosePaymentMethod();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-payment")));
        checkoutPage.selectPaymentMethodOption();
        // Wait until Confirm Order button becomes Active:
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
        // Confirm the order:
        checkoutPage.confirmOrder();
        // Wait until order confirmation page loads
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your order has been placed!')]")));
        // Verify order placement:
        String confirmationText = checkoutPage.getOrderConfirmationText();
        Assert.assertEquals(confirmationText, "Your order has been placed!", "Order was not placed successfully.");
        // Continue after order confirmation:
        checkoutPage.clickOrderContinue();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
