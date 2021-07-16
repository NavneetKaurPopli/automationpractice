package com.example.demo;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class CompareTests {

    WebDriver browser;
    Actions actions;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","D:\\Users\\Cord\\Class things\\SENG 275\\chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        actions = new Actions(browser);
    }

    @AfterMethod
    public void cleanUp(){
        browser.quit();
    }

    @Test
    public void AddToCompare() {
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");
    }

    @Test
    public void RemoveFromCompare(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare");
    }

    @Test
    public void RemoveFromComparePage(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");
        WebElement ComparePageButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/div[3]/div[2]/form/button/span"));
        ComparePageButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Products Comparison - My Store"));
        WebElement TrashCan = browser.findElement(By.xpath("//*[@id=\"product_comparison\"]/tbody/tr[1]/td[2]/div[1]/a/i"));
        TrashCan.click();

    }

    @Test
    public void RemoveFromCompareReload(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        actions.moveToElement(ItemFrame).perform();
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare");
    }

    @Test
    public void addFourthToCompare(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");

        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame2 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div"));
        actions.moveToElement(ItemFrame2).perform();
        WebElement AddToCompareButton2 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[3]/div[2]/a"));
        AddToCompareButton2.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton2.getAttribute("class"),"add_to_compare checked");

        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame3 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div"));
        actions.moveToElement(ItemFrame3).perform();
        WebElement AddToCompareButton3 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[3]/div[2]/a"));
        AddToCompareButton3.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton3.getAttribute("class"),"add_to_compare checked");
    }

    @Test
    public void ComparePageTwoItems(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");

        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame2 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div"));
        actions.moveToElement(ItemFrame2).perform();
        WebElement AddToCompareButton2 = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[3]/div[2]/a"));
        AddToCompareButton2.click();
        try {
            Thread.sleep(2000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton2.getAttribute("class"),"add_to_compare checked");
    }

    @Test
    public void ComparePageDetails(){
        browser.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        WebElement ItemFrame = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div"));
        actions.moveToElement(ItemFrame).perform();
        WebElement AddToCompareButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[3]/div[2]/a"));
        AddToCompareButton.click();
        try {
            Thread.sleep(3000);
        }
        catch
        (
                InterruptedException
                        ignored) {
        }
        assertEquals(AddToCompareButton.getAttribute("class"),"add_to_compare checked");
        WebElement ComparePageButton = browser.findElement(By.xpath("//*[@id=\"center_column\"]/div[3]/div[2]/form/button/span"));
        ComparePageButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Products Comparison - My Store"));
    }
}
