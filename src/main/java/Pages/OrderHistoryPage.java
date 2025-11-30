package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHistoryPage extends BasePage {
    // =====================
    // Locators
    // =====================
    private final By orderHistoryLink = By.cssSelector("#column-right > div.list-group > a:nth-child(7)");
    private final By orderHistoryHeader = By.cssSelector("#content > h1");
    private final By ordersTable = By.cssSelector("#content > div.table-responsive > table");
    private final By firstOrderViewButton  = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[6]/a");

    // =====================
    // Constructor
    // =====================
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================
    public void navigateToOrderHistory() {
        driver.findElement(orderHistoryLink).click();
    }

    public void verifyOrderHistoryPage() {
        driver.findElement(orderHistoryHeader).isDisplayed();
        driver.findElement(ordersTable).isDisplayed();
    }

    public void viewOrderDetails() {
        driver.findElement(firstOrderViewButton).click();
    }
}
