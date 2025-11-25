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

public class TC_C01_CurrencyTest {
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

    @Test(description = "TC-C01: Change currency from selector")
    public void testChangeCurrencyToEuro() {
        HomePage homePage = new HomePage(driver);
        homePage.changeCurrencyToEuro();

        CurrenciesPage currencyPage = new CurrenciesPage(driver);
        Assert.assertTrue(
                currencyPage.arePricesInCurrency("€"),
                "Currency change failed - Prices not showing in Euro"
        );
    }
}