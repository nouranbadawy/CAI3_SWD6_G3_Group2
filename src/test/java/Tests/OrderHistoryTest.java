package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.OrderHistoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTest {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    OrderHistoryPage orderHistoryPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
    }

    @Test(description = "Verify that the register user able to see his orders.")
    public void testOrderHistory() {
        homePage.openLoginPage();
        loginPage.enterEmail("hazem.saied00@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        String myAccountHeader = loginPage.getMyAccountHeaderText();
        Assert.assertEquals(myAccountHeader, "My Account", "Login failed or My Account page not displayed.");
        orderHistoryPage.navigateToOrderHistory();
        orderHistoryPage.verifyOrderHistoryPage();
        orderHistoryPage.viewOrderDetails();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}