package apiShort;

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
import java.util.Map;

public class SpartanJsonToCollection6 {
    @BeforeClass
public void setUpClass(){
    RestAssured.baseURI="http://3.219.28.87:8000";
}
@Test
public void tast1(){

    Response response = given().accept(ContentType.JSON)
            .pathParam("id", 11)
            .and().when().get("/api/spartans/{id}");

    //convert json response to java collection

    Map <String,Object> spartanMap = response.body().as(Map.class);
    System.out.println(spartanMap.get("name"));
    assertEquals(spartanMap.get("gender"),"Female");

}

@Test
public void test2(){

    Response response = given().accept(ContentType.JSON)
            .when().get("/api/spartans");

    //response.prettyPrint();
    //convert full json body to list of map
        //convert full json body to list of maps
    List <Map<String,Object>> listOfSpartans= response.body().as(List.class);

    //print all data of first spartan
    System.out.println(listOfSpartans.get(0));

    //System.out.println(listOfSpartans.get(0).get("name"));
    Map<String,Object> firstSpartan= listOfSpartans.get(0);
    System.out.println(firstSpartan.get("name"));

    int counter=1;
    for (Map<String, Object> map : listOfSpartans) {

        System.out.println(counter + " = " + map);
        counter++;
    }


}
}
