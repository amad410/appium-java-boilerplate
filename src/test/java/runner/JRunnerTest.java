package runner;


import Utils.*;
import com.cucumber.listener.Reporter;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;

import java.io.File;
import java.io.IOException;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/Sample.feature"
        ,glue= {"definitions"}
        /*,tags = {"@foo","not@bar"}*/
        ,plugin= {"pretty","html:target/cucumber-html-report.html"}
        /* ,plugin = { "pretty","html:target/cucumber","summary"}*/
        ,dryRun = false
        ,tags = "@junit"
        ,monochrome = true)
public class JRunnerTest {
    private static String propFileName;
    private static ServerManager _serverManager;

    @BeforeClass
    public static void setup() throws Exception {
        System.setProperty("platformName","Android");
        ThreadContext.put("ROUTINGKEY", System.getProperty("platformName"));
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams(System.getProperty("platformName"));
        _serverManager = new ServerManager();

        _serverManager.startServer(System.getProperty("platformName"));

        PropertyManager props = new PropertyManager();
        switch(System.getProperty("platformName")) {
            case "Android":
                propFileName = System.getProperty("user.dir")+"/src/main/resources/configs/Android.properties";
                break;
            case "iOS":
                propFileName = System.getProperty("user.dir")+"/src/main/resources/configs/iOS.properties";
                break;
            default:
                throw new Exception("Invalid platform! to start server - " + System.getProperty("platformName"));
        }
        new DriverManager().initializeDriver(params,propFileName);

    }
    @AfterClass
    public static void writeExtentReport() throws IOException {
        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);

        }
        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null)
        {
            serverManager.getServer().stop();
        }
    }



}
