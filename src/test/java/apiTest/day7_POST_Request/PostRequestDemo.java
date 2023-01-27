package apiTest.day7_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){
        String jsonBody="{\n" +
                "  \"name\": \"pink2\",\n" +
                "  \"email\": \"pink2@krafttechexlab.com\",\n" +
                "  \"password\": \"pink2\",\n" +
                "  \"about\": \"student\",\n" +
                "  \"terms\": \"2\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .body(jsonBody) // burda bir string body gönderdik. burdaki de bir serialization
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2(){
        Map<String,Object>requestMap=new HashMap<>();
        requestMap.put("name","pink3");
        requestMap.put("email","pink3@krafttechexlab.com");
        requestMap.put("password","pink3");
        requestMap.put("about","student");
        requestMap.put("terms","3");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization  map (java collection) i json body e dönüştürüyoruz
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3(){
        NewUserInfo newUserInfo=new NewUserInfo();
        newUserInfo.setName("pink4");
        newUserInfo.setEmail("pink4@krafttechexlab.com");
        newUserInfo.setPassword("pink4");
        newUserInfo.setAbout("structor");
        newUserInfo.setTerms("4");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization burda da pojo gönderdik
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser4(){

        NewUserInfo newUserInfo=new NewUserInfo("pink5", "pink5@krafttechexlab.com",
                "pink5", "structor","4");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization burda da pojo gönderdik
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }
}
