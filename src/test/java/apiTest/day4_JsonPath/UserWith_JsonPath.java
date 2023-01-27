package apiTest.day4_JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class UserWith_JsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";

    }
    /**
     TASK
     Given accept type is json
     And Path param user id is 111
     When user sends a GET request to /allusers/getbyid/{id}
     Then the status Code should be 200
     And Content type json should be "application/json; charset=UTF-8"
     And user's name should be Thomas Eduson
     And user's id should be 111
     And user's email should be thomas@test.com
     */

    @Test
    public void testWithJsonBody(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("allusers/getbyid/{id}");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();
        String name = jsonPath.getString("name[0]");
        System.out.println("name = " + name);
        Assert.assertEquals(name, "Thomas Eduson");

        int id = jsonPath.getInt("id[0]");
        System.out.println("id = " + id);
        Assert.assertEquals(id, 111);

        String email = jsonPath.getString("email[0]");
        System.out.println("email = " + email);
        Assert.assertEquals(email, "thomas@test.com");
    }

    /**
     TASK
     Given accept type is json
     When user sends a GET request to /allusers/alluser
     Then the status Code should be 200
     And Content type json should be "application/json; charset=UTF-8"
     And second name should be isa akyuz
     And first user's experience jobs should be Junior Developer1, Junior Developer1, Junior Developer
     */

    @Test
    public void testWithJasonPath2(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize", 10)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();
        String secondName = jsonPath.getString("name[1]");
        System.out.println("secondName = " + secondName);
        Assert.assertEquals(secondName, "isa akyuz");
        List<String> experience = jsonPath.getList("experience[0].job");
        System.out.println("experience = " + experience);

        List<String>job2=new ArrayList<>();
        job2.add("Junior Developer1");
        job2.add("Junior Developer1");
        job2.add("Junior Developer");
        System.out.println("job2 = " + job2);
        Assert.assertEquals(experience, job2);
    }

    /**
     * TASK
     * Given accept type is json
     * And Path param user id is 111
     * When user sends a GET request to /allusers/getbyid/{id}
     * Then the status Code should be 200
     * And Content type json should be "application/json; charset=UTF-8"
     * Get user skills
     * And user's first skill should be PHP
     */

    @Test
    public void getTestWithJsonPath(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");
        System.out.println("statusCode: " + response.statusCode());
        System.out.println("contentType: "+response.contentType());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        JsonPath jsonPath=response.jsonPath();
        String skills = jsonPath.getString("skills[0]");
        System.out.println("skills = " + skills);
        String skills2 = jsonPath.getString("skills[0][0]");
        System.out.println("skills2 = " + skills2);
        Assert.assertEquals(skills2, "PHP");

    }
}
