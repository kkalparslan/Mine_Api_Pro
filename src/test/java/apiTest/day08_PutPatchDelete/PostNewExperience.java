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
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzQzIiwic3RhcnQiOjE2NzUwMjU0MjgsImVuZHMiOjE2NzU2MzAyMjh9.HMjB_SRBr2E2QoEWagQ51qQtd69vtSbnvg-SdOoGiPJAmCpyZ93_AGNIxsMTnTzJbF0IQUSAl-RPDYMb1ZBJ1A")
                .body(experienceBody)
                .when()
                .log().all()
                .post("/experience/add").prettyPeek();
        // id 258

    }

}
