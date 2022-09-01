package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Capabilities Manager for managing capabilities and return those capabilities
 * based on the platform.
 */
public class CapabilitiesManager {
    TestUtils utils = new TestUtils();
    public DesiredCapabilities getCaps(GlobalParams params, String propertyFileName) throws IOException {

        Properties props = null;

        try{
            props = new PropertyManager().getProps(propertyFileName);
            utils.log().info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());

            switch(props.getProperty("platformName")){
                case "Android":
                    caps.setCapability("platformName", props.getProperty("platformName"));
                    caps.setCapability("platformVersion", props.getProperty("platformVersion"));
                    caps.setCapability("deviceName", props.getProperty("deviceName"));
                   //caps.setCapability("systemPort", params.getSystemPort());
                   // caps.setCapability("chromeDriverPort", params.getChromeDriverPort());
                    caps.setCapability("app",
                            System.getProperty("user.dir") + props.getProperty("app"));

                    /*
                    utils.log().info("Android appUrl is" + props.getProperty("app"));
                    caps.setCapability("app",
                            props.getProperty("androidAppUrl"));*/
                    utils.log().info("app is located " + props.getProperty("app"));
                    break;
                case "iOS":
                    caps.setCapability("platformName", props.getProperty("platformName"));
                    caps.setCapability("platformVersion", props.getProperty("platformVersion"));
                    caps.setCapability("deviceName", props.getProperty("deviceName"));
                    caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    //caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    caps.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                    //caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    caps.setCapability("app",
                            System.getProperty("user.dir") + props.getProperty("app"));
                    /*
                    utils.log().info("iOS appUrl is" + props.getProperty("app"));
                    caps.setCapability("app",
                            props.getProperty("iOSAppUrl"));*/
                    utils.log().info("appUrl is" + props.getProperty("app"));
                    break;
            }
            return caps;
        } catch(Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }
}
