package Pages;

import Utility.PagesUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginPage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        pagesUtility = new PagesUtility();
    }

    By loginButton = By.id("login2");
    By emailField = By.id("loginusername");
    By passwordfield = By.id("loginpassword");
    By loginSubmitButton = By.xpath("//button[text()='Log in']");
    By logoutButton = By.id("logout2");

    public void login() throws IOException {
        pagesUtility.waitForElementVisibility(driver,loginButton);
        driver.findElement(loginButton).click();
        ChainTestListener.log("login application clicked");

        pagesUtility.waitForElementVisibility(driver,emailField);
        driver.findElement(emailField).sendKeys(pagesUtility.readPropertyFile().getProperty("userName"));
        driver.findElement(passwordfield).sendKeys(pagesUtility.readPropertyFile().getProperty("password"));
        ChainTestListener.log("email and password entered");

        driver.findElement(loginSubmitButton).click();
        ChainTestListener.log("login button clicked");

        pagesUtility.getScreenshot("LoginPage",driver);
        pagesUtility.waitForElementVisibility(driver,logoutButton);

        boolean checkLogoutButton = driver.findElement(logoutButton).isDisplayed();
        Assert.assertTrue(checkLogoutButton);
        ChainTestListener.log("Logout button visibility checked");
    }
}
