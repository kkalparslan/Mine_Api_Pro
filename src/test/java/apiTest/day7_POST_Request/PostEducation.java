package apiTest.day7_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

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
        String about="structor";
        String terms="4";

        Map<String,Object>requestMap=new HashMap<>();
        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms", terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        String token=response.path("token");
        //String userID=response.path("id");

        Map<String,Object>educationBody=new HashMap<>();
        educationBody.put("school", "Krafttech");
        educationBody.put("degree", "Bootcamp");
        educationBody.put("study", "QA");
        educationBody.put("fromdate", "2022-02-02");
        educationBody.put("todate", "2023-02-02");
        educationBody.put("current", "false");
        educationBody.put("description", "structor");

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .header("token", token)
                .when().post("/education/add");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        // verify body
        int id=response.path("id");
        response=given().accept(ContentType.JSON)
                .and()
                .header("token", token) // swaggerda querry yada headre olarak değişebiliyor. dikkat edilmeli
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        //verify with path
        //System.out.println("userID = " + userID);
        System.out.println("id = " + id);
        assertEquals(response.path("school"), "Krafttech");
        //assertEquals(response.path("userid"), userID);

        //verify using hamcrest matcher
        given().contentType(ContentType.JSON)
                .and()
                .header("token", token) //swaggerda querry veya header olarak değişebiliyor. dikkatli olmak gerek
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school", equalTo("Krafttech"), "study", equalTo("QA"))
                .log().all();
    }
}
