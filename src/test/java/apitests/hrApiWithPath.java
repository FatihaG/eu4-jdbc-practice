package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithPath {


    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId=response.path("items.country_id[0]");//hr da items oldugu icin her item bir array.ve icinde tum bilgileri tutuyor
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryName= response.path("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        String beforeLastCountrName=response.path("items.country_name[-2]");//json index 0 dan sondan gelirken -1
        System.out.println("beforeLastCountrName = " + beforeLastCountrName);

        String firsthref=response.path("items.links[0].href[0]");//items is array, links is array in items, href is in links array
        System.out.println("href = " + firsthref);

            //get all contry_name
       List< String> allCounterNames= response.path("items.country_name");
        System.out.println("allCounterNames = " + allCounterNames);


        List<Integer> allRegionId= response.path("items.region_id");
        //System.out.println("allRegionId = " + allRegionId);

        for (int regionId : allRegionId) {
            //System.out.println(regionId);
            assertEquals(regionId,2);
            };

        }



    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure each of job_id is IT_PROG
       List< String>  jobIDs =response.path("items.job_id");

        for (String jobID : jobIDs) {
            assertEquals(jobID,"IT_PROG");

        }


    }









        }











