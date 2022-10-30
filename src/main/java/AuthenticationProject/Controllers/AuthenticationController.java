package AuthenticationProject.Controllers;

import AuthenticationProject.Services.AuthenticationService;

import java.util.HashMap;

public class AuthenticationController {
    private static AuthenticationController authenticationController;
    private static AuthenticationService authenticationService;

    private AuthenticationController() {
        authenticationService = AuthenticationService.getInstance();
    }

    public static synchronized AuthenticationController getInstance() {
        if (authenticationController == null) {
            authenticationController = new AuthenticationController();
        }
        return authenticationController;
    }


    public static HashMap<String, String> logIn(String email, String password) {

        return AuthenticationService.logIn(email, password);
    }

    public static boolean authUser(String id, String token) {
        return AuthenticationService.authUser(id, token);
    }

    public static void createUser(String name, String email, String password) {
        AuthenticationService.createUser(name, email, password);
    }


}
