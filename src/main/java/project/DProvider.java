package project;

import project.models.Post;
import project.models.userModels.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DProvider {

    @DataProvider(name = "testUserData")
    public Object[][] getData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/testData/getUserByIdTestData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<User> testData = new Gson().fromJson(dataSet, new TypeToken<List<User>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @DataProvider(name = "testPostData")
    public Object[][] getPostData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/testData/getPostByIdTestData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<Post> testData = new Gson().fromJson(dataSet, new TypeToken<List<Post>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
