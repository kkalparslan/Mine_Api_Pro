package apiTest.day7_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class PostEducation {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){
        NewUserInfo newUserInfo=new NewUserInfo("pink82", "pink82@krafttechexlab.com",
                "pink82", "structor","4");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization burda da pojo gönderdik
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        String token=response.path("token");

        String educationBody="{\n" +
                "  \"school\": \"Ankara Universitesi\",\n" +
                "  \"degree\": \"BootCamp\",\n" +
                "  \"study\": \"SDET\",\n" +
                "  \"fromdate\": \"2022-02-02\",\n" +
                "  \"todate\": \"YYYY-MM-DD\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"student\"\n" +
                "}";

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .header("token", token)
                .when().post("/education/add");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify(){
        String name="pink9";
        String email="pink9@krafttechexlab.com";
        String password="pink9";
        String about="about me";
        String terms="4";

    }
}
