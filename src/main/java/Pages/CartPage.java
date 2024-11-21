package Pages;

import Utility.PagesUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import javax.print.attribute.standard.OrientationRequested;
import java.io.IOException;

public class CartPage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility();
    }

    By cartLink = By.xpath("//a[text()='Cart']");
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

    public void selectCart(String product) throws IOException {
        driver.findElement(cartLink).click();
        pagesUtility.waitForElementVisibility(driver, cartData);
        pagesUtility.getScreenshot("CartPage", driver);

        boolean elementInCart = driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[text()='Apple monitor 24']")).isDisplayed();
        Assert.assertTrue(elementInCart);
    }

    public void placeOrder() throws IOException {
        driver.findElement(placeOrderButton).click();
        pagesUtility.waitForElementVisibility(driver, nameText);

        driver.findElement(nameText).sendKeys("abstest");
        driver.findElement(countryText).sendKeys("Australia");
        driver.findElement(cityyText).sendKeys("Melbourne");
        driver.findElement(cardText).sendKeys("1234 5678 9101");
        driver.findElement(monthText).sendKeys("09");
        driver.findElement(yearText).sendKeys("2030");

        pagesUtility.getScreenshot("CheckoutPage", driver);
        driver.findElement(purchaseButton).click();
    }

    public void orderMessageValidation() throws IOException {
        pagesUtility.waitForElementVisibility(driver, okButton);
        pagesUtility.getScreenshot("OrderPage", driver);

        boolean expectedThankyouMessage = driver.findElement(orderThankyouMessage).isDisplayed();
        Assert.assertTrue(expectedThankyouMessage);

       String orderMessage  = driver.findElement(orderMessageText).getText();
       System.out.println(orderMessage);

       driver.findElement(okButton).click();
    }
}
