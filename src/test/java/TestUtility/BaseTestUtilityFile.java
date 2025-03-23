package TestUtility;

import Pages.PagesUtility;
import com.mongodb.client.*;
import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseTestUtilityFile {
    public WebDriver driver;
    public String URL;
    public boolean isGrid;
    public String browserName;

    MongoCollection<Document> mongoCollection;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException {
        DriverFactory driverFactory = new DriverFactory();
        PagesUtility pagesUtility = new PagesUtility(driver);

        URL = pagesUtility.readPropertyFile().getProperty("ecomURL");
        browserName = pagesUtility.readPropertyFile().getProperty("browser");
        isGrid = Boolean.valueOf(pagesUtility.readPropertyFile().getProperty("isGrid"));

        if (isGrid) {
            if (browserName.equals("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
            } else if (browserName.equals("firefox")) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
            } else if (browserName.equals("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), edgeOptions);
            }

        } else {
            driver = driverFactory.getDriver(browserName);
        }

        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void getNetworkLogs(ChromeDriver driver) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent ->
        {
            System.out.println("Request URL:\n" + requestWillBeSent.getRequest().getUrl());
            System.out.println("Request Method:\n" + requestWillBeSent.getRequest().getMethod());
        });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            System.out.println("Response URL:\n" + responseReceived.getResponse().getUrl());
            System.out.println("Response Status:\n" + responseReceived.getResponse().getStatus());

            Network.GetResponseBodyResponse responseBody = devTools.send(Network.getResponseBody(responseReceived.getRequestId()));
            System.out.println("Response Body:\n" + responseBody.getBody());
        });
        Double temp = Math.pow(Double.valueOf(1),Double.valueOf(1));
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }

    public void connectMongoDB() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("autodb");
        mongoCollection = mongoDatabase.getCollection("userDB");
    }

    public void loadDatainToDB() {
        Document document = new Document();
        document.append("userName", "");
        document.append("password", "");

        List<Document> documentList = new ArrayList<Document>();
        documentList.add(document);
        mongoCollection.insertMany(documentList);
    }
}
