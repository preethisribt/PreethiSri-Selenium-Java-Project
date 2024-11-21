package Pages;

import Utility.PagesUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

public class CategoryPage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility();
    }

    By phonesCategoryLink = By.xpath("//a[text()='Phones']");
    By laptopsCategoryLink = By.xpath("//a[text()='Laptops']");
    By monitorCategoryLink = By.xpath("//a[text()='Monitors']");

    public void selectCategory(String category)  {
        switch (category) {
            case "phone":
                driver.findElement(phonesCategoryLink).click();
                break;
            case "laptop":
                driver.findElement(laptopsCategoryLink).click();
                break;
            case "monitor":
                driver.findElement(monitorCategoryLink).click();
                break;

            default:
                Assert.fail("Please provide proper category name");
                break;
        }
    }
}
