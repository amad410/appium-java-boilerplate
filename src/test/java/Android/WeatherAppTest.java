package Android;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class WeatherAppTest {

    public static BaseTest base = new BaseTest();
    protected static ThreadLocal<AppiumDriver> _driver = new ThreadLocal<AppiumDriver>();
    InputStream inputStream;

    @BeforeClass
    public static void SetupState() throws Exception {
        base.beforeSuite("Android");

    }

    @Before
    public void SetupTest() throws Exception {
        AppiumDriver driver;
        Properties prop = new Properties();
        String propFileName = System.getProperty("user.dir")+"/src/main/resources/configs/AndroidDemo.properties";


        inputStream = new FileInputStream(propFileName);
        base.setProps(prop);
        base.getProps().get().load(inputStream);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName",  base.getProps().get().getProperty("platformName"));
        capabilities.setCapability("platformVersion",  base.getProps().get().getProperty("platformVersion"));
        capabilities.setCapability("deviceName",  base.getProps().get().getProperty("deviceName"));
        capabilities.setCapability("app",
                System.getProperty("user.dir") +  base.getProps().get().getProperty("app"));
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        setDriver(driver);

    }
    //@Test
    public void verifyMainView(){
        AppiumDriver driver = getDriver();
        String titleBarText = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView").getText();


        Assert.assertTrue(titleBarText.equals("Weather App MVVM Dagger"));


    }
    @Test
    public void Verify_London_Weather(){
        AppiumDriver driver = getDriver();
        driver.findElementById("com.weatherapp:id/spinner").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();
    }
    @Test
    public void Verify_Riyadh_Weather(){
        AppiumDriver driver = getDriver();
        driver.findElementById("com.weatherapp:id/spinner").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();



    }
    //@Test
    public void Verify_AbuDhabi_Weather(){
        AppiumDriver driver = getDriver();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[4]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[5]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[6]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[7]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[8]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

    }

    @AfterClass
    public static void TearDownSetup(){
        base.afterSuite();
    }

    @After
    public void TearDownDriver(){
        base.tearDown();
    }

    public AppiumDriver getDriver() {
        return _driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        _driver.set(driver);
    }
}
