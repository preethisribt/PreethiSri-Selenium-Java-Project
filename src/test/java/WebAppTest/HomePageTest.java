package WebAppTest;

import Pages.API_Mapping.HomePage;
import TestUtility.BaseTestUtilityFile;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

public class HomePageTest extends BaseTestUtilityFile {

    @Test(groups = {"Web"})
    public void checkLinksInHomePageWorking() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.validateAllLinksAreWorking();
    }
}
