package com.example.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WishlistFunctionalityTest {

    private WebDriver driver;
    private boolean signedIn;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/justin/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signedIn = false;
    }

    @AfterMethod
    public void cleanUp() {
        if(signedIn == true){ clearWishlists(); }
        driver.quit();
    }

    //TC_WS_001
    @Test
    public void addItemToWishlistWithoutPriorOne(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        navigateToWishlist();
        WebElement itemName = driver.findElement(By.xpath("//*[@id=\"s_title\"]"));
        assertEquals(itemName.getText(), "Faded Short Sleeve T-shirts\nS, Orange");
    }

    //TC_WS_002
    @Test
    public void addItemToWishlistCreatedBeforehand(){
        signIn();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlistNameBar = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        wishlistNameBar.sendKeys("wishlist");
        WebElement saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        navigateToWishlist();
        WebElement wishlistName = driver.findElement(By.cssSelector("a[href^='javascript']"));
        assertEquals(wishlistName.getText(), "wishlist");
        WebElement itemName = driver.findElement(By.xpath("//*[@id=\"s_title\"]"));
        assertEquals(itemName.getText(), "Faded Short Sleeve T-shirts\nS, Orange");
    }

    //TC_WS_003
    @Test
    public void noAccountNoWishlistAdd(){
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fancybox-error")));
        WebElement errorMessage = driver.findElement(By.className("fancybox-error"));
        assertEquals(errorMessage.getText(), "You must be logged in to manage your wishlist.");
    }
    //TC_WS_004
    @Test
    public void removeItemFromWishlist(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        navigateToWishlist();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-remove-sign")));
        WebElement deleteItem = driver.findElement(By.className("icon-remove-sign"));
        deleteItem.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"wlp_1_1\"]")));
    }

    //TC_WS_005
    @Test
    public void createEmptyWishlist() {
        signIn();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlistNameBar = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        wishlistNameBar.sendKeys("wishlist");
        WebElement saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        WebElement wishlistName = driver.findElement(By.cssSelector("a[href^='javascript']"));
        assertEquals(wishlistName.getText(), "wishlist");
    }

    //TC_WS_006
    @Test
    public void createMultipleWishlists() {
        signIn();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlistNameBar = driver.findElement(By.cssSelector("input[class*='inputTxt form-control']"));
        wishlistNameBar.sendKeys("wishlist1");
        WebElement saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href^='javascript']")));
        wishlistNameBar = driver.findElement(By.cssSelector("input[class*='inputTxt form-control']"));
        wishlistNameBar.sendKeys("wishlist2");
        saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        List<WebElement> wishlistNames = driver.findElements(By.cssSelector("a[href^='javascript']"));
        assertEquals(wishlistNames.get(3).getText(), "wishlist2");
    }


    //TC_WS_007, passing = test fails
    @Test
    public void addItem2ndWishlist() {
        signIn();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlistNameBar = driver.findElement(By.cssSelector("input[class*='inputTxt form-control']"));
        wishlistNameBar.sendKeys("wishlist1");
        WebElement saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href^='javascript']")));
        wishlistNameBar = driver.findElement(By.cssSelector("input[class*='inputTxt form-control']"));
        wishlistNameBar.sendKeys("wishlist2");
        saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        List<WebElement> wishlistNames = driver.findElements(By.cssSelector("a[href^='javascript']"));
        wishlistNames.get(3).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block-order-detail\"]")));
    }

    //TC_WS_008
    @Test
    public void wishlistDeleteWithItems(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        clearWishlists();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"block-history\"]")));
    }

    //TC_WS_009
    @Test
    public void wishlistDeleteWithoutItems(){
        signIn();
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlistNameBar = driver.findElement(By.cssSelector("input[class*='inputTxt form-control']"));
        wishlistNameBar.sendKeys("wishlist");
        WebElement saveButton = driver.findElement(By.id("submitWishlist"));
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clearWishlists();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"block-history\"]")));
    }

    //TC_WS_010, passing = test fails
    @Test
    public void wishlistIdenticalItemSameParam(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div/a")));
        WebElement popupExit = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/a"));
        popupExit.click();
        WebElement quantity = driver.findElement(By.xpath("//*[@id=\"quantity_wanted\"]"));
        quantity.clear();
        quantity.sendKeys("4");
        wishlistButton.click();
        navigateToWishlist();
        WebElement wishlistQuantity = driver.findElement(By.xpath("//*[@id=\"quantity_1_1\"]"));
        assertEquals(wishlistQuantity.getAttribute("value"), "5");
    }

    //TC_WS_011
    @Test
    public void wishlistIdenticalItemDiffParam(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div/a")));
        WebElement popupExit = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div/a"));
        popupExit.click();
        Select size = new Select(driver.findElement(By.xpath("//*[@id=\"group_1\"]")));
        size.selectByVisibleText("L");
        wishlistButton.click();
        navigateToWishlist();
        List<WebElement> wishlistItems = driver.findElements(By.xpath("//*[@id=\"s_title\"]"));
        assertEquals(wishlistItems.get(0).getText(), "Faded Short Sleeve T-shirts\nS, Orange");
        assertEquals(wishlistItems.get(1).getText(), "Faded Short Sleeve T-shirts\nL, Orange");


    }

    //TC_WS_012, passing = test fails
    @Test
    public void sendWishlist(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        navigateToWishlist();
        WebElement sendWishlistButton = driver.findElement(By.id("showSendWishlist"));
        sendWishlistButton.click();
        sendWishlistButton.click();
        WebElement emailName = driver.findElement(By.xpath("//*[@id=\"email1\"]"));
        emailName.sendKeys("testing123@mailinator.com");
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=testing123");
        assertFalse(driver.findElements(By.xpath("//td[contains(.,'Automation Practice')]")).size() > 0);
    }

    //TC_WS_013
    @Test
    public void wishlistPermalink(){
        signIn();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        WebElement wishlistButton = driver.findElement(By.id("wishlist_button"));
        wishlistButton.click();
        navigateToWishlist();
        WebElement permalink = driver.findElement(By.xpath("//*[@id=\"block-order-detail\"]/div/p[1]/input"));
        driver.get(permalink.getAttribute("value"));
        WebElement itemInfo = driver.findElement(By.xpath("//*[@id=\"s_title\"]"));
        assertEquals(itemInfo.getText(), "Faded Short Sleeve T-shirts\nS, Orange");


    }


    private void signIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("seng275test@outlook.com");
        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("Seng275\n");
        signedIn = true;
    }

    private void navigateToWishlist(){
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        WebElement wishlist = driver.findElement(By.cssSelector("a[href^='javascript']"));
        wishlist.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wlp_1_1\"]")));
    }

    private void clearWishlists(){
        driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        List<WebElement> deleteButton = driver.findElements(By.cssSelector("i[class*='icon-remove']"));
        for(int i = 0; i<deleteButton.size(); i++){
            deleteButton.get(i).click();
            driver.switchTo().alert().accept();
        }
    }
}
