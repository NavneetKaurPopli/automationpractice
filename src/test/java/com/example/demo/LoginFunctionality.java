package com.example.demo;

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

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ama/Desktop/SENG 275/web_testing/chromedriver");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        //driver.get("http://automationpractice.com/index.php?controller=password");
        browser.manage().window().maximize();
    }

    @AfterMethod
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
        WebElement error = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/p"));
        assertEquals("There is 1 error", error.getText());
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
        WebElement error = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/p"));
        assertEquals("There is 1 error", error.getText());
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_004
    @Test
    public void InvalidEmailValidPassword(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testingmail.com");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        WebElement error = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/p"));
        assertEquals("There is 1 error", error.getText());
        assertEquals("Login - My Store", browser.getTitle());
    }

    //TC_LF_005
    @Test
    public void NoCredentials(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("\n");
        WebElement error = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/p"));
        assertEquals("There is 1 error", error.getText());
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

    //TC_LF_009
    @Test
    public void signOutCheck(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("My account - My Store", browser.getTitle());

        browser.navigate().refresh();
        assertEquals("My account - My Store", browser.getTitle());
    }

    //TC_LF_010
    //TC_LF_011
    // Both tests can be passed by checking the input type on the password field is 'password'
    @Test
    public void passwordHidden(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        assertTrue(browser.findElements(By.xpath("//input[@type='password']")).size() > 0);
    }

    //TC_LF_012
    @Test
    public void resetLinkExists(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        browser.findElement(By.partialLinkText("Forgot your password")).click();
        assertTrue(browser.findElements(By.xpath("//form[@id='form_forgotpassword']/fieldset/p/button/span")).size() > 0);
    }

    @Test
    public void signOutWorks(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("My account - My Store", browser.getTitle());

        browser.findElement(By.partialLinkText("Sign out")).click();
        assertTrue(browser.findElements(By.xpath("//form[@id='login_form']/h3")).size() > 0);

    }

    @Test
    public void signOutGoBack(){
        browser.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        assertEquals("My account - My Store", browser.getTitle());

        browser.findElement(By.partialLinkText("Sign out")).click();
        browser.navigate().back();
        assertTrue(browser.findElements(By.xpath("//form[@id='login_form']/h3")).size() > 0);

    }
}
