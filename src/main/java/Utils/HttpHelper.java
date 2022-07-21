package Utils;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

public class HttpHelper {

    public static String uploadApp(String uploadUrl, String apkPath) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post(uploadUrl)
                .header("Authorization", "")
                .field("file", new File(apkPath))
                .asString();
        return response.getBody();
    }




}
