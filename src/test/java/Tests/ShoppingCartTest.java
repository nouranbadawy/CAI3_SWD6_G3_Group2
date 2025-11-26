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

        By addToCartLocator = By.xpath(
                "//div[contains(@class,'product-thumb')]//a[text()='MacBook']/ancestor::div[contains(@class,'product-thumb')]//button[@aria-label='Add to Cart']"
        );

        int attempts = 0;
        boolean clicked = false;

        while(attempts < 3 && !clicked) {
            try {
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
                js.executeScript("arguments[0].click();", addToCartBtn);
                clicked = true;
            } catch (TimeoutException | ElementClickInterceptedException e) {
                System.out.println("Attempt " + (attempts + 1) + " failed, retrying...");
                js.executeScript("window.scrollBy(0, 200);");
                attempts++;
            }
        }

        if(!clicked) {
            Assert.fail("Could not click 'Add to Cart' button after 3 attempts!");
        }

        By successAlert = By.cssSelector(".alert-success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));

        driver.get("http://localhost/opencartproject/index.php?route=checkout/cart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".table-responsive tbody tr td:nth-child(2) a")
        ));

        String productName = shoppingCartPage.getProductNameFromCart();
        Assert.assertEquals(productName, "MacBook", "The product added to shopping cart!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
