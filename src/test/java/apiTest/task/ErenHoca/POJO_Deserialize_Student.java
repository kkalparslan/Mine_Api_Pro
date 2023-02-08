package apiTest.task.ErenHoca;


import apiTest.task.ErenHoca.apipojotemplates.Students;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class POJO_Deserialize_Student {
    //TASK
    //base url = https://gorest.co.in/
    //end point = /public/v2/users
    //path parameter = {id} --> 240530
    //send a get request with the above credentials
    //parse to Json object to pojo (custom java class)
    //verify that the body below
    /*
    {
    "id": 240530,
    "name": "Ms. Sujata Tandon",
    "email": "sujata_tandon_ms@crona.co",
    "gender": "female",
    "status": "active"
    }
     */
    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 240530)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");
        //response.prettyPrint();

        Students students1=response.body().as(Students.class);
        System.out.println("students1.toString() = " + students1.toString());
        System.out.println("students1.getId() = " + students1.getId());
        System.out.println("students1.getName() = " + students1.getName());
        System.out.println("students1.getEmail() = " + students1.getEmail());
        System.out.println("students1.getGender() = " + students1.getGender());
        System.out.println("students1.getStatus() = " + students1.getStatus());
    }
}
