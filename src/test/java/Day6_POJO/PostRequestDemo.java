package Day6_POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
      //  RestAssured.baseURI = "http://34.228.41.120:8000";
    RestAssured.baseURI = ConfigurationReader.get("spartan_api_url");
    }

        /*
        Given accept type and Content type is json
        and request json body is:

        {
        "gender":"Male",
        "name":"MikeEU",
        "phone":8877665544;
        }

        When user  sends Post request to '/api/spartans'
        then status code 201
        and content type should be application/json
        and json paylad/response should contain
        "A Spartan is Born!" message
        and same data what is posted

         */
        @Test
        public void deneme(){

            given().accept(ContentType.JSON)
                    .when().get("/api/spartans")
                    .then().statusCode(200);
        }

        @Test
        public void PostNewSpartan(){

            String jsonBody= "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"MikeEe\",\n" +
                    "  \"phone\": 5478783575\n" +
                    "}";
            Response response = given().accept(ContentType.JSON).
                    and().contentType(ContentType.JSON)
                    .and().body(jsonBody).when()
                    .post("/api/spartans");

            //response validations
            assertEquals(response.statusCode(),201);
            assertEquals(response.contentType(),"application/json");

            //verify response body
            String successMessage = response.path("success");
            System.out.println("successMessage = " + successMessage);
            assertEquals(successMessage,"A Spartan is Born!");

            String name = response.path("data.name");
            String gender = response.path("data.gender");
            long phone = response.path("data.phone");

            assertEquals(name,"MikeEe");
            assertEquals(gender,"Male");
            assertEquals(phone,5478783575l);
            //printing the id
            System.out.println(response.path("data.id").toString());


        }

        @Test
    public void PostNewSpartan2(){
            //create a map to kee json body information
            Map<String,Object> requestMap= new HashMap<>();

            //adding value that we want to post
            requestMap.put("name","MikeEU");
            requestMap.put("gender","Male");
            requestMap.put("phone",1122334455l);

            given().log().all()
                    .accept(ContentType.JSON)
                    .and()
                    .contentType(ContentType.JSON)
                    .and()
                    .body(requestMap)
                    .when()
                    .post("/api/spartans")
                    .then()
                    .statusCode(201)
                    .and()
                    .contentType("application/json")
                    .and()
                    .body("success",is("A Spartan is Born!"),
                            "data.name",equalTo("MikeEU"),
                            "data.gender",equalTo("Male"),
                            "data.phone",equalTo(1122334455l));



        }
        @Test
        public void PostNewSpartan3(){
            Spartan spartanEU= new Spartan();
            spartanEU.setName("MikeEe");
            spartanEU.setGender("Male");
            spartanEU.setPhone(1122334455l);

            given().log().all()
                    .accept(ContentType.JSON)
                    .and()
                    .contentType(ContentType.JSON)
                    .and()
                    .body(spartanEU)
                    .when()
                    .post("/api/spartans")
                    .then()
                    //.statusCode(201)
                   // .and()
                    .contentType("application/json")
                    .and()
                    .body("success",is("A Spartan is Born!"),
                            "data.name",equalTo("MikeEe"),
                            "data.gender",equalTo("Male"),
                            "data.phone",equalTo(1122334455l));



        }


        //after post request , send a get request to generated spartan

    @Test
    public void PostNewSpartan4(){
        Spartan spartanEU= new Spartan();
        spartanEU.setName("MikeEe");
        spartanEU.setGender("Male");
        spartanEU.setPhone(1122334455l);

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartanEU)
                .when()
                .post("/api/spartans");
        //end o post request


        //get request
        int id= response.path("data.id");
        System.out.println("id = " + id);
                    given().contentType(ContentType.JSON)
                            .pathParam("id", id)
                            .when().get("/api/spartans/{id}")
                            .then().statusCode(200).log().all();







    }




}
