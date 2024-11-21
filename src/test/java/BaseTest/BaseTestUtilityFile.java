package BaseTest;

import Utility.PagesUtility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTestUtilityFile {
    public WebDriver driver;
    public String URL;

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
