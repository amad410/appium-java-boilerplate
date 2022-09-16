package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.maven.settings.Server;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Driver Manager class to initialize driver based on
 * platform (i.e., Android or iOS).
 */
public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);
    }

    public void initializeDriver(GlobalParams params, String propertyFileName) throws Exception {
        AppiumDriver driver = null;

        if(driver == null){
            try{
                utils.log().info("initializing Appium driver");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                Properties props = new PropertyManager().getProps(propertyFileName);
                switch(params.getPlatformName()){
                    case "Android":
                        capabilities.setCapability("platformName", props.getProperty("platformName"));
                        capabilities.setCapability("platformVersion", props.getProperty("platformVersion"));
                        capabilities.setCapability("deviceName", props.getProperty("deviceName"));
                        capabilities.setCapability("app",
                                System.getProperty("user.dir") + props.getProperty("app"));
                        //driver = new AndroidDriver(new ServerManager().getServer().getUrl(),new CapabilitiesManager().getCaps(params,propertyFileName));
                        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
                        //driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), new CapabilitiesManager().getCaps(params,propertyFileName));
                        break;
                    case "iOS":
                        capabilities.setCapability("platformName", props.getProperty("platformName"));
                        capabilities.setCapability("platformVersion", props.getProperty("platformVersion"));
                        capabilities.setCapability("deviceName", props.getProperty("deviceName"));
                        capabilities.setCapability("app",
                                System.getProperty("user.dir") + props.getProperty("app"));
                        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), new CapabilitiesManager().getCaps(params,propertyFileName));
                        break;
                }
                if(driver == null){
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }
    public void initializeDriver(ServerManager server, GlobalParams params, String propertyFileName) throws Exception {
        AppiumDriver driver = null;

        if(driver == null){
            try{
                utils.log().info("initializing Appium driver");
                switch(params.getPlatformName()){
                    case "Android":
                        /*Properties props = new PropertyManager().getProps(propertyFileName);
                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability("platformName", props.getProperty("platformName"));
                        capabilities.setCapability("platformVersion", props.getProperty("platformVersion"));
                        capabilities.setCapability("deviceName", props.getProperty("deviceName"));
                        capabilities.setCapability("app",
                                System.getProperty("user.dir") + props.getProperty("app"));*/
                        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),new CapabilitiesManager().getCaps(params,propertyFileName));
                        // driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
                        break;
                    case "iOS":
                        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), new CapabilitiesManager().getCaps(params,propertyFileName));
                        break;
                }
                if(driver == null){
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }
}
