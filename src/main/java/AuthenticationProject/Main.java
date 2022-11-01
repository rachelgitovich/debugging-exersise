package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();



        //Tests.testUpdateEmail();
        //Tests.testUpdatePassword();
        Tests.testDeleteUser();

    }
}