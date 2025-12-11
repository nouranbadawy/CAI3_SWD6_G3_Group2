package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductComparePage extends BasePage {
	// =====================
	// Locators
	// =====================
	private final By productComparisonHeader = By.cssSelector("#content h1");
	private final By comparedProductsNames = By.cssSelector("#content > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a");

	// =====================
	// Constructor
	// =====================
	public ProductComparePage(WebDriver driver) {
		super(driver);
	}

	// =====================
	// Actions
	// =====================
	public String getProductComparisonHeaderText() {
		return driver.findElement(productComparisonHeader).getText();
	}
}
