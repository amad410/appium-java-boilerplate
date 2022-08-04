package runner;

import Utils.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

import java.io.IOException;

public class RunnerBase {

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();
    private String propFileName;
    private ServerManager _serverManager;
    public static TestNGCucumberRunner getRunner(){
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1)
    {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({"platformName"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String platformName) throws Exception {
        ThreadContext.put("ROUTINGKEY", platformName);
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams(platformName);
        _serverManager = new ServerManager();

        _serverManager.startServer(platformName);

        PropertyManager props = new PropertyManager();
        switch(platformName) {
            case "Android":
                propFileName = System.getProperty("user.dir")+"/src/main/resources/configs/Android.properties";
                break;
            case "iOS":
                propFileName = System.getProperty("user.dir")+"/src/main/resources/configs/iOS.properties";
                break;
            default:
                throw new Exception("Invalid platform! to start server - " + platformName);
        }
        new DriverManager().initializeDriver(params,propFileName);
        setRunner(new TestNGCucumberRunner(this.getClass()));

    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios",dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature){
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios(){
        return getRunner().provideScenarios();
    }

    @AfterClass
    public static void quit() throws IOException {
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
