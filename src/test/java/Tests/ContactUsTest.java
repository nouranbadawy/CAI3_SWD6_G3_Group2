package Tests;

// Fixes 'package io.github.bonigarcia.wdm does not exist'
// Fixes 'package org.openqa.selenium does not exist' and 'cannot find symbol class WebDriver'
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; // Fixes 'cannot find symbol class ChromeDriver'

// Fixes all TestNG related errors like 'cannot find symbol class Test' and 'Assert'
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.ContactUsPage;


public class ContactUsTest {

    // Instance variables (like in your AddToCartTest)
    WebDriver driver;
    ContactUsPage contactPage;

    @BeforeTest // Setup method runs once before all tests in this class
    public void setup() {
        // Initializes Chrome Driver automatically

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to your specific Localhost OpenCart Contact Page
        driver.get("http://localhost/opencartproject/index.php?route=information/contact");

        contactPage = new ContactUsPage(driver);
    }

    @Test(description = "Verify successful submission of the Contact Us form")
    public void verifySuccessfulContactSubmission() {

        System.out.println("Starting Contact Form Test...");

        // 1. Enter details (using your registered email)
        contactPage.enterName("Muhammed Negm");
        contactPage.enterEmail("muhammednegm94@gmail.com");
        contactPage.enterEnquiry("Hello My Friend.");

        // 2. Click submit
        contactPage.clickSubmit();

        // 3. Assertion: Verify the success message appears
        String expectedMessage = "Your enquiry has been successfully sent to the store owner!";
        String actualMessage = contactPage.getSuccessMessageText();

        // TestNG Assertion
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Assertion Failed: Expected success message not found. Actual: " + actualMessage);
        System.out.println("Test Passed: Contact form submitted successfully.");
    }

    @AfterTest // Teardown method runs once after all tests in this class are complete
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}