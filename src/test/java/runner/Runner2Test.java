package runner;

import Utils.*;
import com.cucumber.listener.Reporter;
import io.cucumber.testng.CucumberOptions;
import org.junit.AfterClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;


@CucumberOptions(features="src/test/resources/features"
        ,glue= {"definitions"}
        /*,tags = {"@foo","not@bar"}*/
         ,plugin= {"pretty","html:target/cucumber-html-report.html","json:target/cucumber.json"}
       /* ,plugin = { "pretty","html:target/cucumber","summary"}*/
        ,snippets = CAMELCASE
        ,strict = true
        ,dryRun = false
        ,monochrome = true)
public class Runner2Test extends RunnerBase{

    @BeforeSuite
    public void setUpEnvironment() throws Exception {

    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        Reporter.loadXMLConfig(new File(ConfigFileReader.getReportConfigPath("Android.properties")));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
    }



}
