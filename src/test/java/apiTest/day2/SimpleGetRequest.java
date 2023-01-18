package apiTest.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String petStoreURL="https://petstore.swagger.io/v2";
    String exlabURL="https://www.krafttechexlab.com/sw/api/v1"; // burdaki sorgular ödev

    @Test
    public void test1(){

        Response response = RestAssured.get(petStoreURL+"/store/inventory");

        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        // print body
        response.prettyPrint();
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL + "/store/inventory");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
        //response.prettyPrint();

        Assert.assertEquals(response.contentType(), "application/json");

    }

    @Test
    public void test3(){
        // verify yesy case with using Restassured Library
        RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL + "/store/inventory")
                .then()
                .assertThat()
                .and()
                .contentType("application/json");
    }

    @Test
    public void test4(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL + "/store/inventory");

        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("response.body().asString() = " + response.body().asString());

        Assert.assertTrue(response.body().asString().contains("sold")); // key yada value lar değişebilir assert te dikkat etmek gerek


    }



}
