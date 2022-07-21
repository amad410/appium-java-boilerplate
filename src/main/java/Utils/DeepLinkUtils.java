package Utils;

import Base.BaseTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class DeepLinkUtils {

    public static void openAndroidAppWith(String url, String packageName){
        BaseTest base = new BaseTest();

        HashMap<String, String> deepUrl = new HashMap<>();
        deepUrl.put("url", url);
        deepUrl.put("package", packageName);
        base.getDriver().executeScript("mobile:deepLink", deepUrl);

    }
    public static void openiOSAppWith(String url, String bundleId){
        BaseTest base = new BaseTest();

        By urlBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'URL'");
        By urlFld = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && name CONTAINS 'URL'");
        By openBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'Open'");
        base.getDriver().activateApp(bundleId);
        WebDriverWait wait = new WebDriverWait(base.getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlFld)).sendKeys("" + url + "\uE007");
        wait.until(ExpectedConditions.visibilityOfElementLocated(openBtn)).click();


    }
}
