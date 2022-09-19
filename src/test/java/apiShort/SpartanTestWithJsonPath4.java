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

public class SpartanTestWithJsonPath4 {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        //how to read value with path() method
        int id =response.path("id");
        System.out.println("id = " + id);

        //how to read value with JsonPath?

        JsonPath jsonData = response.jsonPath();

        int id1= jsonData.getInt("id");
        String name= jsonData.getString("name");
        String gender= jsonData.getString("gender");
        long phone= jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id1,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");


    }
}
