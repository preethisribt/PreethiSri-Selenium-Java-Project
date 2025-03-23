package WebAppTest;

import Pages.SignupPage;
import Pages.LoginPage;
import TestUtility.BaseTestUtilityFile;
import org.testng.annotations.Test;

import java.io.IOException;

public class NewCustomerTest extends BaseTestUtilityFile {


    @Test
    public void validateUserAbleToRegister() throws IOException {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signUp();
        signupPage.validateSignupSuccessMessage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp(); //login and validate
    }
}
