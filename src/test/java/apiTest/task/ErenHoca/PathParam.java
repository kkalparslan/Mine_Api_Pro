package apiTest.task.ErenHoca;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PathParam {
    /**
    //TASK
    //base url = https://gorest.co.in/
    //end point = /public/v2/users
    //path parameter = {id} --> 206601
    //send a get request with the above credentials
    //verify that the body below
    /*
    {
    "id": 206601,
    "name": "Suryakanta Guha",
    "email": "guha_suryakanta@streich.com",
    "gender": "male",
    "status": "active"
    }
     */
    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 206601)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");

        int id = response.path("id");
        String name = response.path("name");
        String email = response.path("email");
        String gender = response.path("gender");
        String status = response.path("status");

        Assert.assertEquals(id,206601);
        Assert.assertEquals(name,"Suryakanta Guha");
        Assert.assertEquals(email,"guha_suryakanta@streich.com");
        Assert.assertEquals(gender, "male");
        Assert.assertEquals(status, "active");
    }
    /**
     * REQUEST
    baseUrl="https://www.krafttechexlab.com/sw/api/v1"
    Given accept type is json
    and path param is 1
    when user sends GET request to /allusers/getbyid/{id}
     * RESPONSE
    then status code should be 200
    and response content type:application/json;charset=UTF-8
    and response payload values match the following
       id is 1
       name is "aFm"
       email is "afmercan@gmail.com"
       about is "About"
       terms is "16"
     */

    @Test
    public void test2(){
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}");
       // response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        int id=response.path("id[0]");
        String name=response.path("name[0]");
        String email=response.path("email[0]");
        Assert.assertEquals(id, 1);
        Assert.assertEquals(name, "MercanS");
        Assert.assertEquals(email, "afmercan@gmail.com");
    }

}
