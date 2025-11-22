package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {
    // =====================
    // Locators
    // =====================
    private final By cartProductName = By.partialLinkText("MacBook");

    // =====================
    // Constructor
    // =====================
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    public String getProductNameFromCart() {
        return driver.findElement(cartProductName).getText();
    }
}
