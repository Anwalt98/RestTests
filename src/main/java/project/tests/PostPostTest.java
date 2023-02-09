package project.tests;

import framework.config.Configuration;
import framework.utils.ApiUtils;
import project.Endpoints;
import framework.utils.JSONUtils;
import org.apache.http.HttpStatus;
import project.ProjectApiMethods;
import project.models.Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class PostPostTest {
    private static final Integer USER_ID = 1;

    @Test
    public static void postPostTest() throws FileNotFoundException {

        Post postFromData = ProjectApiMethods.getRandomPost(USER_ID);
        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.POST_POST);

        Response response = ApiUtils.post(URL, postFromData.toJson());

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED, msgStatusCode);
        Post postFromResponse = ProjectApiMethods.getPost(JSONUtils.getJson(response));

        String titlesNotEqualMsg = String.format("Title для отправки: %s, из ответа: %s.",postFromData.getTitle(),postFromResponse.getTitle());
        String userIdNotEqualMsg = String.format("UserId для отправки: %s, из ответа: %s.",postFromData.getUserId(),postFromResponse.getUserId());
        String bodiesNotEqualMsg = String.format("Body для отправки: %s, из ответа: %s.",postFromData.getBody(),postFromResponse.getBody());

        Assert.assertEquals(postFromData.getTitle(), postFromResponse.getTitle(),titlesNotEqualMsg);
        Assert.assertEquals(postFromData.getUserId(), postFromResponse.getUserId(),userIdNotEqualMsg);
        Assert.assertEquals(postFromData.getBody(), postFromResponse.getBody(),bodiesNotEqualMsg);
        Assert.assertNotNull((Integer) postFromResponse.getId(),"Значение id в ответе null.");
    }
}
