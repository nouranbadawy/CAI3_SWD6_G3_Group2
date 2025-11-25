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

public class TC_H01_HomepageTest {
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

    @Test(description = "TC-H01: Homepage loads successfully")
    public void testHomepageLoad() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isLogoDisplayed(),
                "Homepage failed to load - Logo not displayed"
        );

        // FIX: Add null check for title
        String title = driver.getTitle();
        if (title == null) {
            Assert.fail("Page title is null - homepage failed to load");
        }

        Assert.assertFalse(
                title.toLowerCase().contains("404") || title.toLowerCase().contains("error"),
                "Homepage contains errors in title"
        );
    }
}