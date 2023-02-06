package tests;

import framework.ApiUtils;
import framework.DProvider;
import framework.models.Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostByIdTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private static final int ID = 99;

    @Test(dataProvider = "testPostData",dataProviderClass = DProvider.class)
    public static void getPostByIdTest(Post post) {
        Response response = ApiUtils.get(URL, ID);
        String msg = String.format("Статус код %s",response.getStatusCode());

        Assert.assertTrue(ApiUtils.isStatusCode200(response),msg);
        Post postFromResponse = ApiUtils.getPost(ApiUtils.getJson(response));

        Assert.assertEquals(post.getId(), postFromResponse.getId(),"Id в ответе не совпадает с тестовым");
        Assert.assertEquals(post.getUserId(),postFromResponse.getUserId(),"UserId не совпадает с тестовым.");
        Assert.assertFalse(post.getBody().isEmpty(),"Поле 'Body' пусто.");
        Assert.assertFalse(post.getTitle().isEmpty(),"Поле 'Title' пусто.");
    }
}
