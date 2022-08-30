package Utils;

/**
 * This is the GlobalParams class to set all the global
 * parameters for the system values.
 */
public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<String>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<String>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<String>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<String>();

    public void setPlatformName(String platformName1){
        platformName.set(platformName1);
    }

    public String getPlatformName(){
        return platformName.get();
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort2) {
        systemPort.set(systemPort2);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort2) {
        chromeDriverPort.set(chromeDriverPort2);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort2) {
        wdaLocalPort.set(wdaLocalPort2);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort2) {
        webkitDebugProxyPort.set(webkitDebugProxyPort2);
    }

    /**
     * This will initialize the global parameters. You can either set these values using
     * System.getProperty during runtime execution using commandline or testNG and then set
     * them using the methods outlined above or have static default values.
     * @param platformName
     * @throws Exception
     */

    public void initializeGlobalParams(String platformName) throws Exception {
        try{
            GlobalParams params = new GlobalParams();

            switch(platformName){
                case "Android":
                    params.setPlatformName(System.getProperty("platformName", "Android"));
                    params.setSystemPort(System.getProperty("systemPort", "10000"));
                    params.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                    break;
                case "iOS":
                    params.setPlatformName(System.getProperty("platformName", platformName));
                    params.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                    params.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                    break;
                default:
                    throw new IllegalStateException("Invalid Platform Name!");
            }
        }
        catch(Exception ex){
            throw new Exception(String.format("Exception occured: %s",ex.getMessage()));
        }
    }
}
