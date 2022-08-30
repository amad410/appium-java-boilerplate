package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Property Manager class for the loading of configuration properties.
 */
public class PropertyManager {

    private static Properties props = new Properties();
    TestUtils utils = new TestUtils();

    public Properties getProps(String propertyFileName) throws IOException {
        InputStream is = null;
        String propsFileName = propertyFileName;

        if(props.isEmpty())
        {
            try{
                utils.log().info("loading config properties");
                is = new FileInputStream(propertyFileName);
                props.load(is);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                utils.log().fatal("Failed to load config properties. Abort!!!" + e.toString());
                throw e;
            }
            finally {
                if(is != null){
                    is.close();
                }
            }
        }
        return props;
    }
}
