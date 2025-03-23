package Pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.azure.core.http.rest.Page;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PagesUtility {
    public static File src;
    WebDriver driver;

    public PagesUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void getScreenshot(String page) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        src = screenshot.getScreenshotAs(OutputType.FILE);

        String filePath = "/Screenhots/" + page + "_" + LocalDateTime.now() + ".png";
        String modifiedFilePath = filePath.replaceAll("[:-]", "_");
        String fullPath = System.getProperty("user.dir") + modifiedFilePath;

        FileUtils.copyFile(src, new File(fullPath));
        // uncomment below line to attach screenshot in the index.html for all TC
        ChainTestListener.embed(src, "image/png");
    }

    public WebElement element(By element) {
        return driver.findElement(element);
    }

    public List<WebElement> elements(By element) {
        return driver.findElements(element);
    }

    public Properties readPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(System.getProperty("user.dir") + "/PropertyFile/EcomProperty.property"));
        return properties;
    }

    public void waitForElementVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
