package Utility;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PagesUtility {
//    public RequestSpecification getRequestSpecification() {
//
//        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/maps")
//                .addHeader("Content-Type", "application/json")
//                .addQueryParam("key", "qaclick123")
//                .build();
//
//        return requestSpecification;
//    }

    public ResponseSpecification getResponseSpecification(String statusLine) {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusLine(statusLine).build();
        return responseSpecification;
    }

    public String getScreenshot(String page, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "/ScreenShots/" + page + "_" + LocalDateTime.now() + ".png";
        String modifiedFilePath = filePath.replaceAll("[:-]", "_");
        String fullPath = System.getProperty("user.dir") + modifiedFilePath;

        System.out.println(fullPath);
        FileUtils.copyFile(src, new File(fullPath));
        return fullPath;
    }

    public Properties readPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(System.getProperty("user.dir") + "/PropertyFile/EcomProperty.property"));
        return properties;
    }

    public void waitForElementVisibility(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
