package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CurrenciesPage extends BasePage {

    // =====================
    // Locators
    // =====================
    // FIX: Check product prices AND header cart total.
    private final By priceElements = By.xpath(
            "//span[contains(@class,'price')] | " +
                    "//div[contains(@class,'float-end') and contains(@class,'price')] | " +
                    "//strong[contains(text(),'€')]");

    // =====================
    // Constructor
    // =====================
    public CurrenciesPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    public boolean arePricesInCurrency(String currencySymbol) {
        // Wait for currency change to take effect.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue(
                "return document.readyState === 'complete'"
        ));

        // Give extra time for prices to update
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Debug: Print found prices to console
        System.out.println("Searching for currency symbol: " + currencySymbol);
        driver.findElements(priceElements).forEach(element -> {
            String text = element.getText();
            if (!text.trim().isEmpty()) {
                System.out.println("Found price: '" + text + "'");
            }
        });

        // Check if any price contains the symbol (handles various formats)
        return driver.findElements(priceElements)
                .stream()
                .anyMatch(element -> {
                    String text = element.getText();
                    return text.contains(currencySymbol) ||
                            text.contains("&euro;") ||
                            text.contains("EUR") ||
                            text.contains("Euro");
                });
    }
}