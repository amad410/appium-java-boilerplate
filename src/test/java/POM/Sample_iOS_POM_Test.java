package POM;

import Base.BaseTest;
import Pages.MainPage;
import Reports.Report;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Sample_iOS_POM_Test extends BaseTest {

    MainPage mainPage;

    @Test
    public void sampleTest() throws MalformedURLException {
        //add test code here
        mainPage = new MainPage();
        mainPage.clickBtn();

    }
}
