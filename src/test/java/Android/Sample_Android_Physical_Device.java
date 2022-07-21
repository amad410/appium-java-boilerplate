package Android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_Android_Physical_Device {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("automationName","UiAutomator2");
        ///TODO: Provide platform version
        caps.setCapability("platformVersion","9.0");
        caps.setCapability("deviceName","Nexus");
        //TODO: provide appPackage
        caps.setCapability("appPackage","");
        ///TODO: provide appActivity
        caps.setCapability("appActivity","");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }
    @Test
    public void click_App_Button(){
        //test code

    }

    @AfterTest
    public void tearDown(){
        if(null != driver)
        {
            driver.quit();
        }
    }
}
