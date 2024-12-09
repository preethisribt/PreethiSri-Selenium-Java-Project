package EcomApp;

import BaseTest.BaseTestUtilityFile;
import Data.EcomDataProvider;
import Pages.CartPage;
import Pages.CategoryPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseProductTest extends BaseTestUtilityFile {

    @Test(dataProvider = "EcomData" , dataProviderClass = EcomDataProvider.class)
    public void loginToApplication(String category, String products) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectCategory(category);

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(products);
        productPage.verifyProductAddedInCartPopup();

        CartPage cartPage = new CartPage(driver);
        cartPage.selectCart(products);
        cartPage.placeOrder();
        cartPage.orderMessageValidation();
    }
}
