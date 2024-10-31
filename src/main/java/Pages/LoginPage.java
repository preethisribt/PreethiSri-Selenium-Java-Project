package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginPage {
    WebDriver driver;
    PagesUtility pagesUtility;
    Method method;

    public LoginPage(WebDriver driver, Method method) {
        this.driver = driver;
        pagesUtility = new PagesUtility();
        this.method = method;
    }

    By loginButton = By.xpath("//a[text()=' Signup / Login']");
    By emailField = By.name("email");
    By passwordfield = By.name("password");
    By submitButton = By.xpath("//button[text()='Login']");
    By logoutButton = By.xpath("//a[@href='/logout\']");

    public void login() throws IOException {
        driver.findElement(loginButton).click();
        driver.findElement(emailField).sendKeys(pagesUtility.readPropertyFile().getProperty("userName"));
        driver.findElement(passwordfield).sendKeys(pagesUtility.readPropertyFile().getProperty("password"));
        driver.findElement(submitButton).click();

        pagesUtility.getScreenshot(method,driver);
        boolean checkLogoutButton = driver.findElement(logoutButton).isDisplayed();
        Assert.assertTrue(checkLogoutButton);
    }
}
