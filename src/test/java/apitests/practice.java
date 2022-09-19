package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

public class practice {



@Test

    public void test1(){

    Response response= RestAssured.given().accept(ContentType.JSON)
            .when().get("http://34.228.41.120:8000/api/spartans");
    Assert.assertEquals(response.statusCode(),200);

    System.out.println("response.header(\"Date\") = " + response.header("Date"));
    Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

    List<Header> headers = response.headers().asList();

    System.out.println(response.headers().size());


    System.out.println(response.body().asString());//yazdriyor string formatinda.asertion icin kullanabilirsin

    System.out.println(response.body().toString());// adresini yaziyor
    response.prettyPrint();//konsolda yazdiriyor.asertion yapamazsin


}



}
