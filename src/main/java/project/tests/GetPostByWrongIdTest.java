package project.tests;

import framework.config.Configuration;
import framework.utils.ApiUtils;
import project.Endpoints;
import framework.utils.JSONUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.ProjectApiMethods;

import java.io.FileNotFoundException;

public class GetPostByWrongIdTest {
    private static final int ID = 150;

    @Test
    public static void getPostsTest() throws FileNotFoundException {

        Response response = ProjectApiMethods.getPostById(ID);

        String msg = String.format("Статус код %s",response.getStatusCode());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, msg);
        Assert.assertTrue(JSONUtils.isJsonEmpty(response),"JSON не пуст.");
    }
}
