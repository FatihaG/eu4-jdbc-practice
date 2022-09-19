package apitests;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {

    @Test
    public void onespartanWithHamcrest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 14)
                .when().get("http://100.25.182.201:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json"))//matchers static import edildi
                .and().assertThat().body("id", equalTo(14),
                "name", equalTo("Grenville"),
                "gender", equalTo("Male"),
                "phone", equalTo(1065669615));

    }

    @Test
    public void teacherdata() {
        given().contentType(ContentType.JSON)
                .and().pathParam("id", 3682)
                .when()//.log().all()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Length", equalTo("242"))
                .and().header("Connection", equalTo("Keep-Alive"))
                .and().header("Date", notNullValue())//date surekli degistigi icin olup olmadigini kontrol ediyoruz degerini degil
                .and().assertThat().body("teachers.firstName[0]", equalTo("James"),
                                        "teachers.lastName[0]", equalTo("Bond"),
                                        "teachers.gender[0]", equalTo("Female"))
                .log().headers();//yazdirmak icin ne istiyorsan


    }

    @Test

    public void teachersWithDepartments(){

        given().contentType(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when()//.log().all()
                .get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().body("teachers.firstName",hasItems("Alexander","Marteen"));//once tum ogretmenlerin firstname i sonra icinde onlar var mi diye



    }
}
