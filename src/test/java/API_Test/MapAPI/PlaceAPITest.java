package API_Test.MapAPI;

import APIResources.Place.Location;
import APIResources.Place.RegisterPlaceRequest;
import APIResources.Place.RegisterPlaceResponse;
import Utility.ExtentReportListener;
import Utility.PagesUtility;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PlaceAPITest {
    PagesUtility pagesUtility;

    @Test
    public void addPlace() {
        pagesUtility = new PagesUtility();

        RequestSpecification requestSpecification = RestAssured.given().baseUri("https://rahulshettyacademy.com/maps")
                .header("Content-Type", "application/json")
                .queryParam("key", "qaclick123")
                .body(addPlaceData());

        Response response = requestSpecification.when()
                .post("/api/place/add/json/");

        response.then().spec(pagesUtility.getResponseSpecification("HTTP/1.1 200 OK"));

        RegisterPlaceResponse registerPlaceResponse = response.as(RegisterPlaceResponse.class);
        getPlaceData(registerPlaceResponse);

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportListener.extentTest.log(Status.INFO, "Base URI  " + queryableRequestSpecification.getBaseUri());
        ExtentReportListener.extentTest.log(Status.INFO, "Request Body  " + queryableRequestSpecification.getBody());

        ExtentReportListener.extentTest.log(Status.INFO, "Response  " + response.asPrettyString());
        ExtentReportListener.extentTest.log(Status.INFO, "Response  status code " + String.valueOf(response.statusCode()));
        ExtentReportListener.extentTest.log(Status.INFO, "Response  status line " + response.statusLine());
    }

    public RegisterPlaceRequest addPlaceData() {
        List<String> list = new LinkedList<>(Arrays.asList("home", "residency"));
        Location location = new Location();
        location.setLat(-10.4578);
        location.setLng(12.7887);

        RegisterPlaceRequest registerPlaceRequest = new RegisterPlaceRequest();
        registerPlaceRequest.setLocation(location);
        registerPlaceRequest.setAccuracy(10);
        registerPlaceRequest.setName("Residency A");
        registerPlaceRequest.setPhone_number("(+61) 767 444 4444");
        registerPlaceRequest.setAddress("abc street");
        registerPlaceRequest.setTypes(list);
        registerPlaceRequest.setWebsite("http://google.com");
        registerPlaceRequest.setLanguage("English-AUS");

        ExtentReportListener.extentTest.log(Status.INFO, "Added Payload body");
        return registerPlaceRequest;
    }

    public void getPlaceData(RegisterPlaceResponse response) {
        String status = response.getStatus();
        String place_id = response.getPlace_id();
        String scope = response.getScope();
        String reference = response.getReference();
        String id = response.getId();

        ExtentReportListener.extentTest.log(Status.INFO, "status " + status);
        ExtentReportListener.extentTest.log(Status.INFO, "place_id " + place_id);
    }
}
