package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import java.time.Duration;

public class WaitUtils {

    public static void waitForVisibility(WebElement e, AppiumDriver driver, long duration){
        Wait<AppiumDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(duration))
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public static void waitForVisibility(MobileElement element, AppiumDriver driver, long timeOut){
        Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(java.util.NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
