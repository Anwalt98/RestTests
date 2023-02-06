package tests;

import framework.ApiUtils;
import framework.DProvider;
import framework.models.userModels.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserByIdTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final int ID = 5;

    @Test(dataProvider = "testUserData",dataProviderClass = DProvider.class)
    public static void getUserByIdTest(User user) {

        Response response = ApiUtils.get(URL,ID);
        User userFromResponse = ApiUtils.getUser(ApiUtils.getJson(response));

        String msgStatusCode = String.format("Статус код %s.",response.getStatusCode());

        Assert.assertTrue(ApiUtils.isStatusCode200(response),msgStatusCode);
        Assert.assertEquals(userFromResponse, user,"Пользователь в ответе не совпадает с тестовым.");
    }
}
