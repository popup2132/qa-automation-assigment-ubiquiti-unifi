package ubiquiti.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8), Duration.ofMillis(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@data-testid='navigation-settings']")
    WebElement navigationSettings;

    @FindBy(xpath = "//li[@data-testid='system']")
    WebElement systemSettings;

    @FindBy(xpath = "//input[@id='country.code']")
    WebElement countryNameInput;

    public void navigateToSystemSettingsTab() {
        wait.until(ExpectedConditions.visibilityOf(navigationSettings));
        navigationSettings.click();
        wait.until(ExpectedConditions.visibilityOf(systemSettings));
        systemSettings.click();
    }

    public String getCountryName() {
        wait.until(ExpectedConditions.visibilityOf(countryNameInput));
        return countryNameInput.getAttribute("value");
    }
}
