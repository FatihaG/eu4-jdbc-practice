package apitests;
import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = "http://34.228.41.120:8000";

    }

    /*
    given accept type json
    and path param id is 10
    when user sends a get request to "api/spartans/{id}
    then status code is 200
    and content-type is application/json;char
    and response playload values match the following:
    id is 10,
    name is "Lorenza",
    gender is "Female",
    phone is 3312820936
     */

    @Test
    public void getoneSpartanWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //save json key values
        int id= response.path("id");
        String name=response.path("name");
        String gender= response.path("gender");
        long phone =response.path("phone");

        //assert by one by
        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);


    }

    @Test
    public void getAllSpartanWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.getHeader("Content-Type"),"application/json");

        System.out.println(response.path("id[0]").toString());
        int firstid = response.path("id[0]");

        String firstName= response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastFirstName= response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        int lastId= response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        //print all names of spartan
        List<String> names = response.path("name");
        System.out.println("names = " + names);

        List<Object> phones = response.path("phone");

        for (Object phone : phones) {
            System.out.println(phone);

        }

    }
}
