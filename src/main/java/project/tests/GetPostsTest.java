package project.tests;

import framework.config.Configuration;
import framework.utils.ApiUtils;
import project.Endpoints;
import framework.utils.JSONUtils;
import org.apache.http.HttpStatus;
import project.ProjectApiMethods;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GetPostsTest {

    @Test
    public static void getPostsTest() throws FileNotFoundException {

        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_ALL_POSTS);

        Response response = ApiUtils.get(URL);

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());
        String msgContentType = String.format("Статус код %s.",response.getContentType());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, msgStatusCode);
        Assert.assertTrue(JSONUtils.isContentTypeJson(response),msgContentType);
        Assert.assertTrue(ProjectApiMethods.areRightSorted(ProjectApiMethods.getPostList(JSONUtils.getJson(response))),"Пользователи отсортированы неправильно.");
    }
}

