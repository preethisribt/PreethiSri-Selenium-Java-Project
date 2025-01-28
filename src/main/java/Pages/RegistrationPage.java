package Pages;

import Utility.PagesUtility;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.IOException;

public class RegistrationPage {
    WebDriver driver;
    PagesUtility pagesUtility;
    Faker faker;
    private By registerLink = By.linkText("Register");
    private By firstNameText = By.id("customer.firstName");
    private By lastNameText = By.id("customer.lastName");
    private By addressText = By.id("customer.address.street");
    private By cityText = By.id("customer.address.city");
    private By stateText = By.id("customer.address.state");
    private By zipText = By.id("customer.address.zipCode");
    private By phoneText = By.id("customer.phoneNumber");
    private By SSNText = By.id("customer.ssn");
    private By userNameText = By.id("customer.username");
    private By passwordText = By.id("customer.password");
    private By conformPasswordText = By.id("repeatedPassword");
    private By registerButton = By.cssSelector("input[value='Register']");
    private By registrationSuccessMessage = By.xpath("//*[text()='Your account was created successfully. You are now logged in.']");
    public static String userName;
    public static String password;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        pagesUtility = new PagesUtility();
        faker = new Faker();
    }

    public void registerUser() throws IOException {
        driver.findElement(registerLink).click();
        pagesUtility.getScreenshot("RegistrationPage", driver);
    }

    public void completeRegistration() throws IOException {
        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        userName = fname + lname;
        password = userName + "@123";

        System.out.println("userName = " + userName);
        System.out.println("password = " + password);

        driver.findElement(firstNameText).sendKeys(fname);
        driver.findElement(lastNameText).sendKeys(lname);
        driver.findElement(addressText).sendKeys(faker.address().streetAddress());
        driver.findElement(cityText).sendKeys(faker.address().city());
        driver.findElement(stateText).sendKeys(faker.address().state());
        driver.findElement(zipText).sendKeys(faker.address().zipCode());
        driver.findElement(phoneText).sendKeys(faker.phoneNumber().cellPhone());
        driver.findElement(SSNText).sendKeys(String.valueOf(faker.number().numberBetween(10000, 90000)));
        driver.findElement(userNameText).sendKeys(userName);
        driver.findElement(passwordText).sendKeys(password);
        driver.findElement(conformPasswordText).sendKeys(password);

        pagesUtility.getScreenshot("RegistrationPage", driver);
        driver.findElement(registerButton).click();
        pagesUtility.waitForElementVisibility(driver, By.xpath("//*[contains(text(),'Welcome')]"));
    }

    public void validateRegistration() throws IOException {
        boolean welcomeUser = driver.findElement(By.xpath("//*[text()='Welcome " + userName + "']")).isDisplayed();
        boolean registrationMessage = driver.findElement(registrationSuccessMessage).isDisplayed();

        Assert.assertTrue(welcomeUser);
        Assert.assertTrue(registrationMessage);
        pagesUtility.getScreenshot("RegistrationCompletedPage", driver);
    }
}
