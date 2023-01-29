package apiTest.day08_PutPatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostNewExperience {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newExperience(){
        String experienceBody="{\n" +
                "  \"job\": \"junior Developer\",\n" +
                "  \"company\": \"Kraftech\",\n" +
                "  \"location\": \"Turkey\",\n" +
                "  \"fromdate\": \"2015-05-05\",\n" +
                "  \"todate\": \"YYYY-MM-DD\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"student\"\n" +
                "}";
        Response response=given().accept(ContentType.JSON)
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzM3Iiwic3RhcnQiOjE2NzQ5OTg3MzcsImVuZHMiOjE2NzU2MDM1Mzd9.FqpydmYQ60ncLmVnHxWRs1eohPMmXFQZQ1Mvnlvu2uQimnAI1ItjqwHLfFvIiJALpjVqS1EHitj3pzLQEeKhrQ")
                .body(experienceBody)
                .when()
                .log().all()
                .post("/experience/add").prettyPeek();
        // id 258

    }

}
