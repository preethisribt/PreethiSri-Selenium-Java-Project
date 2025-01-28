package Pages;

import Utility.PagesUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

public class LoginPage {
    private By userNameText = By.name("username");
    private By passwordText = By.name("password");
    private By loginButton = By.cssSelector("input[value='Log In']");
    private By logoutButton = By.linkText("Log Out");
    WebDriver driver;
    PagesUtility pagesUtility;
    Properties properties;
    RegistrationPage registrationPage;

    public LoginPage(WebDriver driver) throws IOException {

        this.driver = driver;
        pagesUtility = new PagesUtility();
        properties = pagesUtility.readPropertyFile();
//        registrationPage = new RegistrationPage(driver);
    }

    public void LoginToApplication() throws IOException {
        pagesUtility.getScreenshot("LoginPage", driver);
        driver.findElement(userNameText).sendKeys(RegistrationPage.userName);
        ChainTestListener.log("Entered user name");

        driver.findElement(passwordText).sendKeys(RegistrationPage.password);
        ChainTestListener.log("Entered password");

        driver.findElement(loginButton).click();
        ChainTestListener.log("clicked on login button");

        Assert.assertTrue(driver.findElement(logoutButton).isDisplayed());
        ChainTestListener.log("Validated logout button is displayed");
        pagesUtility.getScreenshot("LogoutPage", driver);
    }
}
