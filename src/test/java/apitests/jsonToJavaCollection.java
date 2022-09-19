package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.*;


public class jsonToJavaCollection {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.get( "spartan_api_url");

    }


    @Test
    public void SpartanToMap(){
        Response response= given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);

        //we will convert json response to java map de-serialization (gson or jackson kullaniyoruz)

        Map<String,Object> jsonDataMap= response.body().as(Map.class);//converter olarak kullaniyoruz(
        System.out.println("jsonDataMap = " + jsonDataMap);
        String name =(String) jsonDataMap.get("name");
        assertEquals(name,"Meta");
       //
        // double phone = (double) jsonDataMap.get("phone");

        BigDecimal phone= new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone= "+ phone);

    }

    @Test
    public void allSpartansToListOfMap(){
        Response response= given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(response.statusCode(),200);

        //we need to de-serialize jsonresponse to list maps
        List<Map<String,Object>> allspartanList= response.body().as(List.class);
        System.out.println(allspartanList);

        //print second spartan first name

        System.out.println(allspartanList.get(1).get("name"));
            //save spartan 3 in a map
        Map<String,Object> spartan3= allspartanList.get(2);
        System.out.println(spartan3);


    }
        @Test
    public void regionToMap(){

            Response response = when().get("http://100.25.182.201:1000/ords/hr/regions");
            assertEquals(response.statusCode(),200);

            //we re-serialize json response to map
            Map<String,Object> regionMap= response.body().as(Map.class);// cunku en distaki parantezde tek bir map hepsi.list degil

            System.out.println(regionMap.get("count"));

            System.out.println(regionMap.get("hasMore"));

            System.out.println(regionMap.get("items"));//one object but list of map (array of map)

            List<Map<String,Object>> itemsList= (List<Map<String, Object>>) regionMap.get("items");//object ti list of map e ceviriyorum
            //print first region name

            System.out.println(itemsList.get(0).get("region_name"));

           // Map<String,List<Map<String,Object>>> demek olmus oluyor.

            //href i yazmaya calisiyorum europe icin.

            List<Map<String,Object>> links = (List<Map<String, Object>>) itemsList.get(0).get("links");

            System.out.println(links.get(0).get("href"));


            //genel linksdeki ilk value'ya ulasmaya calisiyorum
                List<Map<String,Object>> genelLinks= (List<Map<String, Object>>) regionMap.get("links");
            System.out.println(genelLinks.get(1).get("rel"));

        }




}


