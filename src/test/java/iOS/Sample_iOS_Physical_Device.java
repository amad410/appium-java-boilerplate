package iOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_iOS_Physical_Device {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("automationName","XCUITest");
        ///TODO: Provide platform version
        caps.setCapability("platformVersion","14.4");
        ///TODO: Provide device name
        caps.setCapability("deviceName","");
        ///TODO: Provide UDID of device
        caps.setCapability("udid","XXXXXXXX-XXXXXXXXXXXX");
        ///TODO: Provide XCode Org Id of device (team name or ios developer account email here)
        caps.setCapability("xcodeOrgId","");
        caps.setCapability("xcodeSigningId","iPhone Developer");
        caps.setCapability("useNewWDA",true);
        ///TODO: Provide path to webdriveragent runner
        caps.setCapability("derivedDataPath","");
        ///TODO: Provide location to signed .ipa or .app file built for physical devices
        caps.setCapability("app",System.getProperty("user.dir")+"/apps/*.app");
        ///TODO: Provide location to zip with signed .ipa or .app file, if applicable
        //caps.setCapability("app",System.getProperty("user.dir")+"/apps/*.zip");
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
