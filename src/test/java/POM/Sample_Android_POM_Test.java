package POM;

import Base.BaseTest;
import Pages.MainPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Sample_Android_POM_Test extends BaseTest {

    MainPage mainPage;

    @Test
    public void sampleTest() throws MalformedURLException {
        //add test code here
        mainPage = new MainPage(getDriver());
        mainPage.clickBtn();
    }
}
