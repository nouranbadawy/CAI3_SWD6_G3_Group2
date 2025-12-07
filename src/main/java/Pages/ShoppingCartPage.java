package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
   protected WebDriver driver;

    private final By productNameInCart = By.cssSelector(".table-responsive tbody tr td:nth-child(2) a");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductNameFromCart() {
        return driver.findElement(productNameInCart).getText();
    }
}
