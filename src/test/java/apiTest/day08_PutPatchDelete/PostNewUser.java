package apiTest.day08_PutPatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostNewUser {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newUser() {

        String body = "{\n" +
                "  \"name\": \"Kemal1\",\n" +
                "  \"email\": \"kemal@krafttechexlab.com\",\n" +
                "  \"password\": \"123\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"2\"\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .body(body)
                .when()
                .log().all()
                .post("/allusers/register").prettyPeek();
        String token = response.path("token");
        System.out.println("token = " + token);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzM3Iiwic3RhcnQiOjE2NzQ5OTg3MzcsImVuZHMiOjE2NzU2MDM1Mzd9.FqpydmYQ60ncLmVnHxWRs1eohPMmXFQZQ1Mvnlvu2uQimnAI1ItjqwHLfFvIiJALpjVqS1EHitj3pzLQEeKhrQ
        // new token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzQzIiwic3RhcnQiOjE2NzUwMjU0MjgsImVuZHMiOjE2NzU2MzAyMjh9.HMjB_SRBr2E2QoEWagQ51qQtd69vtSbnvg-SdOoGiPJAmCpyZ93_AGNIxsMTnTzJbF0IQUSAl-RPDYMb1ZBJ1A

    }


}
