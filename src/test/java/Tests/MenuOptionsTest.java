package Tests;

import Pages.CategoryPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

	public class MenuOptionsTest {
		WebDriver driver;
		HomePage homePage;
		CategoryPage categoryPage;

		@BeforeMethod
		public void setup() {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://localhost/opencartproject");
			homePage = new HomePage(driver);
			categoryPage = new CategoryPage(driver);
		}

		@Test(description = "GP-146 | Verify that the menu options navigate to correct category pages")
		public void verifyDesktopsMenuOption() {
			homePage.openAllDesktopsCategory();

			String headerText = categoryPage.getCategoryHeaderText();
			Assert.assertEquals(headerText, "Desktops", "Desktops category page not opened!");
		}

		@AfterMethod

		public void tearDown() {
			if (driver != null) {
				driver.quit();
			}
		}
	}
