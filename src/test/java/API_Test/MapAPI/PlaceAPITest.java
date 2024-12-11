package API_Test.MapAPI;

import APIResources.Place.*;
import Data.EcomDataProvider;
import Utility.ExtentReportListener;
import Utility.PagesUtility;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PlaceAPITest {
    PagesUtility pagesUtility;
    String place_id_data;
    String baseURI = "https://rahulshettyacademy.com/maps";
    double latitude;
    double longitude;
    int accuracy;
    String name;
    String phone;
    String address;
    String types;
    String webstite;
    String language;

    @Test(dataProvider = "APIData", dataProviderClass = EcomDataProvider.class)
    public void addAndGetPlace(double latitude, double longitude, int accuracy, String name, String phone, String address, String types, String website, String language) {
        addPlace(latitude, longitude, accuracy, name, phone, address, types, website, language);
        getPlace();
    }

    public void addPlace(double latitude, double longitude, int accuracy, String name, String phone, String address, String types, String website, String language) {
        pagesUtility = new PagesUtility();
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.types = types;
        this.webstite = website;
        this.language = language;

        RequestSpecification requestSpecification = RestAssured.given().baseUri(baseURI)
                .header("Content-Type", "application/json")
                .queryParam("key", "qaclick123")
                .body(addPlaceData(latitude, longitude, accuracy, name, phone, address));

        Response response = requestSpecification.when()
                .post("/api/place/add/json/");

        //validate response code and message
        response.then().spec(pagesUtility.getResponseSpecification("HTTP/1.1 200 OK"));

        //get response
        System.out.println(response.asPrettyString());
        RegisterPlaceResponse registerPlaceResponse = response.as(RegisterPlaceResponse.class);
        getRegisterPlaceData(registerPlaceResponse);

        //print in report
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportListener.extentTest.log(Status.INFO, "Base URI  " + queryableRequestSpecification.getBaseUri());
        ExtentReportListener.extentTest.log(Status.INFO, "Request Body  " + queryableRequestSpecification.getBody());

        ExtentReportListener.extentTest.log(Status.INFO, "Response  " + response.asPrettyString());
        ExtentReportListener.extentTest.log(Status.INFO, "Response  status code " + String.valueOf(response.statusCode()));
        ExtentReportListener.extentTest.log(Status.INFO, "Response  status line " + response.statusLine());

        System.out.println("Request Body  " + queryableRequestSpecification.getBody());
    }

    public void getPlace() {

        RequestSpecification requestSpecification = RestAssured.given().baseUri(baseURI).queryParam("place_id", place_id_data)
                .queryParam("key", "qaclick123");

        Response response = requestSpecification.when().get("api/place/get/json");

        //validate response code and message
        response.then().spec(pagesUtility.getResponseSpecification("HTTP/1.1 200 OK"));

        GetLocationResponse registerPlace = response.as(GetLocationResponse.class);
        getPlaceData(registerPlace);
        System.out.println(response.asPrettyString());
    }

    public void getPlaceData(GetLocationResponse getLocationResponse) {

        LocationResponse location = getLocationResponse.getLocation();
        Assert.assertEquals(location.getLatitude(), String.valueOf(latitude));
        Assert.assertEquals(location.getLongitude(), String.valueOf(longitude));
        Assert.assertEquals(getLocationResponse.getAccuracy(), String.valueOf(accuracy));
        Assert.assertEquals(getLocationResponse.getName(), name);
        Assert.assertEquals(getLocationResponse.getPhone_number(), phone);
        Assert.assertEquals(getLocationResponse.getAddress(), address);
        Assert.assertEquals(getLocationResponse.getWebsite(), webstite);
        Assert.assertEquals(getLocationResponse.getLanguage(), "English-AUS");

        Assert.assertEquals(getLocationResponse.getTypes(), "home,residency");
    }

    public RegisterPlaceRequest addPlaceData(double latitude, double longitude, int accuracy, String name, String phone, String address) {
        List<String> list = new LinkedList<>(Arrays.asList("home", "residency"));
        Location location = new Location();
        location.setLat(latitude);
        location.setLng(longitude);

        RegisterPlaceRequest registerPlaceRequest = new RegisterPlaceRequest();
        registerPlaceRequest.setLocation(location);
        registerPlaceRequest.setAccuracy(accuracy);
        registerPlaceRequest.setName(name);
        registerPlaceRequest.setPhone_number(phone);
        registerPlaceRequest.setAddress(address);
        registerPlaceRequest.setTypes(list);
        registerPlaceRequest.setWebsite("http://google.com");
        registerPlaceRequest.setLanguage("English-AUS");

        ExtentReportListener.extentTest.log(Status.INFO, "Added Payload body");
        return registerPlaceRequest;
    }

    public void getRegisterPlaceData(RegisterPlaceResponse response) {
        String status = response.getStatus();
        place_id_data = response.getPlace_id();
        String scope = response.getScope();
        String reference = response.getReference();
        String id = response.getId();

        Assert.assertTrue(place_id_data != null);
        ExtentReportListener.extentTest.log(Status.INFO, "status " + status);
        ExtentReportListener.extentTest.log(Status.INFO, "place_id " + place_id_data);
    }
}
