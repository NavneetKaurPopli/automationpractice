package com.example.demo;

import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class CreateAccount {
    private WebDriver driver;
    private WebElement email;
    private WebElement create;
    private Random rand = new Random();

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\My Documents\\Uvic OneDrive\\OneDrive - University of Victoria\\School\\SENG 275\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        email = driver.findElement(By.id("email_create"));
        create = driver.findElement(By.id("SubmitCreate"));
    }

    //TC_CA_001
    @Test
    public void createAccountValid() {
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
        postcode.sendKeys("12345");
        phone.sendKeys("1234567890");
        alias.sendKeys("Home");
        state.selectByIndex(1);
        days.selectByIndex(0);
        months.selectByIndex(0);
        years.selectByIndex(0);

        submit.click();
        assertTrue(driver.findElements(By.partialLinkText("Sign out")).size() > 0);
    }

    //TC_CA_002
    @Test
    public void invalidPassword(){
        int randInt = rand.nextInt(10000);
        email.sendKeys("testing"+randInt+"@mailinator.com");
        create.click();
    }

    //TC_CA_003
    @Test
    public void wrongPostalCode(){
        int randInt = rand.nextInt(10000);
        email.sendKeys("testing"+randInt+"@mailinator.com");
        create.click();

    }

    //TC_CA_004
    @Test
    public void emailExists(){


    }


}
