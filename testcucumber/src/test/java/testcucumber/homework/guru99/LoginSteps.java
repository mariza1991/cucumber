package testcucumber.homework.guru99;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSteps {

    private WebDriver driver;

    private static final String URL = "http://demo.guru99.com/insurance/v1/index.php";

    private final static By EMAIL_INPUT = By.id("email");
    private final static By PASSWORD_INPUT = By.id("password");
    private final static By LOGIN_BUTTON = By.name("submit");
    private final static By SIGNED_IN_TEXT = By.className("content");
    private final static By SIGNED_IN_EMAIL = By.xpath("//h4");

    @Given("^I am on the login page$")
    public void i_am_on_the_login_page(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^I fill email field as \"(.*)\"$")
    public void i_fill_email_field(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @When("^I fill password field as \"(.*)\"$")
    public void i_fill_password_field(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @When("^I click login btn$")
    public void i_click_login_btn(){
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Then("^The page contains \"Signed in as\" \"(.*)\"$")
    public void the_page_contains(String email) {
        WebElement signInText = driver.findElement(SIGNED_IN_TEXT);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signInText));
        Assert.assertEquals(email, driver.findElement(SIGNED_IN_EMAIL).getText());
    }

    @After
    public void quit(){
        driver.quit();
    }
}
