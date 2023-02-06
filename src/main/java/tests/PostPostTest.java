package tests;

import framework.utils.ApiUtils;
import framework.models.Post;
import framework.utils.Constants;
import framework.utils.ModelUtils;
import framework.utils.RandomUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class PostPostTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private static final Integer USER_ID = 1;

    @Test
    public static void postPostTest() {
        Post postFromData = ModelUtils.getRandomPost(USER_ID);
        Response response = ApiUtils.postModel(URL, postFromData);

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());

        Assert.assertTrue(ApiUtils.isStatusCode201(response),msgStatusCode);
        Post postFromResponse = ApiUtils.getPost(ApiUtils.getJson(response));

        String titlesNotEqualMsg = String.format("Title для отправки: %s, из ответа: %s.",postFromData.getTitle(),postFromResponse.getTitle());
        String userIdNotEqualMsg = String.format("UserId для отправки: %s, из ответа: %s.",postFromData.getUserId(),postFromResponse.getUserId());
        String bodiesNotEqualMsg = String.format("Body для отправки: %s, из ответа: %s.",postFromData.getBody(),postFromResponse.getBody());

        Assert.assertEquals(postFromData.getTitle(), postFromResponse.getTitle(),titlesNotEqualMsg);
        Assert.assertEquals(postFromData.getUserId(), postFromResponse.getUserId(),userIdNotEqualMsg);
        Assert.assertEquals(postFromData.getBody(), postFromResponse.getBody(),bodiesNotEqualMsg);
        Assert.assertNotNull((Integer) postFromResponse.getId(),"Значение id в ответе null.");
    }
}
