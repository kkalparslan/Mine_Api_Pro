package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetRequestWithParameterErn {

    @Test
    public void test(){
        Response response= RestAssured
                .when()
                .get();
    }
}
