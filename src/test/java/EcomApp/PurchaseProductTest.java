package EcomApp;

import BaseTest.BaseTestUtilityFile;
import Pages.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class PurchaseProductTest extends BaseTestUtilityFile {

    @Test
    public void loginToApplication(Method method) throws IOException {
        LoginPage loginPage = new LoginPage(driver, method);
        loginPage.login();
    }

}
