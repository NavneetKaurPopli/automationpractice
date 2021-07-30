package com.example.demo;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecurityTest {

    private WebDriver driver;
    private WebElement email;
    private WebElement password;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/justin/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void BruteForceLogin(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for(int i = 0; i < 15; i++){
            driver.get("http://automationpractice.com/index.php?controller=authentication");
            WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
            email.sendKeys("seng275gmail.com");
            WebElement password = driver.findElement(By.xpath("//*[@id=\"passwd\"]"));
            password.sendKeys("sengrtm\n");
        }
        assertEquals("Login - My Store", driver.getTitle());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
    }

    @Test
    public void CacheTest(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement addToCart = driver.findElement(By.id("add_to_cart"));
        addToCart.click();
        WebElement logoutButton = driver.findElement(By.className("logout"));
        logoutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
        driver.navigate().back();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header_user_info")));
        WebElement userButton = driver.findElement(By.className("header_user_info"));
        assertEquals(userButton.getText(), "John Doe");
    }

    @Test
    public void SimultaneousLogins(){
        signIn();
        WebDriver driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get("http://automationpractice.com/index.php?controller=authentication");

        email = driver2.findElement(By.id("email"));
        email.sendKeys("seng275test@outlook.com");

        password = driver2.findElement(By.id("passwd"));
        password.sendKeys("Seng275\n");

        WebElement userButton = driver.findElement(By.className("header_user_info"));
        assertEquals(userButton.getText(), "John Doe");
        driver2.quit();
    }

    private void signIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        email = driver.findElement(By.id("email"));
        email.sendKeys("seng275test@outlook.com");

        password = driver.findElement(By.id("passwd"));
        password.sendKeys("Seng275\n");
    }
}
