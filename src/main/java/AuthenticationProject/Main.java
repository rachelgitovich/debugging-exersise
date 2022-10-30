package AuthenticationProject;

import AuthenticationProject.Controllers.UserController;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        UserController userController = UserController.getInstance();
        userController.updateEmail(1,"1","1@1");

    }
}