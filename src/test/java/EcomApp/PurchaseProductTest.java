package EcomApp;

import BaseTest.BaseTestUtilityFile;
import Pages.CartPage;
import Pages.CategoryPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseProductTest extends BaseTestUtilityFile {

    @Test
    public void loginToApplication() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectCategory("monitor");

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct("Apple monitor 24");
        productPage.verifyProductAddedInCartPopup();

        CartPage cartPage = new CartPage(driver);
        cartPage.selectCart("Apple monitor 24");
        cartPage.placeOrder();
        cartPage.orderMessageValidation();
    }
}
