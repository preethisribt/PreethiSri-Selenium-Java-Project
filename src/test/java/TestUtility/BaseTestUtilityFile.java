package TestUtility;

import Pages.PagesUtility;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTestUtilityFile {
    public WebDriver driver;
    public String URL;

    MongoCollection<Document> mongoCollection;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException {
        DriverFactory driverFactory = new DriverFactory();
        PagesUtility pagesUtility = new PagesUtility();

        URL = pagesUtility.readPropertyFile().getProperty("ecomURL");
        driver = driverFactory.getDriver("chrome");

        driver.get(URL);
        driver.manage().window().maximize();
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
        document.append("password","");

        List<Document> documentList = new ArrayList<Document>();
        documentList.add(document);
        mongoCollection.insertMany(documentList);
    }
}
