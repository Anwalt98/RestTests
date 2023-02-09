package framework.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class JSONUtils {

    public static String getJson(Response response){
        return response.body().asString();
    }

    public static boolean isJsonEmpty(Response response){
        return getJson(response).equals("{}");
    }

    public static boolean isContentTypeJson(Response response){
        try {
            JsonArray jsonArray = new JsonParser().parse(response.body().asString()).getAsJsonArray();
                return true;
        }
        catch (IllegalStateException illegalStateException){
            try {
                JsonObject jsonObject = new JsonParser().parse(response.body().asString()).getAsJsonObject();
                return true;
            }
            catch (IllegalStateException illegalStateException2){
                return false;
            }
        }
    }
}
