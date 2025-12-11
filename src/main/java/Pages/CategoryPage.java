package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends BasePage {
    // =====================
    // Locators
    // =====================
    private final By categoryHeader = By.cssSelector("#content h1");
	private final By cameraHeader = By.cssSelector("#content h1");
	private final By firstProductCompare =
		By.xpath("//*[@id=\"product-list\"]/div[1]/div/div[2]/form/div/button[3]");
	private final By secondProductCompare =
		By.xpath("//*[@id=\"product-list\"]/div[2]/div/div[2]/form/div/button[3]");
	private final By productCompareLink = By.id("compare-total");

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
	public String getCameraHeaderText() {
		return driver.findElement(cameraHeader).getText();
	}
	public void addFirstProductToCompare() {
		driver.findElement(firstProductCompare).click();
	}
	public void addSecondProductToCompare() {
		driver.findElement(secondProductCompare).click();
	}
	public void openProductComparisonPage() {
		driver.findElement(productCompareLink).click();
	}
}
