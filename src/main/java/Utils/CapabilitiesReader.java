package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CapabilitiesReader {
    public static JSONArray getJSON(String JSON_path) throws IOException, ParseException {
        Object object = new JSONParser().parse(new FileReader(JSON_path));
        return (JSONArray) object;
    }
    private static JSONObject getCapability(String capabilityName, String JSON_path) throws IOException, ParseException {
        JSONArray capabilitiesArray = getJSON(JSON_path);
        for (Object jsonObject : capabilitiesArray)
        {
            JSONObject capability = (JSONObject) jsonObject;
            if(capability.get("name").toString().equalsIgnoreCase(capabilityName))
            {
                return (JSONObject) capability.get("caps");
            }
        }
        return null;
    }
    private static HashMap<String, Object> convertCapsToHashMap(String capabilityName, String JSON_path) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCapability(capabilityName,JSON_path).toString(),HashMap.class);
    }

    public static DesiredCapabilities getDesiredCapabilities(String capabilityName, String capsContentRootLocation) throws IOException, ParseException {
        String jsonLocation = System.getProperty("user.dir") + "/" + capsContentRootLocation;
        HashMap<String, Object> caps = convertCapsToHashMap(capabilityName,jsonLocation);
        return new DesiredCapabilities(caps);
    }
}
