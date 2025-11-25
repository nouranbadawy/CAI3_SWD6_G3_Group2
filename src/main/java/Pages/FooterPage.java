package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class FooterPage {
    private final WebDriver driver;
    private final int TIMEOUT_SECONDS = 20;

    public FooterPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLinkByText(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        String cleanedLinkText = linkText.trim();
        String xpath = String.format("//footer//div[@class='col-sm-3']//ul/li/a[normalize-space()='%s']", cleanedLinkText);
        By specificLinkLocator = By.xpath(xpath);

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(specificLinkLocator));
        } catch (TimeoutException e) {
            System.err.println("Element not found (Timeout) for link: " + linkText);
            return null;
        }
    }

    public void clickLink(WebElement linkElement) {
        if (linkElement == null) {
            throw new IllegalArgumentException("Cannot click: The link element is null (failed to retrieve it).");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(linkElement));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);

            wait.until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for element to be clickable or page to load after clicking.");
            throw e;
        }
    }

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.titleIs(driver.getTitle()));
        return driver.getTitle();
    }

    public void navigateBack() {
        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
}