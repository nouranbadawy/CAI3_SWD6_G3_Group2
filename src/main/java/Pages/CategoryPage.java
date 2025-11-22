package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends BasePage {
    // =====================
    // Locators
    // =====================
    private final By categoryHeader = By.cssSelector("#content h1");

    // =====================
    // Constructor
    // =====================
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================

    public String getCategoryHeaderText() {
        return driver.findElement(categoryHeader).getText();
    }
}
