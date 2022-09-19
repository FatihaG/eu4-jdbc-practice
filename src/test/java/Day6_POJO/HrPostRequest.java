package Day6_POJO;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class HrPostRequest {

    @BeforeClass
    public void beforeClass() {
        //  RestAssured.baseURI = "http://34.228.41.120:8000";
        baseURI = ConfigurationReader.get("hr_api_url");
    }



    @Test
    public void postRegion1(){
        RegionPost region1= new RegionPost(13,"London");

        given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(region1)
                .when().post("/regions/")
                .then().statusCode(201);





    }
}
