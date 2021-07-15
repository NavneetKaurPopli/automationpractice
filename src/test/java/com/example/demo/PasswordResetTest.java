package com.example.demo;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PasswordResetTest {
        private WebDriver driver;
        private WebElement email;
        private WebElement submit;

        @BeforeMethod
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "D:\\My Documents\\Uvic OneDrive\\OneDrive - University of Victoria\\School\\SENG 275\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/index.php?controller=password");

            email = driver.findElement(By.id("email"));
            submit = driver.findElement(By.cssSelector(".button-medium:nth-child(1) > span"));
        }

        @AfterMethod
        public void cleanUp() {
            driver.quit();
        }

        //TC_PR_001
        @Test
        public void resetPasswordValid(){
            email.sendKeys("testing123@mailinator.com");
            submit.click();

            // Check email
            driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=testing123");
            assertTrue(driver.findElements(By.xpath("//td[contains(.,'Automation Practice')]")).size() > 0);
        }

        //TC_PR_002
        @Test
        public void resetPasswordInvalidEmail(){
            email.sendKeys("notreal@mailinator.com");
            submit.click();
            assertTrue(driver.findElements(By.xpath("//li[contains(.,'There is no account registered for this email address.')]")).size() > 0);
        }

        //TC_PR_003
        @Test
        public void verifyPasswordChanges(){
            email.sendKeys("testing123@mailinator.com");
            submit.click();

            // Check email
            driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=testing123");
            assertTrue(driver.findElements(By.xpath("//td[contains(.,'Automation Practice')]")).size() > 0);
            WebElement resetEmail = driver.findElement(By.xpath("//td[contains(.,'Automation Practice')]"));
            resetEmail.click();
        }


}
