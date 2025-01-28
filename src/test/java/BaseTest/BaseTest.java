package BaseTest;

import Utility.PagesUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseTest {
    public WebDriver driver;
    public String URL;
    public String browser;
    public static MongoCollection<Document> mongoCollection;

    @BeforeMethod
    @Parameters({"headless"})
    public void initializeDriver(boolean headless) throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless)
            chromeOptions.addArguments("--headless=new");

        DriverFactory driverFactory = new DriverFactory();
        PagesUtility pagesUtility = new PagesUtility();

        URL = pagesUtility.readPropertyFile().getProperty("paraBankURL");
        browser = pagesUtility.readPropertyFile().getProperty("browser");
        driver = new ChromeDriver(chromeOptions);

        ChainPluginService.getInstance().addSystemInfo("Project Owner ", "Preethi Sri");

        driver.get(URL);
        ChainTestListener.log("Navigated to the URL " + URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver(ITestResult iTestResult) {
        if (!iTestResult.isSuccess())
            ChainTestListener.embed(PagesUtility.src, "image/png");
        driver.quit();
    }

    @BeforeSuite
    public void connectMongoDB() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("autodb");
        mongoCollection = mongoDatabase.getCollection("userDB");
    }
}
