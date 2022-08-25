package Android;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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

public class WeatherAppTest {

    public static BaseTest base = new BaseTest();
   // protected static ThreadLocal<AppiumDriver> _driver = new ThreadLocal<AppiumDriver>();
    InputStream inputStream;
    private static EnhancedAndroidDriver<MobileElement> _edriver;

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @BeforeClass
    public static void SetupState() throws Exception {
        base.beforeSuite("Android");

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
        //_edriver = Factory.createAndroidDriver(new URL("https://appcenter.ms/users/john-dixon-15t9/apps/AndroidWeather/distribute/releases/2"), capabilities);
        //driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        //setDriver(driver);

    }
    @Test
    public void verifyMainView(){

        String titleBarText = _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView").getText();


        Assert.assertTrue(titleBarText.equals("Weather App MVVM Dagger"));


    }
    @Test
    public void Verify_London_Weather(){

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();
    }
    @Test
    public void Verify_Riyadh_Weather(){

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();



    }
    //@Test
    public void Verify_AbuDhabi_Weather(){

        _edriver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();
        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[4]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[5]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[6]").click();
        //driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[7]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        _edriver.findElementById("com.weatherapp:id/spinner").click();

        _edriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[8]").click();
        // driver.findElementById("com.weatherapp:id/btn_view_weather").click();

    }

    @AfterClass
    public static void TearDownSetup(){
        base.afterSuite();
    }

    @After
    public void TearDownDriver(){
        if(_edriver != null){
            _edriver.label("Stopping App");
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
