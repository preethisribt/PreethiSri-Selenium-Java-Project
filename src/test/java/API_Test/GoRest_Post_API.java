package API_Test;

import Pages.API_Mapping.UserAPI;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GoRest_Post_API {
    Response response;
    List<UserAPI> users;

    @Test(groups = {"API"})
    public void getAllUsers() {
        baseURI = "https://gorest.co.in/public/v2/";

        response = given().log().all()
                .header("Authorization", "Bearer 220d22050c1ef6cedefd5f8053a340865d6aec532b8096a7f203b6fd6b5ec63c")
                .when()
                .get("/users/?page=1&per_page=10");

        String statusLineResponse = response.statusLine();
        int statusCodeResponse = response.statusCode();
        long responseTime = response.getTime();
        users = response.jsonPath().getList("", UserAPI.class);

        ChainTestListener.log("Response  " + response.asPrettyString());
        ChainTestListener.log("Response Status Line " + statusLineResponse);
        ChainTestListener.log("Response Status Code " + statusCodeResponse);
        ChainTestListener.log("Response Time" + responseTime);

        Assert.assertEquals(statusLineResponse, "HTTP/1.1 200 OK");
        Assert.assertEquals(statusCodeResponse, 200);
        Assert.assertTrue(responseTime < 3000, "Response time below 3000 MS");
    }

    @Test(groups = {"API"}, dependsOnMethods = "getAllUsers", description = "get random user, add post in their profile and validate")
    public void addPostAndValidateItUnderUserProfile() {
        UserAPI userData = users.get(0);
        int id = userData.getId();

        addPost(id);
    }

    public void addPost(int userID) {
        baseURI = "https://gorest.co.in/public/v2/";
        Faker faker = new Faker();
        faker.book().title();

        Response response = given().log().all()
                .header("Authorization", "Bearer 220d22050c1ef6cedefd5f8053a340865d6aec532b8096a7f203b6fd6b5ec63c")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"user_id\": \"" + userID + "\",\n" +
                        "    \"title\": \"" + faker.book().title() + "\",\n" +
                        "    \"body\": \"" + faker.book().author() + "\"\n" +
                        "}")
                .when()
                .post("/users/" + userID + "/posts");

        String statusLineResponse = response.getStatusLine();
        int statusCodeResponse = response.getStatusCode();
        long responseTime = response.getTime();

        ChainTestListener.log("Response " + response.asPrettyString());
        ChainTestListener.log("Response Status Line" + statusLineResponse);
        ChainTestListener.log("Response Status Code" + statusCodeResponse);

        Assert.assertEquals(statusLineResponse, "HTTP/1.1 201 Created");
        Assert.assertEquals(statusCodeResponse, 201);
        Assert.assertTrue(responseTime < 3000, "Response Time less than 3000 MS");
    }

    public void getAddedPost()
    {

    }
}
