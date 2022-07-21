package Android;

import Utils.JsonReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.messages.internal.com.google.gson.JsonParser;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample_Android_BrowserStack {
    ///TODO:Move to DriverManager
    //private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    AppiumDriver driver;

    public static String userName = "{username}}";
    public static String accessKey = "{password}";
    // Rest Url to upload app to BrowserStack
    //public static String upploadUrl = "";

    @BeforeTest
    public void setUp() throws MalformedURLException {
        //Uploading app programmatically to BrowserStack
        // JSONObject appurlObj = new JSONObject(HttpHelper.uploadApp(upploadUrl,System.getProperty("user.dir")+"/apps/<apk>"));
        JSONObject deviceObj = new JSONObject(JsonReader.parse(System.getProperty("user.dir")+"/src/main/resources/configs/browserStack.json")).getJSONObject("1");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device", deviceObj.getString("device"));
        caps.setCapability("os_version", deviceObj.getString("os_version"));
        caps.setCapability("project",deviceObj.getString("project"));
        caps.setCapability("build", deviceObj.getString("build"));
        caps.setCapability("name", deviceObj.getString("name"));
        ///TODO: Paste the url you got after uploading the app to browserstack using Rest
        caps.setCapability("app", deviceObj.getString("app_url"));
        //Retrieving the app from HttpUploaded Response
        //caps.setCapability("app", appurlObj.getString("app_url"));
        URL url = new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");
        driver = new AndroidDriver(url,caps);
        ///TODO:Move to DriverManager
        //setDriver(driver)
    }


    @Test
    public void click_App_Button(){
        //code

    }

    @AfterTest
    public void tearDown(){
        if(null != driver)
        {
            driver.quit();
        }
    }
}
