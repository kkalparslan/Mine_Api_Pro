package apiTest.day3_PathMethod;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserGetRequestWithNegatifParam {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";

    }

    /**
     * Given accept type is json
     * And path param id is 444
     * When user sends a get request to "/allusers/getbyid/{id}
     * Then status code is 404
     * And content-type is "application/json; charset=UTF-8"
     * And "No User Record Found..." message should be in response payload
     */

    @Test
    public void negatifTest(){

        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 444)
                .when().log().all()
                .get("/allusers/getbyid/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("No User Record Found..."));
    }

    /**
     * TASK
     * Given accept type json
     * And query  parameter value name Thomas Eduson
     * And query  parameter value skills Cypress
     * And query  parameter value pagesize 50
     * And query  parameter value page 1
     * When user sends GET request to /allusers/alluser
     * Then response status code should be 200
     * And response content-type application/json; charset=UTF-8
     * And response body contains "Developer" message
     */

    @Test
    public void getWithQueryParam(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("name", "Thomas Eduson")
                .queryParam("skills", "Cypress")
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().log().all()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        Assert.assertTrue(response.body().asString().contains("Cypress"));
    }

    @Test
    public void requestWithMap(){
        Map<String, Object> mapBody=new HashMap<>();
        mapBody.put("name", "Thomas Eduson");
        mapBody.put("skills", "Cypress");
        mapBody.put("pagesize", 50);
        mapBody.put("page", 1);

        Response response=given().accept(ContentType.JSON)
                .queryParams(mapBody)
                .when().log().all()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        Assert.assertTrue(response.body().asString().contains("Cypress"));
    }

}
