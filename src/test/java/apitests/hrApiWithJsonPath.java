package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");

    }


    @Test
    public void test1(){

        Response response= get("/countries");
        //assign to jsonpath
        JsonPath jsonPath= response.jsonPath();

        String secondCountryName= jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country ids
        List<String> countryIDs= jsonPath.getList("items.country_id");
        System.out.println(countryIDs);

        //get all contry names where their region id equla to 2
        // filter with jpath syntax
        List<String> contryNamesWithRegionId2=jsonPath.getList("items.findAll {it.region_id==2}.country_name");//it.hepsini ver demek(each value) >veya < da kullanabilirsin
        List<String> contryNamesWithRegionId22=response.path("items.findAll {it.region_id==2}.country_name");//aynisini veriyor Gpath syntax
        System.out.println(contryNamesWithRegionId2);
        System.out.println(contryNamesWithRegionId22);
    }


    @Test
    public void test2(){
        Response response= given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all first name of employees who is working as it_prog

        List<String> firstNameofITProg= jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println(firstNameofITProg);

        //get me all first name of employees who is making more than 10000

        List<String> NameofITProgMore= jsonPath.getList("items.findAll {it.salary<2200}.first_name");
        System.out.println(NameofITProgMore);

        //get me first name of who is making highest salary

        String king= jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println(king);

        String poor= jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println(poor);


    }











}
