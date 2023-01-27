package apiTest.day5_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonToJavaCollection {

    /**
     * https://demoqa.com/swagger/ demoqa sitesi swagger ı
     */

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void userToMap(){
        Response response=given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11");
        Assert.assertEquals(response.statusCode(), 401);

        Map<String, Object>jsonMap=response.body().as(Map.class); // response body nin yapısı key/value
        // şeklinde olduğu için oluşturduğumuz java collection ı da map yaptık (convert edeceğimiz yapının şekli önemli)
        System.out.println(jsonMap);

        // verify message
        String message= (String) jsonMap.get("message");
        System.out.println("message = " + message);
        Assert.assertEquals(message, "User not authorized!");

        // verify code
        String code= (String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code, "1200");
    }

    @Test
    public void allUserToMap(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().get("allusers/alluser");
        Assert.assertEquals(response.statusCode(), 200);

        // we need to de-serialiaze Json response to java collection
        // birden fazla json body bulunduğu için list of map yapmamız gerekiyor

        List<Map<String, Object>> allUserMap=response.body().as(List.class);  // convert edeceğimiz
        // yapı buradaki gibi map in içinde birden fazla list halinde obje varsa list of map yapıyoruz.
        System.out.println("allUserMap = " + allUserMap);

        // 2. kullanıcının adını assert edelim
        System.out.println(allUserMap.get(1).get("name"));
        Assert.assertEquals(allUserMap.get(1).get("name"), "isa akyuz");
//        String name = (String) allUserMap.get(1).get("name"); assert için 2. yol
//        Assert.assertEquals(name, "isa akyuz");

        System.out.println(allUserMap.get(0).get("skills"));

        // 1. kişinin 1. skills ini getirelim
        //System.out.println(allUserMap.get(0).get("skills[0]")); bu şekilde görmüyor çünkü response body içi list olarak gelmiş
        List<String>skills= (List<String>) allUserMap.get(0).get("skills"); // eğer response yapısı map değilde sadece list şeklindeyse
        // bu defa da bir list yapısının içine çekiyoruz. önemli hususlardan biri list/map/lisy of map oluştururken assigment kısmını
        // response.body().as metodundan sonra(List yada Map.class) olarak yapmak
        System.out.println(skills.get(0));
        Assert.assertEquals(skills.get(0), "PHP");

        List<Map<String, Object>>experienceOfListMap= (List<Map<String, Object>>) allUserMap.get(0).get("experience");
        System.out.println(experienceOfListMap);
        System.out.println(experienceOfListMap.get(0).get("job"));


    }
}
