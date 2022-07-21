package Pages;

import Reports.Report;
import Utils.TestUtils;
import Utils.WaitUtils;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class BasePage {

    AppiumDriver _driver;
    public AndroidTouchAction actions;
    TestUtils utils = new TestUtils();

    public BasePage(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this._driver = driver;
    }

    public void switchToWebView(){
        Set<String> contexts = _driver.getContextHandles();
        for (String context: contexts){
            if (context.contains("WEBVIEW")){
                _driver.context(context);
                break;
            }
        }
    }

    public void clear(MobileElement element, long timeOut){
        WaitUtils.waitForVisibility(element,_driver,timeOut);
        element.clear();
    }

    public void click(MobileElement element, long timeOut){
        WaitUtils.waitForVisibility(element,_driver,timeOut);
        element.click();
    }
    public void enterText(MobileElement element, String text, long timeOut){
        WaitUtils.waitForVisibility(element,_driver,timeOut);
        element.clear();
        element.sendKeys(text);
    }

    public String getAttribute(MobileElement element, String attribute, long timeOut){
        WaitUtils.waitForVisibility(element,_driver,timeOut);
        return element.getAttribute(attribute);
    }
    private void scrollDown(double startPosition, double endPosition, long waitDuration){
        Dimension dimension = this._driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * startPosition);
        int scrollEnd = (int) (dimension.getHeight() * endPosition);

        actions = new AndroidTouchAction(this._driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(waitDuration)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }
    public void scrollAndClick(MobileElement elementToClick, double startPosition, double endPosition, long waitDuration){

        actions = new AndroidTouchAction(this._driver);

        //ScrollDown
        scrollDown(startPosition, endPosition, waitDuration);

        actions.tap(ElementOption.element(elementToClick)).perform();
    }
    public void tap(MobileElement element){
        actions = new AndroidTouchAction(this._driver);
        actions.tap(ElementOption.element(element)).perform();
    }
    public void dragDrop(String dragPointId, String dropPointId){

       actions = new AndroidTouchAction(this._driver);

        AndroidElement drag = (AndroidElement) this._driver.findElement(By.id(dragPointId));
        AndroidElement drop = (AndroidElement) this._driver.findElement(By.id(dropPointId));

        actions.longPress(ElementOption.element(drag))
                .waitAction().moveTo(ElementOption.element(drop))
                .release()
                .perform();
    }
    public void swipe(MobileElement startingElem, int xOffset, int yOffset){

        actions = new AndroidTouchAction(this._driver);

        actions.press(ElementOption.element(startingElem))
                .waitAction()
                .moveTo(PointOption.point(xOffset, yOffset))
                .release()
                .perform();
    }

   public String androidGetText(MobileElement e, String msg, long waitDuration) {
        String txt = null;

       txt = getAttribute(e, "text",waitDuration);
        utils.log().info(msg + txt);
        Report.getTest().log(Status.INFO, msg + txt);
        return txt;
    }

    public String iOSGetText(MobileElement e, String msg, long waitDuration) {
        String txt = null;
        txt = getAttribute(e, "label",waitDuration);

        utils.log().info(msg + txt);
        Report.getTest().log(Status.INFO, msg + txt);
        return txt;
    }

    public MobileElement androidScrollToElement() {
        return (MobileElement) ((FindsByAndroidUIAutomator) _driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));");
    }

    /*public void iOSScrollToElement() {
        RemoteWebElement element = (RemoteWebElement)_driver.findElement(By.name("test-ADD TO CART"));
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        _driver.executeScript("mobile:scroll", scrollObject);
    }*/

}
