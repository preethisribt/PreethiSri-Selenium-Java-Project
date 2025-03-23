package Pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class ProductPage {
    WebDriver driver;
    PagesUtility pagesUtility;
    CategoryPage categoryPage;
    static String[] products;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility(driver);
        this.categoryPage = new CategoryPage(driver);
    }

    By addToCartButton = By.xpath("//a[text()='Add to cart']");
    By homeLink = By.xpath("//a[text()='Home ']");

    public void selectProductFromSpecificCategory(String category, String allProducts) throws IOException {
        pagesUtility.getScreenshot("Product Gridwall Page");

       products = allProducts.split(",");

        for (String product : products) {
            categoryPage.selectCategory(category);

            By element = By.xpath("//a[text()='" + product.trim() + "']");
            pagesUtility.waitForElementVisibility(element);
            driver.findElement(element).click();
            ChainTestListener.log("product " + product + "is selected");

            pagesUtility.waitForElementVisibility(addToCartButton);
            pagesUtility.getScreenshot("ProductPage");
            driver.findElement(addToCartButton).click();
            ChainTestListener.log("product " + product + "added to cart");

            verifyProductAddedInCartPopup();
            navigateToHome();
        }
    }

    public void verifyProductAddedInCartPopup() {
        pagesUtility.waitForAlert();
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();

        Assert.assertEquals(actualMessage, "Product added.");
        alert.accept();
    }

    public void navigateToHome() {
        driver.findElement(homeLink).click();
        ChainTestListener.log("Navigated To Home Page");
    }
}
