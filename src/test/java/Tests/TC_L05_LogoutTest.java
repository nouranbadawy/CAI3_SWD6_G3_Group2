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

public class TC_L05_LogoutTest {
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
    @Test(description = "TC-L05: Logout from user account")
    public void testLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.openLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("arabi@gmail.com");
        loginPage.enterPassword("1234Mn");
        loginPage.clickLogin();

        homePage.logout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("account/logout"),
                "Logout failed - Not redirected to confirmation"
        );
    }
}