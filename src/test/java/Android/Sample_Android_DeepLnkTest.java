package Android;

import Utils.DeepLinkUtils;
import Utils.JsonReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_Android_DeepLnkTest {

    AppiumDriver driver;
    JSONObject deviceObj;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        deviceObj = new JSONObject(JsonReader.parse(System.getProperty("user.dir")+"/src/main/resources/configs/devices.json")).getJSONObject("Android");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceObj.getString("platformName"));
        caps.setCapability("udid", deviceObj.getString("udid"));
        caps.setCapability("app", System.getProperty("user.dir") + deviceObj.getString("app"));
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }
    @Test
    public void navigateToProductsPage() {
        DeepLinkUtils.openAndroidAppWith("swaglabs://swag-overview/0,5",deviceObj.getString("appPackage"));

    }
}
