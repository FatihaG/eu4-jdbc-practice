package apiShort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests1 {
    String spartanBaseUrl="http://3.219.28.87:8000";

    @Test
    public void viewSpartanTest1(){

        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body
        System.out.println(response.body().prettyPrint());
    }


    @Test
    /*
    when user get request to /api/spartans end point
    then status code must be 200
    and body should contains Allen
     */

    public void viewSpartanTest2(){

        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        //verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        //verify body contains Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));

    }

    /*
    Given accep type is Json
    When user sends a get request to spartanAllurl
    then response status code 200
    and response body should be json format
     */

    @Test
    public void viewSpartanTest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl + "/api/spartans");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

    }
}
