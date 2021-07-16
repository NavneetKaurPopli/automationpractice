package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegressionTests {
    private WebDriver driver;

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
    public void checkoutPayByCheck(){
        // sign in
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("testing123@mailinator.com");

        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("supersecret\n");

        // add three items to the cart
        driver.get("http://automationpractice.com/index.php?id_product=3&controller=product");

        for(int i = 0; i < 3; i++) {
            // add that sucker to the cart
            WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
            addToCartButton.click();

            // wait until item is actually added
            new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(By.cssSelector(".ajax_cart_quantity"), Integer.toString(i+1)));

            // continue adding more if need be
            if(i + 1 < 3) {
                driver.findElement(By.className("cross")).click();

                // wait a sec to not abuse the resource limit
                try{Thread.sleep(500);}catch(InterruptedException e){};
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
}
