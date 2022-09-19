package iOS;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_iOS_SauceLabs {
    WebDriver _driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        /**
         * These recommended approach to manage these credentials is to use
         * Environment variables
         */
        String SAUCE_USER ="";
        String SAUCE_ACCESSKEY ="";
        String SAUCE_URL ="";


        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("appium:deviceName","Android GoogleAPI Emulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion","12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:app", "storage:filename=weather-app-debug.apk");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", "1.0");
        sauceOptions.setCapability("name", "weather-sample-test");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://" + SAUCE_USER + ":" + SAUCE_ACCESSKEY + SAUCE_URL + "/wd/hub");
        _driver = new IOSDriver<>( url, caps);

    }

    @Test
    public void verifyLondonTempDisplayed() throws InterruptedException {
        //code
        WebElement dropDown = _driver.findElement(By.id("com.weatherapp:id/spinner"));
        dropDown.click();
        WebElement londonSelection = _driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]"));
        londonSelection.click();

        WebElement weatherBtn = _driver.findElement(By.id("com.weatherapp:id/btn_view_weather"));
        weatherBtn.click();
        Thread.sleep(5000);

        WebElement tempDisplay = _driver.findElement(By.id("com.weatherapp:id/tv_temperature"));
        Assert.isTrue(tempDisplay.isDisplayed(),"Temperature not displayed");
    }

    @AfterTest
    public void tearDown(){
        if(null != _driver)
        {
            _driver.quit();
        }
    }
}
