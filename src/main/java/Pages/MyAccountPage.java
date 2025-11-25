package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MyAccountPage {

    protected WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // =========================================================
    // Locators
    // =========================================================
    private final By addressBookLink = By.xpath("//a[normalize-space()='Address Book']");
    private final By returnsLink = By.xpath("//a[normalize-space()='Returns']");
    private final By transactionsLink = By.partialLinkText("Transactions");
    private final By newsletterLink = By.xpath("//a[normalize-space()='Newsletter']");
    private final By myAccountSidebarLink = By.cssSelector("#column-right a[href*='route=account/account']");
    private final By pageHeading = By.xpath("//div[@id='content']/h1");
    private final By orderHistoryLink = By.partialLinkText("Order");
    private final By wishListLink = By.xpath("//a[normalize-space()='Wish List']");// =========================================================
    // =========================================================
    private void clickUsingJS(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        try { wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("order"),
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        ));
        } catch (Exception e) {
        }

    }
    // =========================================================
    // Public Action Methods
    // =========================================================
    public void clickAddressBook() {
        clickUsingJS(addressBookLink);
    }

    public void clickWishList() {
        clickUsingJS(wishListLink);
    }

    public void clickReturns() {
        clickUsingJS(returnsLink);
    }

    public void clickTransactions() {
        clickUsingJS(transactionsLink);
    }

    public void clickNewsletter() {
        clickUsingJS(newsletterLink);
    }

    public void navigateToMyAccountHome() {
        try {
            WebElement sidebarLink = driver.findElement(By.cssSelector("#column-right a[href*='route=account/account']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", sidebarLink);
        } catch (Exception e) {
            driver.navigate().back();
        }
    }

    // Validation Method
    public String getPageHeading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headingElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/h1")));

        return headingElement.getText().trim();

        }


    public void clickOrderHistory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        try {
            wait.until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );
        } catch (Exception e) {
        }
    }

    public String getAccountPageTitle() {
        return "";
    }
}

