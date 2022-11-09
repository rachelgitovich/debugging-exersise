package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;

public class Main {
    public static void main(String[] args) {

        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();


        authenticationController.createUser("Yossi Gitovich", "yossi@email.com", "yossi1234");
//        Tests.testUpdateEmail();
//        Tests.testUpdatePassword();
//        Tests.testDeleteUser();

    }
}