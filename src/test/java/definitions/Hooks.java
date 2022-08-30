package definitions;

import Utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;


public class Hooks {

    @After
    public void quit(Scenario scenario){
        if(scenario.isFailed())
        {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
        }
    }

}
