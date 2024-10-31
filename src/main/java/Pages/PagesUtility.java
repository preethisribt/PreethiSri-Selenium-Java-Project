package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Properties;

public class PagesUtility {
    public void getScreenshot(Method method, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "\\src\\main\\ScreenShots" + method.getName() + "_" + LocalDateTime.now() + ".png";
        String modifiedFilePath = filePath.replaceAll("[:-]", "_");

        System.out.println(System.getProperty("user.dir") + modifiedFilePath);
        FileUtils.copyFile(src, new File(System.getProperty("user.dir") + modifiedFilePath));
    }

    public Properties readPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(System.getProperty("user.dir") + "/PropertyFile/EcomProperty.property"));
        return properties;
    }
}
