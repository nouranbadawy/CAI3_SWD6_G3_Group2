package Pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    //variables
    protected static WebDriver driver;

    //Constructor to initialize the WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
