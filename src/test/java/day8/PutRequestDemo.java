package day8;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class PutRequestDemo {

    @BeforeClass
    public void beforeClass() {
        //  RestAssured.baseURI = "http://34.228.41.120:8000";
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1() {
        //create one map for put request json body

        Map<String, Object> putRequestmap = new HashMap<>();
        putRequestmap.put("name", "putName");
        putRequestmap.put("gender", "Female");
        putRequestmap.put("phone", 1234554321l);

        given().contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                .body(putRequestmap)
                .when().put("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify it

    }

    @Test
    public void test2(){
        Map<String, Object> patchRequestmap = new HashMap<>();
        patchRequestmap.put("name", "ayse");
       patchRequestmap.put("gender", "Male");


        given().contentType(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .and()
                .body(patchRequestmap)
                .when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);


    }







}
