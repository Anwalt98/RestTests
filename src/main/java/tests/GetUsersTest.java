package tests;

import framework.utils.ApiUtils;
import framework.DProvider;
import framework.utils.ModelUtils;
import framework.models.userModels.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetUsersTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static int id = 5;

    @Test(dataProvider = "testUserData",dataProviderClass = DProvider.class)
    public static void getUsersTest(User user) {
        Response response = ApiUtils.get(URL);
        List<User> userList = ApiUtils.getUserList(ApiUtils.getJson(response));

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());
        String msgContentType = String.format("Статус код %s.",response.getContentType());

        Assert.assertTrue(ApiUtils.isStatusCode200(response),msgStatusCode);
        Assert.assertTrue(ApiUtils.isContentTypeJson(response),msgContentType);
        User userById = ModelUtils.getById(userList,id);
        Assert.assertEquals(user, userById,"Пользователь в ответе не совпадает с тестовыми данными.");
    }
}
