package com.example.demo;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class FooterMenuTests {
    WebDriver browser;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","D:\\Users\\Cord\\Class things\\SENG 275\\chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp(){
        browser.quit();
    }

    @Test
    public void SocialMediaLinks(){
        browser.get("http://automationpractice.com/index.php");
        WebElement Facebook = browser.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[1]/a"));
        WebElement Twiiter = browser.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[2]/a"));
        WebElement YouTube = browser.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[3]/a"));
        assertEquals(Facebook.getAttribute("href"), "https://www.facebook.com/groups/525066904174158/");
        assertEquals(Twiiter.getAttribute("href"), "https://twitter.com/seleniumfrmwrk");
        assertEquals(YouTube.getAttribute("href"), "https://www.youtube.com/channel/UCHl59sI3SRjQ-qPcTrgt0tA");
    }

    @Test
    public void Specials(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[1]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Prices drop - My Store");
    }

    @Test
    public void Categories(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"footer\"]/div/section[2]/div/div/ul/li/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Women - My Store");
    }

    @Test
    public void NewProducts(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[2]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"New products - My Store");
    }

    @Test
    public void ContactUs(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[5]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Contact us - My Store");
        WebElement TextBox = browser.findElement(By.xpath("//*[@id=\"message\"]"));
        WebElement HeadingDropDown = browser.findElement(By.xpath("//*[@id=\"id_contact\"]"));
        WebElement EmailBox = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        TextBox.sendKeys("Hi, I am filling this box out automatically. Thank you!");
        EmailBox.sendKeys("mail@maildrop.cc");
    }

    @Test
    public void OurStores(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[2]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Stores - My Store");
    }

    @Test
    public void BestSellers(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[3]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Best sales - My Store");
    }

    @Test
    public void TermsAndConditions(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("///*[@id=\"block_various_links_footer\"]/ul/li[6]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Terms and conditions of use - My Store");
    }

    @Test
    public void AboutUs(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[7]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"About us - My Store");
    }

    @Test
    public void Sitemap(){
        browser.get("http://automationpractice.com/index.php");
        WebElement SpecialsLink = browser.findElement(By.xpath("//*[@id=\"block_various_links_footer\"]/ul/li[8]/a"));
        browser.get(SpecialsLink.getAttribute("href"));
        assertEquals(browser.getTitle(),"Sitemap - My Store");
    }

    @Test
    public void MyOrders(){

    }

    @Test
    public void MyCreditSlips(){

    }

    @Test
    public void MyAddresses(){

    }

    @Test
    public void MyPersonalInfo(){

    }

    @Test
    public void NewsLetter(){

    }
}
