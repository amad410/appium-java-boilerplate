package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MainPage extends BasePage{

    /*@AndroidFindBy(accessibility = "App")
    @iOSXCUITFindBy(accessibility = "foo-bar")
    MobileElement fooBarBtn;*/

    @AndroidFindBy(accessibility = "Animation")
    @iOSXCUITFindBy(accessibility = "Animation")
    MobileElement tab;

    @AndroidFindBy(id = "com.weatherapp:id/spinner")
    @iOSXCUITFindBy(id = "com.weatherapp:id/spinner")
    MobileElement weatherBtn;

    public MainPage() {
    }

    public void clickBtn(){
        click(weatherBtn,20);
    }

    public void clickTab(){
        click(tab,20);
    }
    public void closeApp(){
        closeApplication();
    }



}
