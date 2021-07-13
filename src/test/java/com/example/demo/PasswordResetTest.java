package com.example.demo;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class PasswordResetTest {
        private WebDriver driver;

        @BeforeMethod
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "D:\\My Documents\\Uvic OneDrive\\OneDrive - University of Victoria\\School\\SENG 275\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/index.php?controller=password");
        }

        //TC_PR_001
        @Test
        public void resetPasswordValid(){

        }

        //TC_PR_002
        @Test
        public void resetPasswordInvalidEmail(){

        }

        //TC_PR_003
        @Test
        public void verifyPasswordChanges(){

        }


}
