package testcucumber.homework.seasonvar;

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

public class SearchSeriesSteps {

    private static final String URL = "http://seasonvar.ru/";

    private WebDriver driver;

    private final static By INPUT_STRING = By.name("q");
    private final static By SEARCH_BTN = By.xpath("//button[@type='submit']");

    private final static By RESULT_BLOCK = By.className("content-wrap");
    private final static By RESULT = By.xpath("//div[@class='pgs-search-info']/a[2]");

    @Given("^I am on the seasonvar main page$")
    public void i_am_on_the_seasonvar_main_page(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^I input \"(.*)\"$")
    public void i_input(String query) {

        driver.findElement(INPUT_STRING).sendKeys("Misfits");
    }

    @When("^Click search button$")
    public void click_search_btn(){

        driver.findElement(SEARCH_BTN).click();
    }

    @Then("^The page contains block with results \"(.*)\"$")
    public void the_page_contains_block_with_result(String query){
        WebElement resultBlock = driver.findElement(RESULT_BLOCK);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(resultBlock));
        Assert.assertEquals(query, driver.findElement(RESULT).getText());
    }

    @After
    public void quit(){
        driver.quit();
    }
}
