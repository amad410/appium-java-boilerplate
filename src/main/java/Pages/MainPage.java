package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MainPage extends BasePage{

    @AndroidFindBy(id = "foobar")
    @iOSXCUITFindBy(accessibility = "foo-bar")
    MobileElement fooBarBtn;

    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickBtn(){
        click(fooBarBtn,20);
    }


}
