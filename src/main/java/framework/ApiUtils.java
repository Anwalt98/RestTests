package framework;

import framework.models.Post;
import framework.models.userModels.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class ApiUtils {

    public static Response get(String baseURL){
        return RestAssured.get(baseURL);
    }

    public static boolean isContentTypeJson(Response response){
        return response.getContentType().contains("application/json");
    }

    public static Response get(String baseURL, Integer id){
        String URL = String.format("%s%s%s",baseURL,"/",id);
        return RestAssured.get(URL);
    }

    public static String getJson(Response response){
        return response.body().asString();
    }

    public static Post getPost(String json){
        return new Gson().fromJson(json, Post.class);
    }

    public static User getUser(String json){
        return new Gson().fromJson(json, User.class);
    }


    public static List<Post> getPostList(String json){
        Type listOfMyClassObject = new TypeToken<ArrayList<Post>>() {}.getType();
        return new Gson().fromJson(json, listOfMyClassObject);
    }

    public static List<User> getUserList(String json){
        Type listOfMyClassObject = new TypeToken<ArrayList<User>>() {}.getType();
        return new Gson().fromJson(json, listOfMyClassObject);
    }

    public static boolean isJsonEmpty(Response response){
        return getJson(response).equals("{}");
    }

    public static Response postModel(String url, Post post){
        String jsonString = String.format(
                "{\"userId\":\"%s\",\"id\":\"%s\",\"title\":\"%s\",\"body\":\"%s\"}",
                post.getUserId(),
                post.getId(),
                post.getTitle(),
                post.getBody());

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString);
        return requestSpecification.post();
    }

    public static boolean isStatusCode200(Response response){
        return (response.statusCode() == HttpStatus.SC_OK);
    }

    public static boolean isStatusCode404(Response response){
        return (response.statusCode() == HttpStatus.SC_NOT_FOUND);
    }

    public static boolean isStatusCode201(Response response){
        return (response.statusCode() == HttpStatus.SC_CREATED);
    }
}
