package project.tests;

import framework.config.Configuration;
import framework.utils.ApiUtils;
import project.DProvider;
import project.Endpoints;
import framework.utils.JSONUtils;
import org.apache.http.HttpStatus;
import project.ProjectApiMethods;
import project.models.Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GetPostByIdTest {
    private static final int ID = 99;

    @Test(dataProvider = "testPostData",dataProviderClass = DProvider.class)
    public static void getPostByIdTest(Post post) throws FileNotFoundException {

        Response response = ProjectApiMethods.getPostById(ID);
        String msg = String.format("Статус код %s",response.getStatusCode());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, msg);
        Post postFromResponse = ProjectApiMethods.getPost(JSONUtils.getJson(response));

        Assert.assertEquals(post.getId(), postFromResponse.getId(),"Id в ответе не совпадает с тестовым");
        Assert.assertEquals(post.getUserId(),postFromResponse.getUserId(),"UserId не совпадает с тестовым.");
        Assert.assertFalse(post.getBody().isEmpty(),"Поле 'Body' пусто.");
        Assert.assertFalse(post.getTitle().isEmpty(),"Поле 'Title' пусто.");
    }
}
