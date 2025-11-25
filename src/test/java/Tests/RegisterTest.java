package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import Pages.HomePage;
import Pages.RegisterPage;

public class RegisterTest {

    WebDriver driver;
    HomePage home;
    RegisterPage registerPage;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject/");

        home = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void registerFlowOnly() {
        home.openRegisterPage();

        registerPage.enterFirstName("Nouran");
        registerPage.enterLastName("Badawy");
        registerPage.enterEmail("nouranbadawy28@gmail.com");
        registerPage.enterPassword("12345678");

        registerPage.acceptPrivacyPolicy();
        registerPage.clickContinue();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

