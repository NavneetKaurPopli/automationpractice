package com.example.demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SearchFilter {
    private WebDriver driver;
    private WebElement search;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\My Documents\\Uvic OneDrive\\OneDrive - University of Victoria\\School\\SENG 275\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

        search = driver.findElement(By.id("search_query_top"));
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    //TC_SF_001
    @Test
    public void validSearch(){
        search.sendKeys("blouse");
        search.sendKeys(Keys.RETURN);
        assertTrue(driver.findElements(By.cssSelector(".ajax_block_product")).size() > 0);

        search = driver.findElement(By.id("search_query_top"));
        search.clear();
        search.sendKeys("top");
        search.sendKeys(Keys.RETURN);
        assertTrue(driver.findElements(By.cssSelector(".ajax_block_product")).size() > 0);

        search = driver.findElement(By.id("search_query_top"));
        search.clear();
        search.sendKeys("shirt");
        search.sendKeys(Keys.RETURN);
        assertTrue(driver.findElements(By.cssSelector(".ajax_block_product")).size() > 0);
    }

    //TC_SF_002
    @Test
    public void invalidSearch(){
        search.sendKeys("asdf");
        search.sendKeys(Keys.RETURN);
        assertTrue(driver.findElements(By.cssSelector(".ajax_block_product")).size() == 0);
    }

    //TC_SF_003
    @Test
    public void sortTest(){
        for(int i=3;i<11;i++){
            driver.get("http://automationpractice.com/index.php?id_category="+i+"&controller=category");
            Select sort = new Select(driver.findElement(By.id("selectProductSort")));

            // Assume test will fail if the site can't load in 5 seconds (will usually hang forever)
            sort.selectByValue("price:asc");
            Boolean loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("price:desc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("name:asc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("name:desc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("quantity:desc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("reference:asc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
            sort.selectByValue("reference:desc");
            loaded = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//img[contains(@src,'http://automationpractice.com/img/loader.gif')])[2]")));
        }
    }


}
