package runner;
import Base.BaseTest;
import Utils.ConfigFileReader;
import com.cucumber.listener.Reporter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

//@RunWith(Cucumber.class)
//@CucumberOptions(features="src/test/resources/FeatureFiles",tags="@feature1scenariogroup1,@feature2cenariogroup2"
@CucumberOptions(features="src/test/resources/features/"
        ,glue= {"definitions"}
       /* ,plugin= {"pretty","html:target/cucumber-html-report.html"}*/
        ,plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/bdd-report.html"})
public class RunnerTest extends AbstractTestNGCucumberTests {

    BaseTest baseTest = new BaseTest();

    @BeforeSuite
    public void setUpEnvironment() throws Exception {
        baseTest.beforeSuite();
    }

    @Parameters({ "platformName" })
    @BeforeTest
    public void setUpScenario(String platform) throws Exception {
        baseTest.beforeTest(platform);
    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        Reporter.loadXMLConfig(new File(ConfigFileReader.getReportConfigPath("configs/Android.properties")));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
    }


}