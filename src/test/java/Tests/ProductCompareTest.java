package Tests;

import Pages.CategoryPage;
import Pages.HomePage;
import Pages.ProductComparePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductCompareTest {
	WebDriver driver;
	HomePage homePage;
	CategoryPage categoryPage;
	ProductComparePage productComparePage;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/opencartproject");
		homePage = new HomePage(driver);
		categoryPage = new CategoryPage(driver);
		productComparePage = new ProductComparePage(driver);
	}

	@Test(description = "GP-162 | Verify that products can be compared successfully")
	public void verifyProductComparison() {
		homePage.openCamerasCategory();

		String headerText = categoryPage.getCameraHeaderText();
		Assert.assertEquals(headerText, "Cameras", "Cameras category page not opened!");

		categoryPage.addFirstProductToCompare();
		categoryPage.addSecondProductToCompare();

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("compare-total")));

		categoryPage.openProductComparisonPage();

		String comparePageText = productComparePage.getProductComparisonHeaderText();
		Assert.assertEquals(comparePageText, "Product Comparison", "Product Comparison page not opened!");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
