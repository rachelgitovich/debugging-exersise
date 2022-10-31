package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;
import AuthenticationProject.Services.AuthenticationService;
import AuthenticationProject.UserRepository.UserRepository;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

//        UserRepository.checkIfUserExists("sdfs","sdfsf");

//        AuthenticationController.createUser("leon", "leon@gmail.com", "leon123");
        //HashMap<String, String> res = AuthenticationController.logIn("haitham@gmail.com", "password");
//        System.out.println(res.get("29982e27-105a-403a-8fd3-d6b8756499a3"));
        //boolean test = AuthenticationController.authUser("29982e27-105a-403a-8fd3-d6b8756499a3", res.get("29982e27-105a-403a-8fd3-d6b8756499a3"));
        //System.out.println(test);
//


//        Tests.testUpdateEmail();
        //Tests.testUpdatePassword();
        //Tests.testDeleteUser();

        //UserRepository.checkIfUserExists("sdfs","sdfsf");

        AuthenticationController authController = AuthenticationController.getInstance();
//        authController.createUser("khader", "khaderzatari@gmail.com", "khader1234"); works 👌
        //====
        HashMap<String, String> user1 = authController.logIn("khaderzatari@gmail.com", "khader1234");
        HashMap<String, String> user2 = authController.logIn("khaderzatari@gmail.com", "khader1234");
        System.out.println(user1.entrySet());
        System.out.println(user2.entrySet());
        //====
        String userId = "8a0bc5ec-5ef7-487a-a4e2-198e9e5d9f75";
        boolean isAuth = authController.authUser(userId, user1.get(userId));
        System.out.println(isAuth);


    }
}