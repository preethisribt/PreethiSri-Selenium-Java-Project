package Pages.API_Mapping;

import Pages.PagesUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.azure.core.http.rest.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    WebDriver driver;
    PagesUtility pagesUtility;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.pagesUtility = new PagesUtility(driver);
    }

    public void validateAllLinksAreWorking() throws IOException {
        List<WebElement> linkElements = driver.findElements(By.xpath("//a"));

        List<String> links = linkElements.stream().map(link -> link.getAttribute("href"))
                .filter(link -> !link.equals("#") && !link.isEmpty())
                .peek(System.out::println)
                .collect(Collectors.toList());

        for (String link : links) {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            ChainTestListener.log(link + " " + httpURLConnection.getResponseCode());
            Assert.assertTrue(httpURLConnection.getResponseCode() == 200, link + " " + httpURLConnection.getResponseCode());
        }
    }
}
