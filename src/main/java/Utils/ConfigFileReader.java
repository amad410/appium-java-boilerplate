package Utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties properties;
    private static String configDirectory =  System.getProperty("user.dir") + "/src/main/resources/configs/";

    public static String getReportConfigPath(String fileName) throws IOException {
        properties = PropertyUtils.readPropertiesFile(configDirectory + fileName);
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
