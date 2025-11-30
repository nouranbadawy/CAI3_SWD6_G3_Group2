package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_S01_SearchTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost/opencartproject/");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    //
    @Test(description = "TC-S01: Search existing product by exact name")
    public void testSearchExistingProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct("MacBook");

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(
                searchPage.isProductDisplayed("MacBook"),
                "MacBook product not found in search results"
        );
    }
}