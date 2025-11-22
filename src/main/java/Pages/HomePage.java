package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    // =====================
    // Locators
    // =====================
    // Currency Dropdown and its options:
    private final By currencyDropdown = By.cssSelector("#form-currency div.dropdown a.dropdown-toggle");
    private final By euroCurrencyOption = By.partialLinkText("Euro");
    private final By gbpCurrencyOption = By.partialLinkText("Pound Sterling");
    private final By usdCurrencyOption = By.partialLinkText("US Dollar");

    // My Account Dropdown and its options:
    private final By myAccountDropdown = By.linkText("My Account");
    private final By loginLink = By.linkText("Login");
    private final By registerLink = By.linkText("Register");

    // Wish List Link:
    private final By wishListLink = By.id("wishlist-total");

    // Shopping Cart Link:
    private final By shoppingCartLink = By.cssSelector("a[title=\"Shopping Cart\"]");

    // Checkout Link:
    private final By checkoutLink = By.cssSelector("a[title=\"Checkout\"]");

    // Search:
    private final By searchInput = By.cssSelector("input[name='search']");
    private final By searchButton = By.cssSelector("form.input-group button");

    // Desktops Category:
    private final By desktopsDropdown = By.linkText("Desktops");
    private final By showAllDesktopsCategory = By.linkText("Show All Desktops");

    // First Product on Home page:
    private final By firstHomeProduct = By.cssSelector("#content > div.row > div:nth-child(1) > .product-thumb");

    // Add to Cart button for the first product on Home page:
    private final By featuredFirstProductAddToCart = By.cssSelector("#content > div.row > div:nth-child(1) > div > div.content > form > div > button:nth-child(1)");

    // Add to Wish List button for the first product on Home page:
    private final By featuredFirstProductAddToWishList = By.cssSelector("#content > div.row > div:nth-child(1) > div > div.content > form > div > button:nth-child(2)");

    // Compare this snippet from src/main/java/Pages/BasePage.java:
    private final By featuredFirstProductCompare = By.cssSelector("#content > div.row > div:nth-child(1) > div > div.content > form > div > button:nth-child(3)");

    // =====================
    // Constructor
    // =====================
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // =====================
    // Actions
    // =====================

    public void openCurrencyMenu() {
        driver.findElement(currencyDropdown).click();
    }

    public void changeCurrencyToEuro() {
        openCurrencyMenu();
        driver.findElement(euroCurrencyOption).click();
    }

    public void changeCurrencyToGBP() {
        openCurrencyMenu();
        driver.findElement(gbpCurrencyOption).click();
    }

    public void changeCurrencyToUSD() {
        openCurrencyMenu();
        driver.findElement(usdCurrencyOption).click();
    }

    public void openLoginPage() {
        driver.findElement(myAccountDropdown).click();
        driver.findElement(loginLink).click();
    }

    public void openRegisterPage() {
        driver.findElement(myAccountDropdown).click();
        driver.findElement(registerLink).click();
    }

    public void openWishList() {
        driver.findElement(wishListLink).click();
    }

    public void openShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }

    public void openCheckout() {
        driver.findElement(checkoutLink).click();
    }

    public void openAllDesktopsCategory() {
        driver.findElement(desktopsDropdown).click();
        driver.findElement(showAllDesktopsCategory).click();
    }

    public void searchForProduct(String productName) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addFirstProductToCart() {
        driver.findElement(featuredFirstProductAddToCart).click();
    }

    public void addFirstProductToWishList() {
        driver.findElement(featuredFirstProductAddToWishList).click();
    }

    public void addFirstProductToCompare() {
        driver.findElement(featuredFirstProductCompare).click();
    }

}
