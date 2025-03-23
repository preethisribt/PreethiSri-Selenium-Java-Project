package WebAppTest;

import Pages.CartPage;
import Pages.LoginPage;
import TestUtility.BaseTestUtilityFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.prefs.BackingStoreException;

public class CartPageTest extends BaseTestUtilityFile {

    @Test(groups = {"Web"})
    public void removeAllProductsFromCart() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp();

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToCart();
        cartPage.deleteProductFromCart();
        cartPage.checkCartIsEmpty();
    }

    public void sample()
    {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=old");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        WebDriver drivernew = new ChromeDriver(options);
        drivernew.get("https://automationexercise.com/");
        System.out.println(drivernew.getTitle());
        System.out.println(drivernew.getCurrentUrl());

        drivernew.quit();
    }
}
