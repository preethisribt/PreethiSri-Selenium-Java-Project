package EcomApp;

import BaseTest.BaseTestUtilityFile;
import BaseTest.DriverFactory;
import Data.EcomDataProvider;
import Pages.CartPage;
import Pages.CategoryPage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utility.PagesUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void initializeDriver() throws IOException {
        DriverFactory driverFactory = new DriverFactory();
        PagesUtility pagesUtility = new PagesUtility();

        URL = pagesUtility.readPropertyFile().getProperty("ecomURL");
        driver = driverFactory.getDriver("chrome");

        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
