package WebAppTest;

import Pages.CartPage;
import Pages.LoginPage;
import TestUtility.BaseTestUtilityFile;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.prefs.BackingStoreException;

public class CartPageTest extends BaseTestUtilityFile {

    @Test(groups = {"Web"})
    public void removeAllProductsFromCart() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToCart();
        cartPage.deleteProductFromCart();
    }
}
