package BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    Map<String, Supplier<WebDriver>> driverMap;

    public DriverFactory() {
        driverMap = new HashMap<>();
        driverMap.put("chrome", this::chromeDriver);
        driverMap.put("firefox", this::firefoxDriver);
        driverMap.put("edge", this::edgeDriver);
    }

    public WebDriver getDriver(String browserName) {
    return driverMap.getOrDefault(browserName.toLowerCase().trim(),()->{
                throw new Error("Browser Driver not found, provide valid browser");
            }).get();
    }

    public WebDriver chromeDriver() {
        return new ChromeDriver();
    }

    public WebDriver firefoxDriver() {
        return new FirefoxDriver();
    }

    public WebDriver edgeDriver() {
        return new EdgeDriver();
    }
}
