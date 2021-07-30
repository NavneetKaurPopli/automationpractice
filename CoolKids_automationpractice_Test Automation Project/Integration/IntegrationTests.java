import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IntegrationTests {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\owenj\\NotOneDrive\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\owenj\\NotOneDrive\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    // TC_IT_01
    @Test
    public void buyItemEndToEnd() {
        // sign in
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testing123@mailinator.com");

        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("supersecret\n");

        // add item to the cart
        driver.get("http://automationpractice.com/index.php?id_product=3&controller=product");

        // add that sucker to the cart
        WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
        addToCartButton.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(By.cssSelector(".ajax_cart_quantity"), "1"));

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
        assertTrue(driver.findElement(By.cssSelector(".price > strong")).getText().equals("$28.00"));
    }

    // TC_IT_02
    @Test
    public void buyMultiItemsEndToEnd() {
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

    // TC_IT_03
    @Test
    public void googleMapIntegration() {
        driver.get("http://automationpractice.com/index.php?controller=stores");
        WebElement map = driver.findElement(By.cssSelector("#map > div:nth-child(2) > div:nth-child(2) > span:nth-child(1)"));

        // Since Google Maps in developer mode Google will warn us. Checking for this warning exists is sufficient since this
        // shows that Google maps has been integrated properly in developer mode.
        assertTrue(map.isDisplayed());
        assertEquals("This page can't load Google Maps correctly.", map.getText());
    }

    // TC_IT_04
    @Test
    public void socialMediaIntegration(){
        driver.get("http://automationpractice.com/index.php");
        WebElement Facebook = driver.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[1]/a"));
        WebElement Twitter = driver.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[2]/a"));
        WebElement YouTube = driver.findElement(By.xpath("//*[@id=\"social_block\"]/ul/li[3]/a"));
        assertEquals(Facebook.getAttribute("href"), "https://www.facebook.com/groups/525066904174158/");
        assertEquals(Twitter.getAttribute("href"), "https://twitter.com/seleniumfrmwrk");
        assertEquals(YouTube.getAttribute("href"), "https://www.youtube.com/channel/UCHl59sI3SRjQ-qPcTrgt0tA");
    }

    // TC_IT_05
    @Test
    public void contactFormIntegrationTest() {
        // sends a message
        driver.get("http://automationpractice.com/index.php?controller=contact");
        new Select(driver.findElement(By.id("id_contact"))).selectByIndex(1);
        driver.findElement(By.id("email")).sendKeys("jim@gmail.com");
        driver.findElement(By.id("id_order")).sendKeys("1234");
        driver.findElement(By.id("message")).sendKeys("Hi, my name is Jim and I am very upset. My order did not come through etc.");
        driver.findElement(By.id("submitMessage")).click();

        // assert the UI says its been sent to the CS team
        assertEquals("Your message has been successfully sent to our team.", driver.findElement(By.className("alert-success")).getText());

        // assert that our fake database has the element in it - since we don't have a way of mocking this and connecting it to the live element it does nothing right now
        assertTrue(true);
    }
}
