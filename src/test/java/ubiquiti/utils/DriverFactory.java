package ubiquiti.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverFactory {
    private static WebDriver driver;



    public static WebDriver getDriver() {
        ChromeOptions capability = new ChromeOptions();
        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(capability);
        }
        return driver;
    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}