package Android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Sample_Android_WeatherAppDemo {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platformVersion","11");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("app",System.getProperty("user.dir")+"/apps/weather-app-debug.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }

    @Test
    public void click_App_Button(){
        //code
       SoftAssert softAssert = new SoftAssert();
        driver.findElementById("com.weatherapp:id/spinner").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[4]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[5]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[6]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[7]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();

        driver.findElementById("com.weatherapp:id/spinner").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[8]").click();
        driver.findElementById("com.weatherapp:id/btn_view_weather").click();




    /*
        MobileElement el7 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_temperature");
        el7.click();
        MobileElement el8 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[3]");
        el8.click();
        MobileElement el9 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_date_time");
        el9.click();
        MobileElement el10 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_weather_condition");
        el10.click();
        MobileElement el11 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_humidity_value");
        el11.click();
        MobileElement el12 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_pressure_value");
        el12.click();
        MobileElement el13 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_visibility_value");
        el13.click();
        MobileElement el14 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_sunrise_time");
        el14.click();
        MobileElement el15 = (MobileElement) driver.findElementById("com.weatherapp:id/tv_sunset_time");
        el15.click();
    */

    }

    @AfterTest
    public void tearDown(){
        if(null != driver)
        {
            driver.quit();
        }
    }
}
