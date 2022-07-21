package iOS;

import Utils.DeepLinkUtils;
import Utils.JsonReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_iOS_DeepLinkTest {

    AppiumDriver driver;
    JSONObject deviceObj;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        deviceObj = new JSONObject(JsonReader.parse(System.getProperty("user.dir")+"/src/main/resources/configs/devices.json")).getJSONObject("iOS");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName",deviceObj.getString("platformName"));
        caps.setCapability("automationName",deviceObj.getString("automationName"));
        caps.setCapability("platformVersion",deviceObj.getString("platformVersion"));
        caps.setCapability("deviceName",deviceObj.getString("deviceName"));
        ///TODO: Provide location to signed .ipa or .app file
        caps.setCapability("app", System.getProperty("user.dir") + deviceObj.getString("app"));
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"),caps);

    }
    @Test
    public void navigateToProductsPage() {
        DeepLinkUtils.openiOSAppWith("swaglabs://swag-overview/0,5","com.apple.mobilesafari");

    }
}
