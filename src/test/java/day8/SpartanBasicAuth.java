package day8;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class SpartanBasicAuth {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("")
                .then().log().all()
                .statusCode(200);
    }
}
