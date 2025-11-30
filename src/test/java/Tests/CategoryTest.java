package Tests;

import Pages.CategoryPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategoryTest {
    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject/");
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @Test(description = "Verify that user can navigate to 'Desktops' category from menu options")
    public void verifyUserCanOpenDesktopsFromMenu() {
        homePage.openAllDesktopsCategory();
        String categoryTitle = categoryPage.getCategoryHeaderText();
        Assert.assertEquals(categoryTitle, "Desktops", "Category title does not match expected title!");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
