package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {


    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "http://34.228.41.120:8000";
        //baseURI = ConfigurationReader.get("spartan_api_url");
    }


    /*
    given accept type is json
    and path param partan id is 14
    when user sends a get request to /api/spartans/{id}
    then status code is 200
    and content type is json
    and "id": 14,
    "name": "Grenville",
    "gender": "Male",
    "phone": 1065669615
     */


    @Test
    public void Spartan14() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 14)
                .when().get("/api/spartans/{id}");

       assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //verify id and name with path

        int id= response.path("id");
        String name = response.path("name");
        String gender= response.path("gender");


        assertEquals(id,14);
        assertEquals(name,"Grenville");
        assertEquals(response.path("gender"),"Male");// bir usengec olarak kisa yol:)

        //assign response to jsonpath

        JsonPath jsonPath= response.jsonPath();

        int idJson=jsonPath.getInt("id");
        String nameJson=jsonPath.getString("name");
        String genderjson=jsonPath.getString("gender");
        long phone=jsonPath.getLong("phone");

        //print values

        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("genderjson = " + genderjson);
        System.out.println("phone = " + phone);

        //verification
        assertEquals(idJson,14);
        assertEquals(nameJson,"Grenville");
        assertEquals(genderjson,"Male");
        assertEquals(phone,1065669615);

    }
}


