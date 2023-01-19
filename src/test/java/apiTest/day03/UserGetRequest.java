package apiTest.day03;


import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserGetRequest {

    String petStoreURL="https://petstore.swagger.io/v2";
    String exlabURL="https://www.krafttechexlab.com/sw/api/v1";



    /**
     * TASK: Get all users
     */

    @Test
    public void getAllUsers(){
        Response response= given().accept(ContentType.JSON)
                .queryParam("pagesize", 5) // kullanıcı (value) sayısını değiştirebiliriz
                .queryParam("page", 1)
                .when().get(exlabURL+"/allusers/alluser");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    /**
     * TASK
     * When user sends a GET request to /allusers/getbyid/111
     * Then Status code should be 200
     * And content type should be application/json; charset=UTF-8
     * And json body should contain Thomas Eduson */

    @Test
    public void allUserGetById(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when().get(exlabURL+"/allusers/getbyid/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        Assert.assertEquals(response.header("Content-Length"),"636");
        Assert.assertEquals(response.header("Vary"),"Accept-Encoding,User-Agent");
        // response header daki tüm başlıkların asserti yapılabilir.
    }
}
