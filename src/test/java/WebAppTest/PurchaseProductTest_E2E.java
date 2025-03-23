package WebAppTest;

import TestUtility.BaseTestUtilityFile;
import Data.EcomDataProvider;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class PurchaseProductTest_E2E extends BaseTestUtilityFile {

    @Test(dataProvider = "data", dataProviderClass = EcomDataProvider.class, groups = {"Web","e2e"})
    public void validateCustomerAbleToPurchaseProduct(Map<String, String> map) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProductFromSpecificCategory(map.get("Category"), map.get("Products"));

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToCart();
        cartPage.validateProductPresentInCart();

        cartPage.placeOrder();
        cartPage.orderMessageValidation();
    }
}
