package day8;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;

public class jsonSchemaValidationDemo {



    @BeforeClass
    public void beforeClass() {
        //  RestAssured.baseURI = "http://34.228.41.120:8000";
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void JsonSchemaValidationForSpartan(){


        given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/partans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }

}
