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
        this.pagesUtility = new PagesUtility(driver);
    }

    By phonesCategoryLink = By.xpath("//a[text()='Phones']");
    By laptopsCategoryLink = By.xpath("//a[text()='Laptops']");
    By monitorCategoryLink = By.xpath("//a[text()='Monitors']");

    public void selectCategory(String category)  {
        switch (category) {
            case "Phone":
                pagesUtility.waitForElementVisibility(phonesCategoryLink);
                driver.findElement(phonesCategoryLink).click();
                break;
            case "Laptop":
                pagesUtility.waitForElementVisibility(laptopsCategoryLink);
                driver.findElement(laptopsCategoryLink).click();
                break;
            case "Monitor":
                pagesUtility.waitForElementVisibility(monitorCategoryLink);
                driver.findElement(monitorCategoryLink).click();
                break;

            default:
                Assert.fail("Please provide proper category name");
                break;
        }
        ChainTestListener.log("category " + category + "selected");
    }
}
