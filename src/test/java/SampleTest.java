import Utils.ConfigFileReader;
import org.testng.annotations.Test;

import java.io.IOException;


public class SampleTest {

    @Test
    public void sampleTest() throws IOException {
        ConfigFileReader.getReportConfigPath("configs/Android.properties");
        System.out.println("test");

    }
}
