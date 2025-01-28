package ParaBankTestCases;

import BaseTest.BaseTest;
import Pages.LoginPage;
import Pages.RegistrationPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends BaseTest {
    @Test
    public void validateNewUserAbleToCompleteRegistration() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser();
        registrationPage.completeRegistration();
        registrationPage.validateRegistration();
    }
    @Test(dependsOnMethods = {"validateNewUserAbleToCompleteRegistration"})
    public void validateExistingUserAbleToLogin() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginToApplication();
    }
}
