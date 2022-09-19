package apiShort;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanTestWithHamcrest5 {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }

    @Test
    public void test1(){
          //request
         given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                 .then().statusCode(200).and()
                 .assertThat().contentType("application/json");

    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", Matchers.equalTo(15),
                        "name",Matchers.equalTo("Meta"),
                        "gender",Matchers.equalTo("Female"));
    }


}
