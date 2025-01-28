package BasicPractise;

import BaseTest.BaseTestUtilityFile;
import Utility.PagesUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.File.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

public class Practise extends BaseTestUtilityFile {
    String actualURL = "https://omayo.blogspot.com/";
    PagesUtility pagesUtility;
    @Test
    public void checkCurrentURLAndTitle() throws IOException {
        pagesUtility = new PagesUtility();

        String expectedTitle = "omayo (QAFox.com)";
        driver.get(actualURL);
        String actualURL = driver.getCurrentUrl();
        String actualTitle = driver.getTitle();
//        ExtentReportListener.extentTest.log(Status.INFO, "actualURL " +actualURL);
//        ExtentReportListener.extentTest.log(Status.INFO, "actualTitle " +actualTitle);
//
//        String sc = pagesUtility.getScreenshot("LoginPage", driver);
//        ExtentReportListener.extentTest.pass("LoginPage", MediaEntityBuilder.createScreenCaptureFromPath(sc).build());

        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualURL, "https://omayo.blogspot.com/");
        driver.quit();
    }

    @Test
    public void checkLinksAreUp() throws IOException {
        driver.get(actualURL);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        HttpURLConnection httpURLConnection = null;

        for (WebElement link : links) {
            String href = link.getAttribute("href");

            if (href != null && !href.toLowerCase().contains("javascript") && !href.startsWith("#")) {
                URL url = new URL(href);
                httpURLConnection = (HttpURLConnection) (url).openConnection();
                httpURLConnection.connect();

                int responseCode = httpURLConnection.getResponseCode();
                String responseStatus = httpURLConnection.getResponseMessage();

                System.out.println("URL : " + href);
                System.out.println("Response Code : " + responseCode);
                System.out.println("Response Status : " + responseStatus);

                if (String.valueOf(responseCode).startsWith("4") || String.valueOf(responseCode).startsWith("5")) {
                    System.out.println("Failed URL " + link);
                    Assert.fail();
                }
            }

        }
        httpURLConnection.disconnect();
    }
}
