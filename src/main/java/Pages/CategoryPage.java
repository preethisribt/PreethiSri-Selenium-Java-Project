package Pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
            case "Phone":
                pagesUtility.waitForElementVisibility(driver,phonesCategoryLink);
                driver.findElement(phonesCategoryLink).click();
                break;
            case "Laptop":
                pagesUtility.waitForElementVisibility(driver,laptopsCategoryLink);
                driver.findElement(laptopsCategoryLink).click();
                break;
            case "Monitor":
                pagesUtility.waitForElementVisibility(driver,monitorCategoryLink);
                driver.findElement(monitorCategoryLink).click();
                break;

            default:
                Assert.fail("Please provide proper category name");
                break;
        }
        ChainTestListener.log("category " + category + "selected");
    }
}
