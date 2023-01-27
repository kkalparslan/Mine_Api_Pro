package apiTest.day6_Pojo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GsonTest {
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
    public void jsonToMap(){
        Gson gson=new Gson();
        String myJsonBody= "{\n" +
                "     \"id\": 9222968140497201319,\n" +
                "     \"username\": \"Alpimka71\",\n" +
                "     \"firstName\": \"Alpim\",\n" +
                "     \"lastName\": \"Ka\",\n" +
                "     \"email\": \"alpim@gmail.com\",\n" +
                "     \"password\": \"alp123\",\n" +
                "     \"phone\": \"33304440555\",\n" +
                "     \"userStatus\": 10001\n" +
                "     }";
        System.out.println("myJsonBody = " + myJsonBody);

        // gson converting to map
        Map<String, Object>dataMap=gson.fromJson(myJsonBody,Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        PetStoreUser oneUser=gson.fromJson(myJsonBody, PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //Serialization
        //java collection or POJO yo Json
        PetStoreUser petStoreUser=new PetStoreUser(9, "Alpimka71","Alpim","Ka",
                "alpim@gmail.com","alp123","33304440555",10001);

        String jsonUser=gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);
    }

}
