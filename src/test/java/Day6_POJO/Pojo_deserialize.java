package Day6_POJO;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://34.228.41.120:8000/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        //json to pojo --> de serialize to java custom class
        //json to our spartan class object

        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getName() = " + spartan15.getName());

        //assertion
        Assert.assertEquals(spartan15.getId(), 15);
        Assert.assertEquals(spartan15.getName(), "Meta");

    }

    @Test
    public void regionToPojo() {

        Response response = when().get("http://34.228.41.120:1000/ords/hr/regions");
        Assert.assertEquals(response.statusCode(), 200);

//        //json to pojo (regions class)
//
        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());
        List<Item> items = regions.getItems();
        System.out.println("items.get(1).getRegionId() = " + items.get(1).getRegionId());


    }
        @Test
    public void gson_example(){

        Gson gson = new Gson();

        //json to java collections or pojo -->de-serialization
            String myJsonData="{\n" +
                    "    \"id\": 15,\n" +
                    "    \"name\": \"Matt\",\n" +
                    "    \"gender\": \"Male\",\n" +
                    "    \"phone\": 1123456789\n" +
                    "}";


            Map<String,Object> map  = gson.fromJson(myJsonData, Map.class);
            System.out.println(map);

            Spartan spartan15= gson.fromJson(myJsonData,Spartan.class);
            System.out.println(spartan15);



            //--------SERIALIZATION------------
                //java collection or pojo to json

        Spartan spartanEU = new Spartan(200,"fatiha","Female",1234567890);
        String jsonSpartanEU= gson.toJson(spartanEU);
            System.out.println(jsonSpartanEU);

        }



}