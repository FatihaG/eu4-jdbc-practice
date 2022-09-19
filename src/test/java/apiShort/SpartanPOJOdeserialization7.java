package apiShort;




import Day6_POJO.Spartan;
import com.google.gson.Gson;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SpartanPOJOdeserialization7 {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");


        //class yap constructor,getter setter ve to string i yap hepsi icin
        // private int id;
        //private String name;
        //private String gender;
        //private long phone;

        //GSON--> de-serialization

        //how to convert json response to our sparta class
        Spartan spartan1= response.body().as(Spartan.class);
        //verify
        assertEquals(spartan1.getName(),"Meta");
        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getGender(),"Female");

    }



    @Test
    public  void gsonExample(){
        Gson gson=new Gson();
         String myJsonBody= "{\n" +
                 "    \"id\": 15,\n" +
                 "    \"name\": \"Meta\",\n" +
                 "    \"gender\": \"Female\",\n" +
                 "    \"phone\": 1938695106\n" +
                 "}";
         //using gson method do de serialize our json body

        Spartan spartanMeta=gson.fromJson(myJsonBody,Spartan.class);
        System.out.println(spartanMeta.toString());

        //serialization Java object--> JSON BODY
         Spartan spartanFatiha =new Spartan(102,"Fatiha","Female",123456789);
         //converting custom class to json (serialization)
      String jsonBody= gson.toJson(spartanFatiha);

        System.out.println(jsonBody);
    }
}
