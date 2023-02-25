package apiTest.task.ErenHoca;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostMethod {
    //POST METHOD
    /**
    Along with the others, there are 3 particular ways to provide data into the request body when we use post method:
        1. Assign the JSON body inside a string variable and put it into the body() method
        2. Put data inside a map and provide it into the body() method
        NOTE:body() method converts the data inside the map to JSON automatically. This only happens with POST,PUT andPATCH method.
        3. Put data into an object which is created based on a java custom class and put it into the body() method.
     */

    //TASK
    /**
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "hhh",
    "email": "hhh@xxx.com",
    "password": "h123"
    }
    When user sends POST request
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */
    //first way
    @Test
    public void test1(){
        String jsonBody="{\n" +
                "    \"name\": \"hhhhhh\",\n" +
                "    \"email\": \"hhhhhh@xxx.com\",\n" +
                "    \"password\": \"h123\"\n" +
                "    }";
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when().post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertEquals(response.path("name"), "hhhhhh");
        Assert.assertEquals(response.path("email"), "hhhhhh@xxx.com");
        Assert.assertNotNull(response.path("id"));
    }
}
