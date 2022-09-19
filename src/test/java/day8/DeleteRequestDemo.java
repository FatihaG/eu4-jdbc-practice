package day8;

import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

        import io.restassured.http.ContentType;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;
        import utilities.ConfigurationReader;

import java.sql.SQLOutput;
import java.util.HashMap;
        import java.util.Map;
import java.util.Random;

import static org.hamcrest.Matchers.is;

public class DeleteRequestDemo {


    @BeforeClass
    public void beforeClass() {
        //  RestAssured.baseURI = "http://34.228.41.120:8000";
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void test1(){
        given()
                .pathParam("id", 452)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }


    @Test
    public void test2(){
        // randomly delete
        Random rd = new Random();
        int idToDelete= rd.nextInt(200)+1;
        System.out.println("This Spartan id: "+ idToDelete+ " is deleted..Say good bye!");

        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }


}
