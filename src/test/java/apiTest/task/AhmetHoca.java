package apiTest.task;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AhmetHoca {

    /**
     * TASK
     * Given accept type is json
     * And Path param user id is 111
     * When user sends a GET request to /allusers/getbyid/{id}
     * Then the status Code should be 200
     * And Content type json should be "application/json; charset=UTF-8"
     * And user's company should be "GHAN Software"
     * And user's id should be 111
     * And SQL should be the one of the user's skills
     * And user's education should be ODTU, Delft University
     * And user's email should be thomas@test.com
     */
    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testTask(){

        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
//        String company = response.path("company").toString();
//        System.out.println("company = " + company);
//        Assert.assertEquals(company, "GHAN Software"); string değere atınca [] içinde yazdırıyor. assert yapınca failed oluyor.

        JsonPath jsonPath=response.jsonPath();
        String company = jsonPath.getString("company[0]");
        System.out.println("company = " + company);
        Assert.assertEquals(company, "GHAN Software");

        Assert.assertEquals(jsonPath.getInt("id[0]"), 111);
        Assert.assertEquals(jsonPath.getString("skills[0][4]"), "SQL");
        List<String>actualSchool=jsonPath.getList("education[0].school");

        List<String > expectedSchool =new ArrayList<>();
        expectedSchool.add("ODTU");
        expectedSchool.add("Delft University");
        System.out.println("expectedSchool = " + expectedSchool);
        Assert.assertEquals(actualSchool, expectedSchool);

        Assert.assertEquals(jsonPath.getString("email[0]"),"thomas@test.com");


    }


}
