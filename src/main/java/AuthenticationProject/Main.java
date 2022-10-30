package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;
import AuthenticationProject.Services.AuthenticationService;
import AuthenticationProject.UserRepository.UserRepository;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //Tests.testUpdateEmail();
        //Tests.testUpdatePassword();
        //Tests.testDeleteUser();

        UserRepository.checkIfUserExists("sdfs","sdfsf");

    }
}