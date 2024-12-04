package API_Test;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class UserData {

    Faker faker;
    @Test
    public void getUserData() {
        baseURI = "https://reqres.in/api/";

        Response response = given().log().all()
                .queryParam("page", 1)
                .queryParam("per_page", 10)
                .when()
                .get("resource");

        System.out.println(response.asPrettyString());
        int statusCode = response.statusCode();
        String statusLine = response.statusLine();
        long responseTime = response.timeIn(TimeUnit.MILLISECONDS);

        Headers headers = response.headers();
        System.out.println("******Headers******");
        headers.forEach(e -> System.out.println(e.getName() + ": " + e.getValue()));

        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(statusLine.contains("OK"));
        Assert.assertTrue(responseTime < 2000);
    }

   @Test
    public void getToken()
    {
        faker = new Faker();
        Map<String, Object> obj = new LinkedHashMap<>();

        obj.put("email","eve.holt@reqres.in");
        obj.put("password",faker.name().firstName() + faker.number().numberBetween(1,100));

        baseURI = "https://reqres.in/api/";
        Response response  = given().log().all()
                .headers("Content-Type",ContentType.JSON)
                .body(obj)
                .when()
                .post("login");


        System.out.println("***Response***");
        System.out.println(response.asPrettyString());
        int statusCode = response.statusCode();
        String statusLine = response.statusLine();
        long responseTime = response.timeIn(TimeUnit.MILLISECONDS);

        Headers headers = response.headers();
        System.out.println("******Headers******");
        headers.forEach(e -> System.out.println(e.getName() + ": " + e.getValue()));

        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(statusLine.contains("OK"));
        Assert.assertTrue(responseTime < 2000);
    }
}
