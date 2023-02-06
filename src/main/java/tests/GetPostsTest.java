package tests;

import framework.ApiUtils;
import framework.ModelUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostsTest {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    @Test
    public static void getPostsTest() {
        Response response = ApiUtils.get(URL);

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());
        String msgContentType = String.format("Статус код %s.",response.getContentType());

        Assert.assertTrue(ApiUtils.isStatusCode200(response),msgStatusCode);
        Assert.assertTrue(ApiUtils.isContentTypeJson(response),msgContentType);
        Assert.assertTrue(ModelUtils.areRightSorted(ApiUtils.getPostList(ApiUtils.getJson(response))),"Пользователи отсортированы неправильно.");
    }
}

