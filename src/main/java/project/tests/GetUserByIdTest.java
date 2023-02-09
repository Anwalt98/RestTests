package project.tests;

import framework.config.Configuration;
import framework.utils.ApiUtils;
import project.DProvider;
import project.Endpoints;
import framework.utils.JSONUtils;
import org.apache.http.HttpStatus;
import project.ProjectApiMethods;
import project.models.userModels.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GetUserByIdTest {
    private static final int ID = 5;

    @Test(dataProvider = "testUserData",dataProviderClass = DProvider.class)
    public static void getUserByIdTest(User user) throws FileNotFoundException {

        String URL = ProjectApiMethods.getURL(Configuration.getURL(),Endpoints.GET_USER_BY_ID,ID);

        Response response = ApiUtils.get(URL);
        User userFromResponse = ProjectApiMethods.getUser(JSONUtils.getJson(response));

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, msgStatusCode);
        Assert.assertEquals(userFromResponse, user,"Пользователь в ответе не совпадает с тестовым.");
    }
}
