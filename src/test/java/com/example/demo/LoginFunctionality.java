package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class LoginFunctionality {
    WebDriver browser;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ama/Desktop/SENG 275/web_testing/chromedriver");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        //driver.get("http://automationpractice.com/index.php?controller=password");
        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }


    //TC_LF_001
    @Test
    public void ValidCredentials(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("My account - My Store", browser.getTitle());
    }

    //TC_LF_002
    @Test
    public void InvalidCredentials(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275gmail.com");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("sengrtm\n");
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_003
    @Test
    public void ValidEmailInvalidPassword(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("Seng275\n");
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_004
    @Test
    public void InalidEmailValidPassword(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testingmail.com");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_005
    @Test
    public void NoCredentials(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("\n");
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_006
    //subject to change
    @Test
    public void MultipleUnsuccessfulAttempts(){
        for(int i = 0; i < 10; i++){
            browser.get("http://automationpractice.com/index.php?controller=authentication");
            WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
            email.sendKeys("seng275gmail.com");
            WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
            password.sendKeys("sengrtm\n");
        }
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_007
    @Test
    public void BrowsingBack(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("My account - My Store", browser.getTitle());

        browser.navigate().back();
        assertEquals("My account - My Store", browser.getTitle());
    }

    //TC_LF_008 removed test

}
