package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();

//        authenticationController.createUser("khader", "khaderzatari@gmail.com", "khader1234");
//        authenticationController.createUser("khader", "khaderzatariNew@gmail.com", "khader1234");
        authenticationController.createUser("leon", "leon@gmail.com", "leon1234");
//        HashMap<String, String> user1 = authenticationController.logIn("khaderzatari@gmail.com", "khader1234");
        HashMap<String, String> user2 = authenticationController.logIn("khaderzatari@gmails.com", "khader1234");


    }
}