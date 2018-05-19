package testcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static utils.WebdriverManager.getDriver;

public class GoogleSteps {

    private WebDriver driver;

    @Given("^I am on the Google Search page$")
    public void i_am_on_the_google_search_page(){
    //    WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "lib/chrome/chromedriver.exe");
        driver = getDriver();
        driver.get("https://google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^I search for \"(.*)\"$")
    public void i_search_for(String query){
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(query);
        element.sendKeys(Keys.ENTER);
    }

    @Then("^The page title contains \"(.*)\"$")
    public void the_page_title_contains(String title){
        WebElement element = driver.findElement(By.id("resultStats"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(driver.getTitle().contains(title));
    }

    @After
    public void quit(){
        driver.quit();
    }
}
