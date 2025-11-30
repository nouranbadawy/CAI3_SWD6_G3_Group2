package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    // =====================
    // Locators.
    // =====================
    private final By emailInput = By.id("input-email");
    private final By passwordInput = By.id("input-password");
    private final By loginButton = By.xpath("//button[@type='submit' and contains(.,'Login')]");
    // FIX.:
    private final By myAccountHeader = By.xpath("//div[@id='content']//h1[contains(text(),'My Account')]");

    // =====================
    // Constructor
    // =====================
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
        // Wait for My Account page to load.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(myAccountHeader));
    }

    public String getMyAccountHeaderText() {
        return driver.findElement(myAccountHeader).getText();
    }

    public void login(String validEmail, String validPassword) {
        enterEmail(validEmail);
        enterPassword(validPassword);
        clickLogin();
    }

    public String getWarningMessageText() {
        return "";
    }
}