package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class RegressionTests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\owenj\\NotOneDrive\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\owenj\\NotOneDrive\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    //TC_CA_003
    @Test
    public void wrongPostalCode(){
        Random rand = new Random();
        WebElement email = driver.findElement(By.id("email_create"));
        WebElement create = driver.findElement(By.id("SubmitCreate"));
        int randInt = rand.nextInt(10000);
        email.sendKeys("testing"+randInt+"@mailinator.com");
        create.click();

        WebElement firstResult = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("customer_firstname")));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement passwd = driver.findElement(By.id("passwd"));
        Select days = new Select(driver.findElement(By.id("days")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select years = new Select(driver.findElement(By.id("years")));
        WebElement address1 = driver.findElement(By.id("address1"));
        WebElement city = driver.findElement(By.id("city"));
        Select state = new Select(driver.findElement(By.id("id_state")));
        WebElement postcode = driver.findElement(By.id("postcode"));
        WebElement phone = driver.findElement(By.id("phone_mobile"));
        WebElement alias = driver.findElement(By.id("alias"));
        WebElement submit = driver.findElement(By.cssSelector("#submitAccount > span"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        passwd.sendKeys("supersecret");
        address1.sendKeys("123 street st.");
        city.sendKeys("Anchorage");
        postcode.sendKeys("V5F4G7");
        phone.sendKeys("1234567890");
        alias.sendKeys("Home");
        state.selectByIndex(1);
        days.selectByIndex(0);
        months.selectByIndex(0);
        years.selectByIndex(0);

        submit.click();
        assertTrue(driver.findElements(By.xpath("//ol/li")).size() > 0);
    }
}
