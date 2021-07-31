package io.testproject.generated.tests.mobiletesting;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.drivers.android.AndroidDriver;
import io.testproject.sdk.interfaces.junit5.ExceptionsReporter;
import java.lang.Exception;
import java.lang.Override;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class was automatically generated by TestProject
 * Project: Mobile Testing
 * Test: Checkout
 * Generated by: Cord Corcese (badwithnames14@gmail.com)
 * Generated on Sat Jul 31 04:42:53 GMT 2021.
 */
@DisplayName("Checkout")
public class Checkout implements ExceptionsReporter {
  public static AndroidDriver<? extends MobileElement> driver;

  @BeforeAll
  static void setup() throws Exception {
    driver = new AndroidDriver<>("u5NRImjEhv-vbqqSIuFIt_cUSRNR-0xtBqmQikpXbr8", getCapabilities(), "Cord Corcese");
  }

  /**
   * In order to upload the test to TestProject need to un-comment @ArgumentsSource and set in comment the @MethodSource
   * @org.junit.jupiter.params.provider.ArgumentsSource(io.testproject.sdk.interfaces.parameterization.TestProjectParameterizer.class) */
  @DisplayName("Checkout")
  @ParameterizedTest
  @MethodSource("provideParameters")
  void execute() throws Exception {
    // set timeout for driver actions (similar to step timeout)
    driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
    By by;
    boolean booleanResult;

    // 1. Reset App
    //    Clear application data and restart (Auto-generated)
    GeneratedUtils.sleep(500);
    driver.resetApp();

    // 2. Click ''
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = '']");
    driver.findElement(by).click();

    // 3. Click 'Check out'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'Check out']");
    driver.findElement(by).click();

    // 4. Make a Swipe gesture from ('673','1454') to ('648','907')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(673,1454))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(648,907)).release().perform();

    // 5. Click 'Proceed to checkout1'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'Proceed to checkout']");
    driver.findElement(by).click();

    // 6. Make a Swipe gesture from ('511','1477') to ('486','469')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(511,1477))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(486,469)).release().perform();

    // 7. Make a Swipe gesture from ('569','1585') to ('574','917')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(569,1585))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(574,917)).release().perform();

    // 8. Click 'Proceed to checkout2'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'Proceed to checkout']");
    driver.findElement(by).click();

    // 9. Make a Swipe gesture from ('688','1499') to ('688','622')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(688,1499))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(688,622)).release().perform();

    // 10. Click 'ANDROID.VIEW.VIEW'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[3]/android.view.View[1]/android.view.View");
    driver.findElement(by).click();

    // 11. Click 'Proceed to checkout3'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'Proceed to checkout ']");
    driver.findElement(by).click();

    // 12. Make a Swipe gesture from ('748','1587') to ('748','872')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(748,1587))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(748,872)).release().perform();

    // 13. Make a Swipe gesture from ('733','985') to ('726','380')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(733,985))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(726,380)).release().perform();

    // 14. Make a Swipe gesture from ('590','1713') to ('585','1071')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(590,1713))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(585,1071)).release().perform();

    // 15. Make a Swipe gesture from ('721','950') to ('723','1154')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(721,950))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(723,1154)).release().perform();

    // 16. Make a Swipe gesture from ('668','532') to ('665','806')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(668,532))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(665,806)).release().perform();

    // 17. Click ''
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[1]/android.view.View/android.view.View[@text = '']");
    driver.findElement(by).click();

    // 18. Make a Swipe gesture from ('736','1550') to ('736','776')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(736,1550))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(736,776)).release().perform();

    // 19. Click 'I confirm my order'
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'I confirm my order']");
    driver.findElement(by).click();

    // 20. Make a Swipe gesture from ('663','1512') to ('668','1015')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(663,1512))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(668,1015)).release().perform();

    // 21. Make a Swipe gesture from ('723','1633') to ('731','1338')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(723,1633))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(731,1338)).release().perform();

    // 22. Make a Swipe gesture from ('597','1552') to ('607','1225')
    GeneratedUtils.sleep(500);
    (new TouchAction(driver)).press(PointOption.point(597,1552))
        				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
        				.moveTo(PointOption.point(607,1225)).release().perform();

    // 23. Does 'Your order on My Store is complete.' contain 'Your order on My Store is complete.'?
    GeneratedUtils.sleep(500);
    by = By.xpath("//android.view.View[@text = 'Your order on My Store is complete.']");
    Assertions.assertTrue(driver.findElement(by).getText().contains("Your order on My Store is complete."));

  }

  @Override
  public ReportingDriver getDriver() {
    return (ReportingDriver) driver;
  }

  @AfterAll
  static void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  private static Stream provideParameters() throws Exception {
    return Stream.of(Arguments.of());
  }

  public static DesiredCapabilities getCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    capabilities.setCapability(MobileCapabilityType.UDID, "52003ecefaf8a427");
    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.mozilla.firefox");
    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.mozilla.firefox.App");
    return capabilities;
  }
}