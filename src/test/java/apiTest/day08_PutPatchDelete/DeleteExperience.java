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
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzQzIiwic3RhcnQiOjE2NzUwMjU0MjgsImVuZHMiOjE2NzU2MzAyMjh9.HMjB_SRBr2E2QoEWagQ51qQtd69vtSbnvg-SdOoGiPJAmCpyZ93_AGNIxsMTnTzJbF0IQUSAl-RPDYMb1ZBJ1A")
                .pathParam("id", 266)
                .when().log().all()
                .delete("/experience/delete/{id}").prettyPeek();
    }
}
