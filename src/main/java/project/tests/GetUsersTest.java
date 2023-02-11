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
import java.util.List;

public class GetUsersTest {
    private static int ID = 5;

    @Test(dataProvider = "testUserData",dataProviderClass = DProvider.class)
    public static void getUsersTest(User user) throws FileNotFoundException {

        Response response = ProjectApiMethods.getAllUsers();
        List<User> userList = ProjectApiMethods.getUserList(JSONUtils.getJson(response));

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());
        String msgContentType = String.format("Статус код %s.",response.getContentType());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, msgStatusCode);
        Assert.assertTrue(JSONUtils.isContentTypeJson(response),msgContentType);
        User userById = ProjectApiMethods.getById(userList,ID);
        Assert.assertEquals(user, userById,"Пользователь в ответе не совпадает с тестовыми данными.");
    }
}
