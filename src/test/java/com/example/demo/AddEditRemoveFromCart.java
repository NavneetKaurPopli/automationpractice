package com.example.demo;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AddEditRemoveFromCart {
    WebDriver browser;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ama/Desktop/SENG 275/web_testing/chromedriver");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        //driver.get("http://automationpractice.com/index.php?controller=password");
        browser.manage().window().maximize();

        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");

    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }

    @Test
    public void AddFromQuickView() throws InterruptedException {
        //test fails for some reason. still working to fix it
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img"));
        Thread.sleep(5000);
        action.moveToElement(ItemToAdd).perform();      //hovering action

        WebElement QuickViewButton = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[1]/div/a[2]/span"));
        QuickViewButton.click();
        Thread.sleep(9000);

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div/div[3]/form/div/div[3]/div[1]/p/button"));
        Thread.sleep(3000);
        AddToCart.click();
        Thread.sleep(3000);

        WebElement close = browser.findElement(By.xpath("/html/body/div[2]/div/div/a"));
        Thread.sleep(3000);
        close.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();
        Thread.sleep(3000);

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_1_1_0_506305\"]/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Item.getText());
    }

    //TC_ARC_002
    @Test
    public void AddFromHome(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img"));
        action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_1_1_0_506305\"]/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Item.getText());

    }

    //TC_ARC_003
    @Test
    public void AddFromWomen(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();

        WebElement women = browser.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
        women.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/h5/a"));
        action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/div[2]/a[1]/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_2_7_0_506305\"]/td[2]/p/a"));
        assertEquals("Blouse", Item.getText());

    }

    //TC_ARC_004
    @Test
    public void AddFromDresses(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();

        WebElement dresses = browser.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
        dresses.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[3]/ul"));
        action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_3_13_0_506305\"]/td[2]/p/a"));
        assertEquals("Printed Dress", Item.getText());

    }

    //TC_ARC_005
    @Test
    public void AddFromTShirt(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();

        WebElement Tshirt = browser.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a"));
        Tshirt.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a"));
        action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_1_1_0_506305\"]/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Item.getText());
    }

    //TC_ARC_006
    @Test
    public void FromWishlist(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();

        WebElement AddtoWish = browser.findElement(By.xpath("//*[@id=\"wishlist_button\"]"));
        AddtoWish.click();

        WebElement account = browser.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
        account.click();

        WebElement Wishlist = browser.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[2]/ul/li/a/span"));
        Wishlist.click();

        WebElement List = browser.findElement(By.xpath("//*[@id=\"wishlist_33680\"]/td[1]/a"));
        List.click();
        new WebDriverWait(browser, 5);

        //assertThrows(browser.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).isDisplayed());
        try {
            WebElement AddtoCart = browser.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"));
        } catch (Exception e){
            String exception = e.getMessage();
           assertNotEquals(0, exception.length());
        }
        //System.out.println(AddtoCart.isDisplayed());
        //assertFalse(AddtoCart.isDisplayed());

        /*WebElement item = browser.findElement(By.cssSelector(""));
        item.click();

        WebElement AddtoCart = browser.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"));
        AddtoCart.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Cart.click();

        WebElement Item = browser.findElement(By.xpath("//*[@id=\"product_1_1_0_506305\"]/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Item.getText());*/

    }

    //TC_ARC_007
    @Test
    public void RemoveFromCart() throws InterruptedException {
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img"));
        action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span"));
        AddToCart.click();

        WebElement close = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"));
        Thread.sleep(3000);
        close.click();

        WebElement Cart = browser.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b"));
        Thread.sleep(3000);
        Cart.click();
        Thread.sleep(3000);

        WebElement delete = browser.findElement(By.cssSelector("#\\31 _1_0_506305 > i"));
        delete.click();
        Thread.sleep(3000);

        WebElement warning = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"));
        //System.out.println(warning.getText());
        assertEquals("Your shopping cart is empty.",warning.getText());
    }

    //TC_ARC_008
    @Test
    public void IncreaseQuantity(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();
        //action.moveToElement(ItemToAdd).perform();
        new WebDriverWait(browser, 5);

        WebElement IncreaseButton = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[2]/p[1]/a[2]/span/i"));
        IncreaseButton.click();
        new WebDriverWait(browser,5);

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a"));
        Cart.click();

        WebElement quantity = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/h1/span/span"));
        assertEquals("2 Products", quantity.getText());
    }

    //TC_ARC_009
    @Test
    public void EditSize(){
        WebElement homeIcon = browser.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > a > i"));
        homeIcon.click();
        Actions action = new Actions(browser);

        WebElement ItemToAdd = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();
        new WebDriverWait(browser, 5);

        WebElement dropdown = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[2]/div/fieldset[1]/div/div/select"));
        Select dropdown1 = new Select(dropdown);
        dropdown1.selectByVisibleText("L");

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button/span"));
        AddToCart.click();

        WebElement Cart = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a"));
        Cart.click();

        WebElement Size = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/small[2]/a"));
        assertEquals("Color : Orange, Size : L", Size.getText());

    }

    //TC_ARC_010
    @Test
    public void LogOUTAndInCart() throws InterruptedException {
        WebElement homeIcon = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/a/i"));
        homeIcon.click();

        WebElement ItemToAdd = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button"));
        AddToCart.click();
        Thread.sleep(5000);

        WebElement close = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"));
        close.click();

        WebElement Cart = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a/b"));
        Thread.sleep(2000);
        Cart.click();
        Thread.sleep(3000);
        //new WebDriverWait(browser, 5);

        //asserting that item is initially in cart before logout
        WebElement Clothing = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Clothing.getText());

        //logging out
        WebElement logout = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a"));
        logout.click();
        Thread.sleep(3000);

        //logging back in
        WebElement Signin = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        Signin.click();
        Thread.sleep(3000);

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");
        Thread.sleep(3000);

        WebElement Cart1 = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a/b"));
        Thread.sleep(2000);
        Cart1.click();
        Thread.sleep(2000);

        WebElement alert = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"));
        Thread.sleep(3000);
        assertEquals("Your shopping cart is empty.", alert.getText());
        //test fails because item shouldve remained in cart when user logged out and logged back in

    }

    //TC_ARC_011
    @Test
    public void LogOutCart() throws InterruptedException {
        WebElement logout = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a"));
        logout.click();

        WebElement homeIcon = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/a/i"));
        homeIcon.click();

        WebElement ItemToAdd = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();
        //new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button"));
        AddToCart.click();
        //Thread.sleep(5000);
        //new WebDriverWait(browser, 5);

        WebElement Popup = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/h2/span[2]"));
        //Popup.click();
        Thread.sleep(5000);
        assertEquals("There is 1 item in your cart.", Popup.getText());

        WebElement Item = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/div[2]/span[1]"));
        Thread.sleep(2000);
        assertEquals("Faded Short Sleeve T-shirts", Item.getText());

        WebElement close = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"));
        close.click();

        WebElement Cart = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a/b"));
        Thread.sleep(2000);
        Cart.click();
        Thread.sleep(3000);
        //new WebDriverWait(browser, 5);

        WebElement Clothing = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a"));
        assertEquals("Faded Short Sleeve T-shirts", Clothing.getText());

    }

    //TC_ARC_012
    @Test
    public void LogOutRemoveCart() throws InterruptedException {
        WebElement logout = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a"));
        logout.click();

        WebElement homeIcon = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/a/i"));
        homeIcon.click();

        WebElement ItemToAdd = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a"));
        ItemToAdd.click();
        //new WebDriverWait(browser, 5);

        WebElement AddToCart = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button"));
        AddToCart.click();

        WebElement close = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"));
        Thread.sleep(3000);
        close.click();

        WebElement Cart = browser.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a/b"));
        Thread.sleep(2000);
        Cart.click();
        Thread.sleep(3000);

        WebElement delete = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[7]/div/a/i"));
        Thread.sleep(3000);
        delete.click();
        Thread.sleep(3000);

        WebElement alert = browser.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"));
        Thread.sleep(3000);
        assertEquals("Your shopping cart is empty.", alert.getText());
    }

}
