package apiTest.task.ErenHoca;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class HamcrestMatcher {
    // no testNG
    // we wiil be using rest assured assertions (matchers class) for the verification
    // no response body
    // matchers is a assertion library for a verification coming from restassured
    // we will be using gpath syntex
    //  1. matchers.equalTo("abc)
    //  2. matchers.notNullValue()
    //  3. matchers.hasItems("abc", "def")
    // good to know for interview purposes

    //task
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //get the first user from {{baseUrl}}/allusers/getbyid/1
    //status code is equel to 200
    //verify id, name, email and location with the hamcrestmatchers
    @Test
    public void test1() {
        RestAssured
                .given()
                .pathParam("id", 1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .body("id[0]", Matchers.equalTo(1),
                        "name[0]", Matchers.equalTo("MercanS"),
                        "email[0]", Matchers.equalTo("afmercan@gmail.com"));
    }
    //TASK
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //get the first user from {{baseUrl}}/allusers/getbyid/1
    //verify response headers with the hamcrestmatchers
    //verify that the company equals to "Mrcn Software" with the hamcrestmatchers (inside body)
    @Test
    public void test2() {
        RestAssured
                .given()
                .pathParam("id", 1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .header("content-type", Matchers.equalTo("application/json; charset=UTF-8"))
                .and()
                .header("date", Matchers.notNullValue())
                .and()
                .body("company[0]", Matchers.equalTo("Mrcn Software"));
    }
    //TASK
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //end point /allusers/alluser
    //query parameter 1. page = 1
    //query parameter 2. pagesize = 5
    //send a request to get first 5 users and their features
    //verify that the names contains MercanS and Sebastian with hamcrestmatchers
    @Test
    public void test3() {
        RestAssured
                .given()
                .queryParam("page", 1)
                .queryParam("pagesize", 5)
                .when().get("https://www.krafttechexlab.com/sw/api/v1/allusers/alluser")
                .then()
                .statusCode(200)
                .and()
                .body("name", Matchers.hasItems("MercanS", "Sebastian"));
    }
}
