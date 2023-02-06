package tests;

import framework.ApiUtils;
import framework.models.Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostPostTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public static void postPostTest() {

        Post firstPost = new Post(1, 2, "3", "4");
        Response response = ApiUtils.postModel(URL, firstPost);

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());

        Assert.assertTrue(ApiUtils.isStatusCode201(response),msgStatusCode);
        Post secondPost = ApiUtils.getPost(ApiUtils.getJson(response));

        String titlesNotEqualMsg = String.format("Title для отправки: %s, из ответа: %s.",firstPost.getTitle(),secondPost.getTitle());
        String userIdNotEqualMsg = String.format("UserId для отправки: %s, из ответа: %s.",firstPost.getUserId(),secondPost.getUserId());
        String bodiesNotEqualMsg = String.format("Body для отправки: %s, из ответа: %s.",firstPost.getBody(),secondPost.getBody());

        Assert.assertEquals(firstPost.getTitle(), secondPost.getTitle(),titlesNotEqualMsg);
        Assert.assertEquals(firstPost.getUserId(), secondPost.getUserId(),userIdNotEqualMsg);
        Assert.assertEquals(firstPost.getBody(), secondPost.getBody(),bodiesNotEqualMsg);
        Assert.assertNotNull((Integer) secondPost.getId(),"Значение id в ответе null.");
    }
}
