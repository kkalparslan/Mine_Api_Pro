package apiTest.task.ErenHoca;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class JsonToJavaDeserialize {

    //TASK
    //base url = https://gorest.co.in/
    //end point = /public/v2/users
    //path parameter = {id} --> 239427
    //send a get request with the above credentials
    //parse to Json object to java collection
    //verify that the body below
    /*
    {
        "id": 239427,
        "name": "Smriti Panicker",
        "email": "panicker_smriti@mosciski.org",
        "gender": "female",
        "status": "active"
    }
     */

    //TEST WITH HARD ASSERTION
    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 241307)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");
        //response.prettyPrint();

        Map<String,Object>map=response.body().as(Map.class);
//        System.out.println("map = " + map);
//        System.out.println("map.get(\"id\") = " + map.get("id"));
//        System.out.println("map.get(\"name\") = " + map.get("name"));
//        System.out.println("map.get(\"email\") = " + map.get("email"));
//        System.out.println("map.get(\"gender\") = " + map.get("gender"));
//        System.out.println("map.get(\"status\") = " + map.get("status"));

        Assert.assertEquals(map.get("id"), 239427.0);
        Assert.assertEquals(map.get("name"), "Smriti Panicker");
        Assert.assertEquals(map.get("email"), "panicker_smriti@mosciski.orgg");
        Assert.assertEquals(map.get("gender"), "female");
        Assert.assertEquals(map.get("status"), "active");

    }
    // SAME TEST WİTH SOFTASSERTION
    //COMING FROM TESTNG

    @Test
    public void test2(){
        Response response = RestAssured
                .given()
                .pathParam("id", 239427)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");

        Map<String,Object> map = response.as(Map.class);
        //System.out.println("map = " + map);

        //System.out.println("map.get(\"id\") = " + map.get("id"));
        //System.out.println("map.get(\"name\") = " + map.get("name"));
        //System.out.println("map.get(\"email\") = " + map.get("email"));
        //System.out.println("map.get(\"gender\") = " + map.get("gender"));
        //System.out.println("map.get(\"status\") = " + map.get("status"));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(map.get("id"), 239427.0);
        softAssert.assertEquals(map.get("name"), "Smriti Panicker");
        softAssert.assertEquals(map.get("email"), "panicker_smriti@mosciski.orgg");
        softAssert.assertEquals(map.get("gender"), "femalee");
        System.out.println("eren");
        softAssert.assertEquals(map.get("status"), "active");
        softAssert.assertAll();
    }

    // TASK
    // base url = https://gorest.co.in/
    // endpoint = /public/v2/users
    // send the GET request, get the all data into a list by de-serilization
    @Test
    public void test3(){
        Response response = RestAssured
                .when()
                .get("https://gorest.co.in/public/v2/users");
       // response.prettyPrint();
        List<Map<String,Object>>list=response.body().as(List.class);
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));
        System.out.println("list.get(0).get(\"name\") = " + list.get(0).get("name"));
        Map<String,Object>map1=list.get(0);
        System.out.println("map1.get(\"name\") = " + map1.get("name"));
    }

}
