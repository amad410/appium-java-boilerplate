package iOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Sample_iOS_WebTest {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("automationName","XCUITest");
        caps.setCapability("platformVersion","14.4");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("deviceName", "iPhone 11 Pro");
        caps.setCapability("safari:useSimulator", true);
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void userLogin(){
        //code
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        //use standard locators
        WebElement username = driver.findElementByCssSelector("input#username");
        username.sendKeys("tomsmith");
        WebElement password = driver.findElementByCssSelector("input#password");
        password.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElementByCssSelector("button.radius");
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("secure"));
        System.out.println(driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDown(){
        if(null != driver)
        {
            driver.quit();
        }
    }
}
