package Pages;

import Utility.PagesUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

public class ProductPage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility();
    }

    By selectProductLink = By.xpath("//a[text()='Apple monitor 24']");
    By addToCartButton = By.xpath("//a[text()='Add to cart']");

    public void selectProduct(String product) throws IOException {
        pagesUtility.getScreenshot("Product Gridwall Page", driver);
        pagesUtility.waitForElementVisibility(driver, selectProductLink);
        driver.findElement(selectProductLink).click();
        ChainTestListener.log("product " + product + "is selected");

        pagesUtility.waitForElementVisibility(driver, addToCartButton);
        pagesUtility.getScreenshot("ProductPage", driver);
        driver.findElement(addToCartButton).click();
        ChainTestListener.log("product " + product + "added to cart");
    }

    public void verifyProductAddedInCartPopup() {
        pagesUtility.waitForAlert(driver);
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();

        Assert.assertEquals(actualMessage,"Product added.");
        alert.accept();
    }
}
