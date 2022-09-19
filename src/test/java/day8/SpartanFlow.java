package day8;
import static io.restassured.RestAssured.*;

import Day6_POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
public class SpartanFlow {



    @BeforeClass
    public void beforeClass() {
        //  RestAssured.baseURI = "http://34.228.41.120:8000";
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


    int id=0;
    @Test(priority=1)
    public void POSTNewSpartan(){
        Spartan BabyS = new Spartan();
            BabyS.setName("Joseph");
            BabyS.setGender("Male");
            BabyS.setPhone(1111111111l);

       Response response= (Response) given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(BabyS)
                .when().post("/api/spartans");
                //.then().statusCode(201).log().all();

        id=response.path("data.id");
    }

@Test (priority = 2)
    public void PutExistingSpartan(){
        Spartan youngS = new Spartan();

        youngS.setName("Sarah");
        youngS.setGender("Female");
        youngS.setPhone(2222222222l);

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",id)
                .body(youngS)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


}
@Test(priority = 3)
    public void GetThatSpartan(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);
}

@Test(priority = 4)
    public void DeleteThatSpartan(){

        given().contentType(ContentType.JSON)
                .and().pathParam("id",id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
}
}
