package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class RegressionTests {
    private WebDriver driver;
    private WebDriver browser;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\owenj\\NotOneDrive\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\owenj\\NotOneDrive\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    //TC_CO_014
    @Test
    public void checkoutPayByCheck() {
        // sign in
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testing123@mailinator.com");

        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("supersecret\n");

        // add three items to the cart
        driver.get("http://automationpractice.com/index.php?id_product=3&controller=product");

        for (int i = 0; i < 3; i++) {
            // add that sucker to the cart
            WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
            addToCartButton.click();

            // wait until item is actually added
            new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(By.cssSelector(".ajax_cart_quantity"), Integer.toString(i + 1)));

            // continue adding more if need be
            if (i + 1 < 3) {
                driver.findElement(By.className("cross")).click();

                // wait a sec to not abuse the resource limit
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                ;
            }
        }

        // go to cart
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        checkoutButton.click();

        // checkout
        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.cssSelector("#center_column > form > p > button")).click();
        driver.findElement(By.name("cgv")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
        driver.findElement(By.className("cheque")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        assertTrue(driver.findElement(By.className("alert-success")).getText().equals("Your order on My Store is complete."));
        assertTrue(driver.findElement(By.cssSelector(".price > strong")).getText().equals("$80.00"));
    }

    //TC_CA_003
    @Test
    public void wrongPostalCode(){
        Random rand = new Random();
        WebElement email = driver.findElement(By.id("email_create"));
        WebElement create = driver.findElement(By.id("SubmitCreate"));
        int randInt = rand.nextInt(10000);
        email.sendKeys("testing"+randInt+"@mailinator.com");
        create.click();

        WebElement firstResult = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("customer_firstname")));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement passwd = driver.findElement(By.id("passwd"));
        Select days = new Select(driver.findElement(By.id("days")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select years = new Select(driver.findElement(By.id("years")));
        WebElement address1 = driver.findElement(By.id("address1"));
        WebElement city = driver.findElement(By.id("city"));
        Select state = new Select(driver.findElement(By.id("id_state")));
        WebElement postcode = driver.findElement(By.id("postcode"));
        WebElement phone = driver.findElement(By.id("phone_mobile"));
        WebElement alias = driver.findElement(By.id("alias"));
        WebElement submit = driver.findElement(By.cssSelector("#submitAccount > span"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        passwd.sendKeys("supersecret");
        address1.sendKeys("123 street st.");
        city.sendKeys("Anchorage");
        postcode.sendKeys("V5F4G7");
        phone.sendKeys("1234567890");
        alias.sendKeys("Home");
        state.selectByIndex(1);
        days.selectByIndex(0);
        months.selectByIndex(0);
        years.selectByIndex(0);

        submit.click();
        assertTrue(driver.findElements(By.xpath("//ol/li")).size() > 0);
    }

    //TC_ARC_011
    @Test
    public void LogOutCart() throws InterruptedException {
        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");

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
        browser.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = browser.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("seng275testing@gmail.com");

        WebElement password = browser.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("seng275rtm\n");

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
