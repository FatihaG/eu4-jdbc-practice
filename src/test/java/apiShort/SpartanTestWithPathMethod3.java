package apiShort;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SpartanTestWithPathMethod3 {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }

    @Test
    public void Test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        System.out.println("id" + response.body().path("id").toString());

        int id=response.body().path("id");
        String gender=response.body().path("gender");
        String name= response.body().path("name");
        long phono=response.body().path("phone");

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");

    }

    @Test
    public void test2(){
        Response response = get("/api/spartans");

        int firstID= response.path("id[0]");
        System.out.println("firstID = " + firstID);
        //get first one
        String firts1name= response.path("name[0]");
        System.out.println("firts1name = " + firts1name);
        //get last one
        String last1name=response.path("name[-1]");
        System.out.println("last1name = " + last1name);

        String sondan2once= response.path("name[-3]");
        System.out.println("sondan2once = " + sondan2once);

        //get all first names
        List<String> names= response.path("name");
        System.out.println(names);

        List<Object> phoneNumbers= response.path("phone");
        for (Object phoneNumber : phoneNumbers){
            System.out.println(phoneNumbers);
        }

    }

}
