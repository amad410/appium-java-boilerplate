package Base;

import Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.Before;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseSteps {
    protected static ThreadLocal<AppiumDriver> _driver = new ThreadLocal<AppiumDriver>();
    private static AppiumDriverLocalService server;
    protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
    protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
    protected static ThreadLocal <String> platform = new ThreadLocal<String>();
    protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return _driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        _driver.set(driver);
    }

    @Parameters({"platformName"})
    @Before
    public void beforeTest(String platformName) throws Exception {
        setDateTime(utils.dateTime());
        setPlatform(platformName);

        switch(platformName) {
            case "Android":
                Android_setUp();
                break;
            case "iOS":
                iOS_setUp();
                break;
            default:
                throw new Exception("Invalid platform! - " + platformName);
        }

    }

    public void Android_setUp() throws MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apps/ApiDemos.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        setDriver(driver);

    }

    public void iOS_setUp() throws MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "14.4");
        capabilities.setCapability("deviceName", "iPhone 11 Pro");
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apps/*.zip");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        setDriver(driver);
    }

    public BaseSteps() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @BeforeSuite
    public void beforeSuite() throws Exception, Exception {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        //server = getAppiumService(); // -> If using Mac, uncomment this statement and comment below statement
        server = getAppiumServerDefault(); // -> If using Windows, uncomment this statement and comment above statement
        if(!checkIfAppiumServerIsRunnning(4723)) {
            server.start();
            server.clearOutPutStreams(); // -> Comment this if you don't want to see server logs in the console
            utils.log().info("Appium server started");
        } else {
            utils.log().info("Appium server already running");
        }
    }

    public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            System.out.println("1");
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if(server.isRunning()){
            server.stop();
            utils.log().info("Appium server stopped");
        }
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform2) {
        platform.set(platform2);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String dateTime2) {
        dateTime.set(dateTime2);
    }

    // for Windows
    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    // for Mac. Update the paths as per your Mac setup
    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin:/Users/omprakashchavan/Library/Android/sdk/tools:/Users/omprakashchavan/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/omprakashchavan/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
                .withEnvironment(environment)
                .withLogFile(new File("ServerLogs/server.log")));
    }
    public void closeApp() {
        ((InteractsWithApps) getDriver()).closeApp();
    }

    public void launchApp() {
        ((InteractsWithApps) getDriver()).launchApp();
    }
    @AfterTest
    public void tearDown() {
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
