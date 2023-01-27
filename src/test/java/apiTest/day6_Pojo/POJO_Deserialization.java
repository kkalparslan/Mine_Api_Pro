package apiTest.day6_Pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class POJO_Deserialization {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://petstore.swagger.io/v2";
    }

    /**
     {
     "id": 9222968140497201319,
     "username": "Alpimka71",
     "firstName": "Alpim",
     "lastName": "Ka",
     "email": "alpim@gmail.com",
     "password": "alp123",
     "phone": "33304440555",
     "userStatus": 10001
     }
     */

    @Test
    public void oneUserPetStore(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .pathParam("username", "Alpimka71")
                .when().get("/user/{username}");
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("response.statusCode() = " + response.statusCode());

        // json to our petStore object
        PetStoreUser oneUser=response.body().as(PetStoreUser.class);

        // print all information
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getUsername() = " + oneUser.getUsername());
        System.out.println("oneUser.getFirstName() = " + oneUser.getFirstName());
        System.out.println("oneUser.getLastName() = " + oneUser.getLastName());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getPassword() = " + oneUser.getPassword());
        System.out.println("oneUser.getPhone() = " + oneUser.getPhone());
        System.out.println("oneUser.getUserStatus() = " + oneUser.getUserStatus());

        Assert.assertEquals(oneUser.getId(),9.2229681404972052E18);
        Assert.assertEquals(oneUser.getUsername(), "Alpimka71");
        Assert.assertEquals(oneUser.getFirstName(), "Alpim");
        Assert.assertEquals(oneUser.getLastName(), "Ka");
        Assert.assertEquals(oneUser.getEmail(), "alpim@gmail.com");
        Assert.assertEquals(oneUser.getPassword(), "alp123");
        Assert.assertEquals(oneUser.getPhone(), "33304440555");
        Assert.assertEquals(oneUser.getUserStatus(), 10001);
    }


}
