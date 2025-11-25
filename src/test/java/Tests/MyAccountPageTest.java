package Tests;

import Pages.MyAccountPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass; //
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class MyAccountPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    // =========================================================
    // =========================================================
    private final String BASE_URL = "http://localhost/opencartproject/index.php?route=account/login&language=en-gb";
    private final String VALID_EMAIL = "muhammednegm94@gmail.com";
    private final String VALID_PASSWORD = "GTB@301020#";

    private final String ADDRESS_BOOK_HEADING = "Address Book Entries"; //
    private final String ORDER_HISTORY_HEADING = "Orders";
    private final String WISH_LIST_HEADING = "My Wishlist";
    private final String RETURNS_HEADING = "Returns";
    private final String TRANSACTIONS_HEADING = "Your Transactions";
    private final String NEWSLETTER_HEADING = "Newsletter Subscription"; //


    // =========================================================
    // =========================================================
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);

        // تسجيل الدخول يتم مرة واحدة
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        if (!driver.getCurrentUrl().contains("route=account/account")) {
            Assert.fail("SETUP FAILED: Login failed or redirection error.");
        }
        System.out.println("Setup Complete: Logged in successfully and driver initialized once.");
    }

    // =========================================================
    // =========================================================
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Teardown Complete: Driver closed.");
        }
    }

    // =========================================================
    // =========================================================

    @Test(priority = 1)
    public void verifyAddressBookNavigation() {
        myAccountPage.clickAddressBook();
        String actualHeading = myAccountPage.getPageHeading();
        Assert.assertEquals(actualHeading, ADDRESS_BOOK_HEADING, "Address Book Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();
    }

    @Test(priority = 2)
    public void verifyOrderHistoryNavigation() {
        myAccountPage.clickOrderHistory();
            String actualHeading = myAccountPage.getPageHeading();
        final String ORDER_HISTORY_HEADING = "Orders";
        Assert.assertEquals(actualHeading, ORDER_HISTORY_HEADING, "Order History Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();
    }

    @Test(priority = 3)
    public void verifyWishListNavigation() {
        myAccountPage.clickWishList();
        String actualHeading = myAccountPage.getPageHeading();
        Assert.assertEquals(actualHeading, WISH_LIST_HEADING, "Wish List Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();
    }

    @Test(priority = 4)
    public void verifyReturnsNavigation() {
        myAccountPage.clickReturns();
        String actualHeading = myAccountPage.getPageHeading();
        Assert.assertEquals(actualHeading, RETURNS_HEADING, "Returns Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();
    }

    @Test(priority = 5)
    public void verifyTransactionsNavigation() {
        myAccountPage.clickTransactions();
        String actualHeading = myAccountPage.getPageHeading();
        Assert.assertEquals(actualHeading, TRANSACTIONS_HEADING, "Transactions Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();}

    @Test(priority = 6)
    public void verifyNewsletterNavigation() {
        myAccountPage.clickNewsletter();
        String actualHeading = myAccountPage.getPageHeading();
        Assert.assertEquals(actualHeading, NEWSLETTER_HEADING, "Newsletter Navigation Failed.");
        myAccountPage.navigateToMyAccountHome();}
}