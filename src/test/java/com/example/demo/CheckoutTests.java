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

import java.util.concurrent.TimeUnit;

public class CheckoutTests {
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

    //TC_CO_001
    @Test
    public void checkoutEmptyCart(){
        driver.get("http://automationpractice.com/index.php");

        WebElement checkoutButton = driver.findElement(By.cssSelector("div .shopping_cart > a"));
        checkoutButton.click();

        WebElement warning = driver.findElement(By.cssSelector(".alert.alert-warning"));
        System.out.println(warning.getText());
        assertTrue(warning.getText().equals("Your shopping cart is empty."));
    }

    //TC_CO_002
    @Test
    public void checkoutWithOneItem(){
        signIn();
        addItemsToCart(1);

        // makes sure the correct item is in the cart
        driver.findElement(By.linkText("Printed Dress"));
        assertTrue(driver.findElement(By.id("total_price")).getText().equals("$28.00"));

        goToSignInFromSummary();
        goToShippingFromAddress();
        agreeToShippingTermsOfService();
        goToPaymentFromShipping();
        payByCheque();
        assertOrderComplete("$28.00");
    }

    //TC_CO_003
    @Test
    public void checkoutWithMultiItems(){
        signIn();
        addItemsToCart(3);

        // makes sure the correct item is in the cart
        driver.findElement(By.linkText("Printed Dress"));
        assertTrue(driver.findElement(By.id("total_price")).getText().equals("$80.00"));

        goToSignInFromSummary();
        goToShippingFromAddress();
        agreeToShippingTermsOfService();
        goToPaymentFromShipping();
        payByCheque();
        assertOrderComplete("$80.00");
    }

    //TC_CO_004
    @Test
    public void checkoutWithoutAcceptingTerms(){

    }

    //TC_CO_005
    @Test
    public void checkoutSignIn(){

    }

    //TC_CO_006
    @Test
    public void checkoutNoSignIn(){

    }

    //TC_CO_007
    @Test
    public void checkoutUpdateShipping(){

    }

    //TC_CO_008
    @Test
    public void checkoutUpdateBilling(){

    }

    //TC_CO_009
    @Test
    public void checkoutAsGuest(){
        // test fails as expected until feature is developed
        // TODO: develop
        assertTrue(false);
    }

    //TC_CO_010
    @Test
    public void checkoutAddNewBilling(){

    }

    //TC_CO_011
    @Test
    public void checkoutDifferentBilling(){

    }

    //TC_CO_012
    @Test
    public void checkoutAddComment(){

    }

    //TC_CO_013
    @Test
    public void checkoutPayByBankWire(){

    }

    //TC_CO_014
    @Test
    public void checkoutPayByCheck(){

    }

    // helper methods

    private void addItemsToCart(int num) {
        // find a product (adding to cart is separately tested so it can be the same one)
        driver.get("http://automationpractice.com/index.php?id_product=3&controller=product");

        for(int i = 0; i < num; i++) {
            // add that sucker to the cart
            WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
            addToCartButton.click();

            // wait until item is actually added
            new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(By.cssSelector(".ajax_cart_quantity"), Integer.toString(i+1)));

            // continue adding more if need be
            if(i + 1 < num) {
                 driver.findElement(By.className("cross")).click();

                 // wait a sec to not abuse the resource limit
                try{Thread.sleep(500);}catch(InterruptedException e){};
            }
        }

        // go to cart
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        checkoutButton.click();
    }

    private void signIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication");

        WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("testing123@mailinator.com");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"passwd\"]"));
        password.sendKeys("supersecret\n");
    }

    private void goToSignInFromSummary() {
        driver.findElement(By.linkText("Proceed to checkout")).click();
    }

    private void goToShippingFromAddress() {
        driver.findElement(By.cssSelector("#center_column > form > p > button")).click();
    }

    private void goToPaymentFromShipping() {
        driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
    }

    private void agreeToShippingTermsOfService() {
        driver.findElement(By.name("cgv")).click();
    }

    private void payByCheque() {
        driver.findElement(By.className("cheque")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
    }

    private void assertOrderComplete(String expectedPrice) {
        assertTrue(driver.findElement(By.className("alert-success")).getText().equals("Your order on My Store is complete."));
        assertTrue(driver.findElement(By.cssSelector(".price > strong")).getText().equals(expectedPrice));
    }
}
