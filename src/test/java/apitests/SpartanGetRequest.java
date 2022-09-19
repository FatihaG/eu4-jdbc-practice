package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpartanGetRequest {
    String spartanurl="http://100.25.182.201:8000";

    @Test
    public void test1(){


        Response response = when().get("http://100.25.182.201:8000" + "/api/spartans");
        System.out.println(response.statusCode());
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

    }

    /*when users sends a get request to /api/spartans/3
        then status code should be 200
        and content type should be application /json;charset=UFT-8
        and json body should contain Fidole
        * */
    @Test
    public void test2(){
        Response response=when().get(spartanurl + "/api/spartans/3");
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    /*
    * Given no headers provided
    * when users sends get request to /api/hello
    * then response status code should be 200
    * and content type header should be "text/plain;charset=UTF-8"
    * and header should contain date
    * and content-length should be 17
    * and body should be "Hello from Sparta"
    * */

@Test
    public void helloTest(){

    //request
    Response response= when().get(spartanurl+"/api/hello");
    //verify status code

    Assert.assertEquals(response.statusCode(),200);

    //verify content-type
    Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");
    //verify we hAVE HEADERS NAMED date
    Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

    // to get any header passing a key
    System.out.println(response.header("Content-Length"));
    System.out.println(response.header("Date"));

    //verify content length is 17
    Assert.assertEquals(response.header("Content-Length"),"17");

    Assert.assertEquals(response.getBody().asString(),"Hello from Spartan");
}
}
