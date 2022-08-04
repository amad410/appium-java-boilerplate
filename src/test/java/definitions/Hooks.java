package definitions;

import Utils.DriverManager;
import Utils.GlobalParams;
import Utils.PropertyManager;
import Utils.ServerManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.jmx.Server;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Parameters;

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
