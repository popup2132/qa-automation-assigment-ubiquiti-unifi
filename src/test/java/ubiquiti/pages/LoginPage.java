package ubiquiti.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8), Duration.ofMillis(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "tosAndEula")
    WebElement tosAndEulaLoginCheckBox;

    @FindBy(xpath = "//button[.//span[text()='Next']]")
    WebElement nextButton;

    @FindBy(xpath = "//button[.//span/span[text()='Advanced Setup']]")
    WebElement advancedSetupButton;

    @FindBy(xpath = "//button[.//span/span[text()='Skip']]")
    WebElement skipButton;

    @FindBy(id = "localAdminUsername")
    WebElement localAdminUsernameInput;

    @FindBy(id = "localAdminPassword")
    WebElement localAdminPasswordInput;

    @FindBy(id = "localAdminEmail")
    WebElement localAdminEmailInput;

    @FindBy(id = "localAdminPasswordConfirm")
    WebElement localAdminPasswordConfirmInput;

    @FindBy(xpath = "//button[.//span[text()='Finish']]")
    WebElement finishButton;

    @FindBy(name = "username")
    WebElement usernameInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(id = "loginButton")
    WebElement signInButton;

    @FindBy(xpath = "(//span[@data-uic-component='Text' and @data-uic-variant='body-primary' and text()='admin_test_user'])[1]")
    WebElement adminActivityUserName;

    public void createLocalAdminUser(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(tosAndEulaLoginCheckBox));
        tosAndEulaLoginCheckBox.click();
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOf(advancedSetupButton));
        advancedSetupButton.click();
        wait.until(ExpectedConditions.visibilityOf(skipButton));
        skipButton.click();
        wait.until(ExpectedConditions.visibilityOf(localAdminUsernameInput));
        localAdminUsernameInput.sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(localAdminPasswordInput));
        localAdminPasswordInput.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(localAdminPasswordConfirmInput));
        localAdminPasswordConfirmInput.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(localAdminEmailInput));
        localAdminEmailInput.sendKeys("test@mail.com");
        finishButton.click();
        wait.until(ExpectedConditions.visibilityOf(adminActivityUserName));
    }

    public void LoginAsLocalAdminUser(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
    }

    public String getAdminActivityUserName() {
        wait.until(ExpectedConditions.visibilityOf(adminActivityUserName));
        return adminActivityUserName.getText();
    }
}
