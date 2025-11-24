package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest {
    WebDriver driver;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;

    @BeforeTest
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
        homePage.openShoppingCart();

        String cartProductName = shoppingCartPage.getProductNameFromCart();
        Assert.assertEquals(cartProductName, "MacBook", "Product does not added to the cart!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
