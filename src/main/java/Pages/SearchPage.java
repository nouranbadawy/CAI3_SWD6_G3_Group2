package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage extends BasePage {

    // =====================
    // Locators
    // =====================
    //
    private final By productNames = By.xpath(
            "//div[@id='product-list']//h4/a | " +
                    "//div[@id='product-list']//h3/a | " +
                    "//div[contains(@class,'product-thumb')]//h4/a");

    // Search Result (iPhone) Locator:
    private final By searchProductAddToCartButton = By.xpath("//*[@id=\"product-list\"]/div/div/div[2]/form/div/button[1]");

    // =====================
    // Constructor
    // =====================
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    public boolean isProductDisplayed(String productName) {
        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(productNames));

        // Fix: Print all found product names to console
        System.out.println("Searching for: " + productName);
        driver.findElements(productNames).forEach(p ->
                System.out.println("Found product: " + p.getText())
        );

        return driver.findElements(productNames)
                .stream()
                .anyMatch(p -> p.getText().toLowerCase().contains(productName.toLowerCase()));
    }

    // Add the searched product to cart:
    public void addSearchedProductToCart() {
        driver.findElement(searchProductAddToCartButton).click();
    }
}