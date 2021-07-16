package com.example.demo;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReviewFunctionalityTest {

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

    //TC_RE_001
    @Test
    public void userReviewFromWriteAReviewButton(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product_comments_block_extra\"]/ul/li/a")));
        WebElement reviewButton = driver.findElement(By.xpath("//*[@id=\"product_comments_block_extra\"]/ul/li/a"));
        reviewButton.click();
        new WebDriverWait(driver,3);
        WebElement starButton = driver.findElement(By.xpath("//*[@id=\"criterions_list\"]/li/div[1]/div[6]"));
        starButton.click();
        WebElement reviewTitle = driver.findElement(By.xpath("//*[@id=\"comment_title\"]"));
        reviewTitle.sendKeys("good");
        WebElement reviewBox = driver.findElement(By.xpath("//*[@id=\"content\"]"));
        reviewBox.sendKeys("good");
        WebElement submitReview = driver.findElement(By.xpath("//*[@id=\"submitNewMessage\"]/span"));
        submitReview.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/h2")));
        WebElement finalTextTitle = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/h2"));
        WebElement finalTextBody = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/p[1]"));

        assertEquals("New comment", finalTextTitle.getText());
        assertEquals("Your comment has been added and will be available once approved by a moderator", finalTextBody.getText());
    }

    //TC_RE_002
    @Test
    public void userReviewFromBeTheFirstButton(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"new_comment_tab_btn\"]/span")));
        WebElement reviewButton = driver.findElement(By.xpath("//*[@id=\"new_comment_tab_btn\"]/span"));
        reviewButton.click();
        new WebDriverWait(driver,3);
        WebElement starButton = driver.findElement(By.xpath("//*[@id=\"criterions_list\"]/li/div[1]/div[6]"));
        starButton.click();
        WebElement reviewTitle = driver.findElement(By.xpath("//*[@id=\"comment_title\"]"));
        reviewTitle.sendKeys("good");
        WebElement reviewBox = driver.findElement(By.xpath("//*[@id=\"content\"]"));
        reviewBox.sendKeys("good");
        WebElement submitReview = driver.findElement(By.xpath("//*[@id=\"submitNewMessage\"]/span"));
        submitReview.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/h2")));
        WebElement finalTextTitle = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/h2"));
        WebElement finalTextBody = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/p[1]"));

        assertEquals("New comment", finalTextTitle.getText());
        assertEquals("Your comment has been added and will be available once approved by a moderator", finalTextBody.getText());
    }

    //TC_RE_003
    @Test
    public void unableToLeaveReview(){
        driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_logo\"]/a/img")));
        assertTrue(driver.findElements(By.xpath("//*[@id=\"new_comment_tab_btn\\\"]/span")).isEmpty());
        assertTrue(driver.findElements(By.xpath("//*[@id=\"product_comments_block_extra\\\"]/ul/li/a")).isEmpty());
    }

    private void signIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        email = driver.findElement(By.id("email"));
        email.sendKeys("seng275test@outlook.com");

        password = driver.findElement(By.id("passwd"));
        password.sendKeys("Seng275\n");
    }
}
