package Tests;

import Pages.ShoppingCartPage;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ShoppingCartTest {

    WebDriver driver;
    ShoppingCartPage shoppingCartPage;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject");

        shoppingCartPage = new ShoppingCartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
        
    }
    @Test(description = "Verify MacBook appears in shopping cart after adding it")
    public void verifyProductAppearsInShoppingCart() {

        js.executeScript("window.scrollBy(0, 800);");

        By addToCartLocator = By.xpath("//*[@id='content']/div[2]/div[1]/div/div[2]/form/div/button[1]");

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
        js.executeScript("arguments[0].click();", addToCartBtn);

        By successAlert = By.cssSelector(".alert-success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));

        driver.get("http://localhost/opencartproject/index.php?route=checkout/cart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".table-responsive tbody tr td:nth-child(2) a")
        ));

        String productName = shoppingCartPage.getProductNameFromCart();

        Assert.assertEquals(productName, "MacBook",
                "The product added to the shopping cart!");
    }
    @AfterTest
    public void tearDown() {
    }
}
