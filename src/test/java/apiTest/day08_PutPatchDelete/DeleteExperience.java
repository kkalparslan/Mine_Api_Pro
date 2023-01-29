package apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DeleteExperience {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void deleteExperience() {

        Response response = given().accept(ContentType.JSON)
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzM3Iiwic3RhcnQiOjE2NzQ5OTg3MzcsImVuZHMiOjE2NzU2MDM1Mzd9.FqpydmYQ60ncLmVnHxWRs1eohPMmXFQZQ1Mvnlvu2uQimnAI1ItjqwHLfFvIiJALpjVqS1EHitj3pzLQEeKhrQ")
                .pathParam("id", 258)
                .when().log().all()
                .delete("/experience/delete/{id}").prettyPeek();
    }
}
