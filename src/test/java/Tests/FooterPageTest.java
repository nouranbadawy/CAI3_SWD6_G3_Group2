package Tests;

import Pages.FooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FooterPageTest {

    private WebDriver driver;
    private FooterPage footerPage;

    private static final Map<String, String> EXPECTED_DESTINATIONS = new HashMap<>();
    static {
        // === Information ===
        EXPECTED_DESTINATIONS.put("Terms & Conditions", "Terms & Conditions");
        EXPECTED_DESTINATIONS.put("Delivery Information", "Delivery Information");
        EXPECTED_DESTINATIONS.put("About Us", "About Us");
        EXPECTED_DESTINATIONS.put("Privacy Policy", "Privacy Policy");

        // === Customer Service ===
        EXPECTED_DESTINATIONS.put("Contact Us", "Contact Us");

        EXPECTED_DESTINATIONS.put("Returns", "Returns");

        EXPECTED_DESTINATIONS.put("Site Map", "Site Map");

        // === Extras ===
        EXPECTED_DESTINATIONS.put("Brands", "Find Your Favorite Brand");

        EXPECTED_DESTINATIONS.put("Affiliate", "Account Login");

        EXPECTED_DESTINATIONS.put("Specials", "Special Offers");

        // === My Account ===
        EXPECTED_DESTINATIONS.put("My Account", "Account Login");

        EXPECTED_DESTINATIONS.put("Order History", "Account Login");

        EXPECTED_DESTINATIONS.put("Wish List", "Account Login");

        EXPECTED_DESTINATIONS.put("Newsletter", "Account Login");
    }

    // =========================================================
    // Setup and Teardown (Code is fine here)
    // =========================================================

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartproject/");
        footerPage = new FooterPage(driver);
        System.out.println("Setup Complete: Driver initialized for Footer Tests.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Teardown Complete: Driver closed.");
        }
    }

    // =========================================================
    // Data Provider (Code is fine here)
    // =========================================================

    @DataProvider(name = "footerLinks")
    public Object[][] getFooterLinkData() {
        List<Object[]> data = new ArrayList<>();
        for (Map.Entry<String, String> entry : EXPECTED_DESTINATIONS.entrySet()) {
            data.add(new Object[]{entry.getKey(), entry.getValue()});
        }
        return data.toArray(new Object[0][0]);
    }

    // =========================================================
    // Test Method (Code is fine here)
    // =========================================================

    @Test(dataProvider = "footerLinks")
    public void verifyFooterLinkNavigation(String linkText, String expectedHeading) {
        System.out.println("Testing link: " + linkText);

        WebElement linkElement = footerPage.getLinkByText(linkText);

        Assert.assertNotNull(linkElement, "FAILURE: The link element '" + linkText + "' was not found after navigating back.");

        try {
            footerPage.clickLink(linkElement);
        } catch (Exception e) {
            Assert.fail("Failed to click or wait for page load for link: " + linkText + ". Error: " + e.getMessage());
            return;
        }

        String actualTitle = footerPage.getPageTitle();

        Assert.assertTrue(
                actualTitle.contains(expectedHeading),
                "Navigation failed for link: " + linkText +
                        ". Expected title to contain: '" + expectedHeading +
                        "', but found title: '" + actualTitle + "'"
        );

        footerPage.navigateBack();
    }
}