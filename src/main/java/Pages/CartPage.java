package Pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class CartPage {
    WebDriver driver;
    PagesUtility pagesUtility;
    ProductPage productPage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility(driver);
        this.productPage = new ProductPage(driver);
    }

    By cartLink = By.xpath("//a[text()='Cart']");
    By cartTableRow = By.cssSelector("#tbodyid tr");
    By cartData = By.xpath("//tr[@class='success']");
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
    By purchaseButton = By.xpath("//button[text()='Purchase']");
    By okButton = By.xpath("//button[text()='OK']");
    By nameText = By.id("name");
    By countryText = By.id("country");
    By cityyText = By.id("city");
    By cardText = By.id("card");
    By monthText = By.id("month");
    By yearText = By.id("year");
    By orderThankyouMessage = By.xpath("//*[text()='Thank you for your purchase!']");
    By orderMessageText = By.xpath("//p[contains(@class,'lead')]//br");
    By deleteLink = By.xpath("//a[text()='Delete']");
    By cartTable = By.xpath("//tbody[@id='tbodyid']");

    public void navigateToCart() {
        driver.findElement(cartLink).click();
        pagesUtility.waitForElementVisibility(cartTable);
    }

    public void deleteProductFromCart() throws IOException, InterruptedException {
        Thread.sleep(2000);  //no other wait condition can be added, since we are not sure cart will have element or not
        List<WebElement> deleteProduct = driver.findElements(deleteLink);
        pagesUtility.getScreenshot("CartPage");

        if (deleteProduct.size() > 0) {
            for (WebElement element : deleteProduct) {
                pagesUtility.waitForElementVisibility(deleteLink);
                element.click();
            }
            ChainTestListener.log("Deleted Products from cart - cart now empty");
        } else
            ChainTestListener.log("cart is already empty");

        pagesUtility.getScreenshot("ClearCartPage");
    }

    public void checkCartIsEmpty() throws IOException {
        int cartActualRows = driver.findElements(cartTableRow).size();
        Assert.assertEquals(cartActualRows, 0);
        pagesUtility.getScreenshot("CartPageValidation");
    }

    public void validateProductPresentInCart() throws IOException {
        pagesUtility.waitForElementVisibility(cartData);
        pagesUtility.getScreenshot("CartPage");

        for (String product : productPage.products) {
            boolean elementInCart = driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[text()='" + product.trim() + "']")).isDisplayed();
            Assert.assertTrue(elementInCart);
        }
    }

    public void placeOrder() throws IOException {
        driver.findElement(placeOrderButton).click();
        pagesUtility.waitForElementVisibility(nameText);

        driver.findElement(nameText).sendKeys("abstest");
        driver.findElement(countryText).sendKeys("Australia");
        driver.findElement(cityyText).sendKeys("Melbourne");
        driver.findElement(cardText).sendKeys("1234 5678 9101");
        driver.findElement(monthText).sendKeys("09");
        driver.findElement(yearText).sendKeys("2030");

        pagesUtility.getScreenshot("CheckoutPage");
        driver.findElement(purchaseButton).click();
    }

    public void orderMessageValidation() throws IOException {
        pagesUtility.waitForElementVisibility(okButton);
        pagesUtility.getScreenshot("OrderPage");

        boolean expectedThankyouMessage = driver.findElement(orderThankyouMessage).isDisplayed();
        Assert.assertTrue(expectedThankyouMessage);

        String orderMessage = driver.findElement(orderMessageText).getText();
        System.out.println(orderMessage);

        driver.findElement(okButton).click();
    }
}
