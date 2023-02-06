package tests;

import framework.utils.ApiUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostByWrongIdTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private static final int ID = 150;

    @Test
    public static void getPostsTest() {
        Response response = ApiUtils.get(URL, ID);

        String msg = String.format("Статус код %s",response.getStatusCode());
        Assert.assertTrue(ApiUtils.isStatusCode404(response),msg);
        Assert.assertTrue(ApiUtils.isJsonEmpty(response),"JSON не пуст.");
    }
}
