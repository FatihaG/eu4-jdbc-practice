package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiParameterTest {

        @BeforeClass
        public void beforeClass(){
            baseURI= ConfigurationReader.get("hr_api_url");


        }

        /*
        given accept type is json
        and parameters:q="region_id":2
        when users sends a get request to "/countries"
        then status code is 200
        and content type is application/json
        and playload should contain "United States of America"
        {"region_id":2}
         */

        @Test
    public void test1(){

            Response response = given().accept(ContentType.JSON)
                    .and().queryParams("q", "{\"region_id\":2}")
                    .when().get("/countries");

            System.out.println(response.prettyPrint());
            assertEquals(response.statusCode(),200);
            assertEquals(response.contentType(),"application/json");
            assertTrue(response.body().asString().contains("United States of America"));


        }


        @Test
    public void test2(){
            Response response = given().accept(ContentType.JSON)
                    .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                    .when().get("/employees");
            assertEquals(response.statusCode(),200);
            assertEquals(response.contentType(),"application/json");
            assertTrue(response.body().asString().contains("IT_PROG"));

        }
}
