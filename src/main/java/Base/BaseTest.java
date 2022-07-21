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
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest extends AbstractTestNGCucumberTests {

    protected static ThreadLocal<AppiumDriver> _driver = new ThreadLocal<AppiumDriver>();
    private static AppiumDriverLocalService server;
    protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
    protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
    protected static ThreadLocal <String> platform = new ThreadLocal<String>();
    protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
    TestUtils utils;

   public AppiumDriver getDriver() {
        return _driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        _driver.set(driver);
    }

    @Parameters({"platformName"})
    @BeforeTest
    public void beforeTest(String platformName) throws Exception {
        utils = new TestUtils();
        setDateTime(utils.dateTime());
        setPlatform(platformName);
        URL url;
        InputStream inputStream = null;
        InputStream stringsis = null;
        Properties props = new Properties();
        AppiumDriver driver;

        String strFile = "logs" + File.separator + platformName;
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }

        try{
            props = new Properties();
            String propFileName = "config.properties";
            String xmlFileName = "strings/strings.xml";

            utils.log().info("load " + propFileName);
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            setProps(props);

            utils.log().info("load " + xmlFileName);
            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            setStrings(utils.parseStringXML(stringsis));
            //route logs to separate file for each thread
            ThreadContext.put("ROUTINGKEY", strFile);
            utils.log().info("log path: " + strFile);

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
        catch (Exception e) {
            utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
            throw e;
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
            if(stringsis != null) {
                stringsis.close();
            }
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

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @BeforeSuite
    public void beforeSuite() throws Exception, Exception {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        //server = getAppiumService(); // -> If using Mac, uncomment this statement and comment below statement
        server = getAppiumServerDefault(); // -> If using Windows, uncomment this statement and comment above statement
        if(!checkIfAppiumServerIsRunning(4723)) {
            server.start();
            server.clearOutPutStreams(); // -> Comment this if you don't want to see server logs in the console
           utils.log().info("Appium server started");
        } else {
            utils.log().info("Appium server already running");
        }
    }

    public boolean checkIfAppiumServerIsRunning(int port) throws Exception {
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
    public void setProps(Properties props2) {
        props.set(props2);
    }
    public HashMap<String, String> getStrings() {
        return strings.get();
    }

    public void setStrings(HashMap<String, String> strings2) {
        strings.set(strings2);
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

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    // for Windows
    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    // for Mac. Update the paths as per your Mac setup
    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "C:\\Program Files\\Microsoft\\jdk-11.0.12.7-hotspot\\bin;C:\\Program Files (x86)\\RSA SecurID Token Common;C:\\Program Files\\Java\\jdk-13.0.1\\bin;C:\\Program Files\\dotnet\\;C:\\Program Files\\Microsoft SQL Server\\130\\Tools\\Binn\\;C:\\Program Files\\Microsoft SQL Server\\Client SDK\\ODBC\\170\\Tools\\Binn\\;C:\\Program Files\\Docker\\Docker\\resources\\bin;C:\\ProgramData\\DockerDesktop\\version-bin;C:\\Users\\antwan.maddox\\AppData\\Local\\Android\\Sdk;C:\\Users\\antwan.maddox\\AppData\\Local\\Android\\Sdk\\platforms;C:\\Users\\antwan.maddox\\AppData\\Local\\Android\\Sdk\\platform-tools;C:\\Program Files\\apache-maven-3.6.3-bin\\apache-maven-3.6.3\\bin;C:\\Windows\\System32;C:\\WINDOWS\\system32;C:\\WINDOWS;C:\\WINDOWS\\System32\\Wbem;C:\\WINDOWS\\System32\\WindowsPowerShell\\v1.0\\;C:\\WINDOWS\\System32\\OpenSSH\\;C:\\Program Files (x86)\\Microsoft Emulator Manager\\1.0\\;C:\\Program Files\\nodejs\\;C:\\Program Files (x86)\\scala\\bin;C:\\Program Files\\Python\\Python39;C:\\Program Files\\Python\\Python39\\Scripts;c:\\users\\antwan.maddox\\appdata\\local\\programs\\python\\python39\\Scripts;C:\\flutttersdk\\flutter\\bin;C:\\Android\\platform-tools;C:\\Program Files\\Git\\cmd;C:\\Program Files\\Microsoft SQL Server\\150\\Tools\\Binn\\;C:\\Program Files (x86)\\dotnet\\;C:\\ProgramData\\chocolatey\\bin;C:\\Program Files (x86)\\CMake\\bin;C:\\Users\\antwan.maddox\\AppData\\Local\\Programs\\Python\\Python310\\Scripts\\;C:\\Users\\antwan.maddox\\AppData\\Local\\Programs\\Python\\Python310\\;C:\\Users\\antwan.maddox\\scoop\\shims;C:\\Program Files\\MySQL\\MySQL Shell 8.0\\bin\\;C:\\Program Files\\Java\\jdk-13.0.1\\bin;C:\\Users\\antwan.maddox\\AppData\\Local\\Programs\\Microsoft VS Code\\bin;C:\\Users\\antwan.maddox\\.dotnet\\tools;C:\\Program Files\\nodejs;C:\\Windows\\System32;C:\\Program Files\\Git\\bin\\git.exe;C:\\Program Files\\Git\\cmd;C:\\WINDOWS\\System32\\WindowsPowerShell\\v1.0\\;C:\\flutter\\flutter_windows_1.20.1-stable\\flutter\\bin;C:\\Users\\antwan.maddox\\AppData\\Local\\Programs\\Fiddler;C:\\Users\\antwan.maddox\\.dotnet\\tools;C:\\Users\\antwan.maddox\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Users\\antwan.maddox\\AppData\\Roaming\\npm;C:\\Users\\antwan.maddox\\AppData\\Local\\rasjani\\WebDriverManager\\chrome\\89.0.4389.23\\chromedriver_win32\\;search-ms:displayname=Search%20Results%20in%20Downloads" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "C:\\Program Files (x86)\\Android\\android-sdk\\");

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                ///TODO: Get node installation path. Type 'where node' using commandline
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                ///TODO: Get path of the main.js location from the appium installation
                .withAppiumJS(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
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
