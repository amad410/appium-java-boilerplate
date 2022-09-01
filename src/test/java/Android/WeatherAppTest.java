package Android;

import Base.BaseTest;
import Pages.MainPage;
import Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.eclipse.sisu.inject.Soft;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.jq.Main;

public class WeatherAppTest {

    public static BaseTest base = new BaseTest();
   // protected static ThreadLocal<AppiumDriver> _driver = new ThreadLocal<AppiumDriver>();
    InputStream inputStream;
    private static EnhancedAndroidDriver<MobileElement> _edriver;
    private static SoftAssert _testAssert;
    MainPage mainPage;

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @BeforeClass
    public static void SetupState() throws Exception {
        base.beforeSuite("Android");
        _testAssert = new SoftAssert();

    }

    @Before
    public void SetupTest() throws Exception {
        //AppiumDriver driver;
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

        _edriver = Factory.createAndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }
    @Test
    public void verifyMainView(){

        String titleBarText = _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView").getText();
        _testAssert.assertTrue(titleBarText.equals("Weather App MVVM Dagger"));
    }
    @Test
    public void Verify_London_Weather() throws InterruptedException {

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();

        Thread.sleep(5000);

        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_temperature").isDisplayed(),"Temperature not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_weather_condition").isDisplayed(), "Weather condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_humidity_value").isDisplayed(), "Humidity condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_pressure_value").isDisplayed(),"Pressure not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_visibility_value").isDisplayed(),"Visibility not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunrise_time").isDisplayed(),"Time to sunrise not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunset_time").isDisplayed(),"Time to sunset not displayed");
       _testAssert.assertAll();

    }
    @Test
    public void Verify_Riyadh_Weather() throws InterruptedException {

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();
        Thread.sleep(5000);

        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_temperature").isDisplayed(),"Temperature not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_weather_condition").isDisplayed(), "Weather condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_humidity_value").isDisplayed(), "Humidity condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_pressure_value").isDisplayed(),"Pressure not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_visibility_value").isDisplayed(),"Visibility not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunrise_time").isDisplayed(),"Time to sunrise not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunset_time").isDisplayed(),"Time to sunset not displayed");
        _testAssert.assertAll();

    }
    @Test
    public void Verify_AbuDhabi_Weather() throws InterruptedException {

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]").click();
        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();
        Thread.sleep(5000);

        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_temperature").isDisplayed(),"Temperature not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_weather_condition").isDisplayed(), "Weather condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_humidity_value").isDisplayed(), "Humidity condition not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_pressure_value").isDisplayed(),"Pressure not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_visibility_value").isDisplayed(),"Visibility not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunrise_time").isDisplayed(),"Time to sunrise not displayed");
        _testAssert.assertTrue(_edriver.findElementById("com.weatherapp:id/tv_sunset_time").isDisplayed(),"Time to sunset not displayed");

        _testAssert.assertAll();

    }

    @AfterClass
    public static void TearDownSetup(){
        base.afterSuite();
    }

    @After
    public void TearDownDriver(){
        if(_edriver != null){
            _edriver.label("Stopping App");
            ((InteractsWithApps)_edriver).closeApp();
            _edriver.quit();
        }
    }

    /*public AppiumDriver getDriver() {
        return _driver.get();
    }*/

    /*public void setDriver(AppiumDriver driver) {
        _driver.set(driver);
    }*/
}
