package Pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        pagesUtility = new PagesUtility(driver);
    }

    By loginButton = By.id("login2");
    By emailField = By.id("loginusername");
    By passwordfield = By.id("loginpassword");
    By loginSubmitButton = By.xpath("//button[text()='Log in']");
    By logoutButton = By.id("logout2");

    public void loginToApp() throws IOException {
        clickLoginLink();
        enterLoginCredentials();
        clickLoginButton();
        validateLogoutButtonIsPresent();
    }

    public void clickLoginLink() throws IOException {
        pagesUtility.waitForElementVisibility(loginButton);
        driver.findElement(loginButton).click();
        ChainTestListener.log("login application link clicked");
        pagesUtility.waitForElementVisibility(emailField);
    }

    public void clickLoginButton() throws IOException {
        driver.findElement(loginSubmitButton).click();
        ChainTestListener.log("login button clicked");

        pagesUtility.getScreenshot("LoginPage");
        pagesUtility.waitForElementVisibility(logoutButton);
    }

    public void validateLogoutButtonIsPresent() {
        boolean checkLogoutButton = driver.findElement(logoutButton).isDisplayed();
        Assert.assertTrue(checkLogoutButton);
        ChainTestListener.log("Logout button visibility checked");
    }

    public void enterLoginCredentials() throws IOException {
        String userName = SignupPage.username != null ? SignupPage.username : pagesUtility.readPropertyFile().getProperty("userName");
        String password = SignupPage.password != null ? SignupPage.password : pagesUtility.readPropertyFile().getProperty("password");

        driver.findElement(emailField).sendKeys(userName);
        driver.findElement(passwordfield).sendKeys(password);

        if (SignupPage.username != null && SignupPage.password != null)
            ChainTestListener.log("email and password entered from registered user");
        else
            ChainTestListener.log("email and password entered");
    }
}
