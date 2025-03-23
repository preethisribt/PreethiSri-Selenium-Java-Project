package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class SignupPage {
    WebDriver driver;
    PagesUtility pagesUtility;
    Faker faker;
    By signupButton = By.id("signin2");
    By usernameText = By.id("sign-username");
    By passwordText = By.id("sign-password");
    By signupRegistrationButton = By.xpath("//button[text()='Sign up']");
    static String username;
    static String password;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility(driver);
        faker = new Faker();
    }

    public void signUp() throws IOException {
        username = faker.name().firstName();
        password = username + faker.random().nextInt(0, 100);

        pagesUtility.waitForElementVisibility(signupButton);
        pagesUtility.element(signupButton).click();
        pagesUtility.waitForElementVisibility(usernameText);
        pagesUtility.element(usernameText).sendKeys(username);
        pagesUtility.element(passwordText).sendKeys(password);
        pagesUtility.getScreenshot("Signup Page");
        pagesUtility.element(signupRegistrationButton).click();
        pagesUtility.waitForAlert();
    }

    public void validateSignupSuccessMessage() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        Assert.assertEquals(message, "Sign up successful.");
        alert.accept();
    }
}