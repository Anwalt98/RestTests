package framework.utils;

import project.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;


public class ApiUtils {

    public static Response get(String baseURL){
        return RestAssured.get(baseURL);
    }

    public static Response post(String url, String jsonString){
        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString);
        return requestSpecification.post();
    }
}
