package API_Test;

import APIResources.UserAPI;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GoRestUserAPI {
    Faker faker;
    UserAPI userAPI;
    int userID;

    @Test
    public void getAllUsers() {
        baseURI = "https://gorest.co.in/public/v2/";

        Response response = given().log().all()
                .queryParam("Authorization", "Bearer 220d22050c1ef6cedefd5f8053a340865d6aec532b8096a7f203b6fd6b5ec63c")
                .when()
                .get("/users/?page=1&per_page=10");

        String statusLineResponse = response.statusLine();
        int statusCodeResponse = response.statusCode();

        ChainTestListener.log("Response  " + response.asPrettyString());
        ChainTestListener.log("Response Status Line " + statusLineResponse);
        ChainTestListener.log("Response Status Code " + statusCodeResponse);

        Assert.assertEquals(statusLineResponse, "HTTP/1.1 200 OK");
        Assert.assertEquals(statusCodeResponse, 200);

    }

    @Test
    public void addAndValidateUserCreation() {
//        addUser();
        getParticularUsers(7663080);
    }

    public void addUser() {
        baseURI = "https://gorest.co.in/public/v2/";
        faker = new Faker();

        String firstname = faker.name().firstName();
        String email = firstname + "@osinski.example";

        userAPI = new UserAPI();
        userAPI.setName(firstname);
        userAPI.setEmail(email);
        userAPI.setGender("male");
        userAPI.setStatus("active");

        Response response = given().log().all()
                .queryParam("access-token", "220d22050c1ef6cedefd5f8053a340865d6aec532b8096a7f203b6fd6b5ec63c")
                .header("Content-Type", "application/json")
                .body(userAPI)
                .when()
                .post("users");

        String statusLineResponse = response.statusLine();
        int statusCodeResponse = response.statusCode();

        ChainTestListener.log("Response  " + response.asPrettyString());
        ChainTestListener.log("Response Status Line " + statusLineResponse);
        ChainTestListener.log("Response Status Code " + statusCodeResponse);

        Assert.assertEquals(statusLineResponse, "HTTP/1.1 201 Created");
        Assert.assertEquals(statusCodeResponse, 201);

        userAPI = response.as(UserAPI.class);
        userID = userAPI.getId();
    }

    public void getParticularUsers(int userID) {
        baseURI = "https://gorest.co.in/public/v2/";

        Response response = given().log().all()
                .header("Authorization", "Bearer 220d22050c1ef6cedefd5f8053a340865d6aec532b8096a7f203b6fd6b5ec63c")
                .when()
                .get("users/" + userID + "");

        String statusLineResponse = response.statusLine();
        int statusCodeResponse = response.statusCode();

        ChainTestListener.log("Response  " + response.asPrettyString());
        ChainTestListener.log("Response Status Line " + statusLineResponse);
        ChainTestListener.log("Response Status Code " + statusCodeResponse);

        userAPI = response.as(UserAPI.class);
        int actualID = userAPI.getId();

        ChainTestListener.log("ID  " +  actualID);
        ChainTestListener.log("Name  " + userAPI.getName());
        ChainTestListener.log("Email  " + userAPI.getEmail());
        ChainTestListener.log("Gender  " + userAPI.getGender());
        ChainTestListener.log("Status  " + userAPI.getStatus());

        Assert.assertEquals(statusLineResponse, "HTTP/1.1 200 OK");
        Assert.assertEquals(statusCodeResponse, 200);
        Assert.assertEquals(actualID, userID);
    }
}
