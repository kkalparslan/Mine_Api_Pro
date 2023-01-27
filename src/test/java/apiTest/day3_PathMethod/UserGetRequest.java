package apiTest.day3_PathMethod;


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

        //response.prettyPrint

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //verify the body
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        //verify the header
        Assert.assertEquals(response.header("Content-Length"),"636");
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");
        Assert.assertEquals(response.header("Vary"),"Accept-Encoding,User-Agent");
        // response header daki tüm başlıkların asserti yapılabilir.

        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
    }

    /**
     *       TASK
     *     Given accept type json
     *     And query  parameter value pagesize 50
     *     And query  parameter value page 1
     *     When user sends GET request to /allusers/alluser
     *     Then response status code should be 200
     *     And response content-type application/json; charset=UTF-8
     *     Verify the first id is 1
     *     Verify the first name is aFm
     *     Verify the last id is 102
     *     Verify the last name is GHAN
     */

    @Test
    public void getQueryParamWithIdAndName(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .get(exlabURL+"/allusers/alluser");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);
        // verify content type
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertEquals(response.getHeader("Content-type"),"application/json; charset=UTF-8");
        // verify the first ID is 1
        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);
        Assert.assertEquals(firstId, 1);
        //Assert.assertEquals(response.path("id[0]"), "1");//integer değeri stringe cast yapamıyor burada.
        // bu nedenle id isimli bir obje oluşturp daha sonra assert yaptık

        // verify first name is aFm
        String firstName=response.path("name[0]");
        System.out.println("firstName = " + firstName);
        Assert.assertEquals(firstName, "aFm");

        //verify last ID is 102
        int lastId=response.path("id[-1]");
        System.out.println("lastId = " + lastId);
        Assert.assertEquals(lastId, 102);

        //verify last name is GHAN
        String lastName=response.path("name[-1]");
        System.out.println("lastName = " + lastName);
        Assert.assertEquals(lastName, "GHAN");
    }

    /**
     *     Given accept type json
     *     When user sends a get request to https://demoqa.com/BookStore/v1/Books
     *                                      https://demoqa.com/BookStore/v1/Books
     *     Then status code should be 200
     *     And content type should be application/json; charset=utf-8
     *     And the first book isbn should be 9781449325862
     *     And the first book publisher should be O'Reilly Media
     *     https://demoqa.com/swagger/#/BookStore/BookStoreV1BooksGet
     */
    @Test
    public void testDemoqa(){
        Response response=given().accept(ContentType.JSON)
                .get("https://demoqa.com/BookStore/v1/Books");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
//        Assert.assertEquals(response.path("books.isbn[0]"), "9781449325862");
//        Assert.assertEquals(response.path("books.publisher[0]"), "O'Reilly Media"); bu şekilde direk
//        assert de yapabilir aşağıda olduğu gibi path leri bir variable atayıp daha sonrada assert edebiliriz.

        String isbn=response.path("books.isbn[0]");
        String publisher=response.path("books.publisher[0]");

        Assert.assertEquals(isbn, "9781449325862");
        Assert.assertEquals(publisher, "O'Reilly Media");

    }


}
