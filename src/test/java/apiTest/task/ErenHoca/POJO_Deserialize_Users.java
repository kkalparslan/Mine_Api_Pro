package apiTest.task.ErenHoca;

public class POJO_Deserialize_Users {

    /* TASK
    base url = https://www.krafttechexlab.com/sw/api/v1
    end point /allusers/getbyid/{id}
    id parameter value is 1
    send the GET request
    then status code should be 200
    get all data into a custom class (POJO) by de-serilization
    */

//    @Test
//    public void test1(){
//        Response response = RestAssured
//                .given()
//                .pathParam("id", 1)
//                .when()
//                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}");
//        //de-serilization
//        //POJO --> Plain Old Java Object
//        //get the data and put inside the custom java class (POJO)
//        User[] user = response.body().as(User[].class);
//        System.out.println("user.length = " + user.length);
//        System.out.println("user[0].getId() = " + user[0].getId());
//        System.out.println("user[0].getName() = " + user[0].getName());
//        System.out.println("user[0].getAvatar() = " + user[0].getAvatar());
//        System.out.println("user[0].getSkills().get(0) = " + user[0].getSkills().get(0));
//        List<String> lists = user[0].getSkills();
//        System.out.println("lists.get(1) = " + lists.get(1));
//        List<Education> education = user[0].getEducation();
//        System.out.println("education.get(0) = " + education.get(0));
//        Education education1 = education.get(0);
//
//        System.out.println("education1.getDate() = " + education1.getDate());
//        System.out.println("education1.getDescription() = " + education1.getDescription());
//    }



}
