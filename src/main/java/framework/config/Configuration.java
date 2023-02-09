package framework.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {
    public static String getURL() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/config.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("URL");
        return dataSet.getAsString();
    }
}
