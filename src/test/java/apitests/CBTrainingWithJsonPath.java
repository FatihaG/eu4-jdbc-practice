package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("cbt_api_url");

    }


    @Test
    public void test1(){

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 20202)
                .when().get("/student/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();


        //get value from jsonpath

        String firstName= jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);
        String lastName=jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);
        String phone=jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        //get city and zip code

        String city= jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        int zipCode=jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);

        assertEquals(city,"Chicago");
        assertEquals(zipCode,60606);

    }



}