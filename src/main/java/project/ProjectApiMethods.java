package project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import framework.Constants;
import framework.config.Configuration;
import framework.utils.ApiUtils;
import framework.utils.RandomUtils;
import io.restassured.response.Response;
import project.models.Post;
import project.models.userModels.User;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProjectApiMethods {
    public static User getUser(String json) {
        return new Gson().fromJson(json, User.class);
    }

    public static Post getPost(String json) {
        return new Gson().fromJson(json, Post.class);
    }
    public static Response getAllUsers() throws FileNotFoundException {
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_ALL_USERS);
        return ApiUtils.get(URL);
    }
    public static Response getAllPosts() throws FileNotFoundException {
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_ALL_POSTS);
        return ApiUtils.get(URL);
    }
    public static Response postPost(Post post) throws FileNotFoundException {
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.POST_POST);
        return ApiUtils.post(URL, post.toJson());
    }
    public static Response getUserById(int id) throws FileNotFoundException {
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_USER_BY_ID,id);
        return ApiUtils.get(URL);
    }
    public static Response getPostById(int id) throws FileNotFoundException {
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_POST_BY_ID,id);
        return ApiUtils.get(URL);
    }

    public static List<User> getUserList(String json) {
        Type listOfMyClassObject = new TypeToken<ArrayList<User>>() {
        }.getType();
        return new Gson().fromJson(json, listOfMyClassObject);
    }

    public static List<Post> getPostList(String json) {
        Type listOfMyClassObject = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return new Gson().fromJson(json, listOfMyClassObject);
    }

    public static Post getRandomPost(int userId) {
        return new Post(userId, RandomUtils.getRandomInt(),
                RandomUtils.getRandomString(Constants.RANDOM_STRING_SIZE),
                RandomUtils.getRandomString(Constants.RANDOM_STRING_SIZE));
    }

    public static User getById(List<User> listOfUsers, int id) {
        for (User user : listOfUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static boolean areRightSorted(List<Post> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).getId() > data.get(i + 1).getId()) {
                return false;
            }
        }
    return true;
    }

    public static String getURL(String URL,String endpoint){
        return String.format("%s%s", URL, endpoint);
    }
    public static String getURL(String URL,String endpoint,int id) {
        String endpointByID= String.format(endpoint,id);
        return String.format("%s%s", URL, endpointByID);
    }
}
