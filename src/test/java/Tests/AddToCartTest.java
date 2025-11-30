package Tests;

import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AddToCartTest {
    WebDriver driver;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject");

        homePage = new HomePage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test(description = "GP-57 | Verify that a product can be added to the shopping cart from Featured Section")
    public void addToCartFromFeaturedSection() {
        homePage.addFirstProductToCart();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title*='Cart']")));
        homePage.openShoppingCart();

        String cartProductName = shoppingCartPage.getProductNameFromCart();
        Assert.assertEquals(cartProductName, "MacBook", "Product does not added to the cart!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}