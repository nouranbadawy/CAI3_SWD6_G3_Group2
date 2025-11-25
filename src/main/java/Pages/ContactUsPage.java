package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage extends BasePage {

    private final By nameField = By.id("input-name");
    private final By emailField = By.id("input-email");
    private final By enquiryField = By.id("input-enquiry");
    private final By submitButton = By.cssSelector("input[type='submit']");
    private final By successMessage = By.xpath("//div[@id='content']/p");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterEnquiry(String enquiry) {
        driver.findElement(enquiryField).sendKeys(enquiry);
    }

    public void clickSubmitButton() {
        WebElement button = driver.findElement(submitButton);
        button.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("contact/success"));
    }

    public String getSuccessMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        );
        assert message != null;
        return message.getText();
    }


    public void clickSubmit() {

    }
}
