package iOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_iOS_Built_In_App {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("automationName","XCUITest");
        caps.setCapability("platformVersion","14.4");
        caps.setCapability("deviceName","iPhone 12");
        ///TODO: Provide bundle Id of built-in or already installed app
        caps.setCapability("bundleId","");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }

    @Test
    public void click_App_Button(){
        //code

    }

    @AfterTest
    public void tearDown(){
        if(null != driver)
        {
            driver.quit();
        }
    }
}
