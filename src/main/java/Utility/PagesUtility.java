package Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

public class PagesUtility {
    public void getScreenshot(String page, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "/ScreenShots/" + page + "_" + LocalDateTime.now() + ".png";
        String modifiedFilePath = filePath.replaceAll("[:-]", "_");

        System.out.println(System.getProperty("user.dir") + modifiedFilePath);
        FileUtils.copyFile(src, new File(System.getProperty("user.dir") + modifiedFilePath));
    }

    public Properties readPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(System.getProperty("user.dir") + "/PropertyFile/EcomProperty.property"));
        return properties;
    }

    public void waitForElementVisibility(WebDriver driver, By element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForAlert(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
