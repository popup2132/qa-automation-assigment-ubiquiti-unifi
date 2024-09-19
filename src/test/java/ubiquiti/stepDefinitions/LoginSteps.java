package ubiquiti.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ubiquiti.ApiTests;
import ubiquiti.pages.LoginPage;
import ubiquiti.pages.SettingsPage;
import ubiquiti.utils.DriverFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    SettingsPage settingsPage;
    ApiTests apiTests;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        loginPage = new LoginPage(driver);
        settingsPage = new SettingsPage(driver);
        apiTests = new ApiTests();
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver();
    }

    @Given("User navigates to Unifi login page and create user with username {string} and password {string}")
    public void createLocalAdminUserUi(String username, String password) {
        driver.get("https://127.0.0.1:8443");
        loginPage.createLocalAdminUser(username, password);
    }

    @When("User navigates to Unifi login page and login with username {string} and password {string}")
    public void loginAsLocalAdminUser(String username, String password) {
        driver.get("https://127.0.0.1:8443");
        loginPage.LoginAsLocalAdminUser(username, password);
    }

    @Then("Validate admin activity name")
    public void validateAdminActivityName() {
        assertTrue(loginPage.getAdminActivityUserName().contains("admin_test_user"), "Expected user text not found!");
    }

    @And("Validate Country or Region in settings")
    public void validateCountryNameSettings() {
        settingsPage.navigateToSystemSettingsTab();
        assertTrue(settingsPage.getCountryName().contains("United States"), "Expected country/region text not found!");
    }
}
