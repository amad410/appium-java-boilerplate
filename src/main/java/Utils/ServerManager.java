package Utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> _server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer(){
        return _server.get();
    }
    public void startServer(String platformName) throws Exception {
        utils.log().info("starting appium server");

        try{
            if(!checkIfAppiumServerIsRunning(4723)) {
                switch(platformName) {
                    case "Android":
                        AppiumDriverLocalService winServer = WindowsGetAppiumService();
                        winServer.start();
                        winServer.clearOutPutStreams();
                        this._server.set(winServer);
                        break;
                    case "iOS":
                        AppiumDriverLocalService macServer = MACGetAppiumService();
                        macServer.start();
                        macServer.clearOutPutStreams();
                        this._server.set(macServer);
                        break;
                    default:
                        throw new Exception("Invalid platform! - " + platformName);
                }
                utils.log().info("Appium server started");
            } else {
                utils.log().info("Appium server already running");
            }

            utils.log().info("Appium server started");

        }
        catch(Exception ex){
            if(this._server == null)
                utils.log().fatal("Appium server not started. Abort!!!");
                throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. Abort!!!");
        }
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService WindowsGetAppiumService() {
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildDefaultService();

        /*return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(params.getPlatformName() + "_"
                        + File.separator + "Server.log")));*/
    }

    public AppiumDriverLocalService MACGetAppiumService() {
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<String, String>();
        ///TODO: Get PATH
        environment.put("PATH", "/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin:/Users/Om/Library/Android/sdk/tools:/Users/Om/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin" + System.getenv("PATH"));
        ///TODO: Get ANDROID_HOME
        environment.put("ANDROID_HOME", "{android home}");
        ///TODO: Get JAVA_HOME
        environment.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(params.getPlatformName() + "_"
                        + File.separator + "Server.log")));
    }
    public boolean checkIfAppiumServerIsRunning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            System.out.println("1");
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }
}
