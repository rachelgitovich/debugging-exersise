package AuthenticationProject.Controllers;

import AuthenticationProject.Services.AuthenticationService;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationController {
    private static AuthenticationController authenticationController;
    private static AuthenticationService authenticationService;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{8,20}$");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");

    private AuthenticationController() {
        authenticationService = AuthenticationService.getInstance();
    }

    public static synchronized AuthenticationController getInstance() {
        if (authenticationController == null) {
            authenticationController = new AuthenticationController();
        }
        return authenticationController;
    }

    public HashMap<String, String> logIn(String email, String password) {

        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (!emailMatchFound) {
            throw new IllegalArgumentException("write email properly example@ex.com");
        }
        if (!passwordMatchFound) {
            throw new IllegalArgumentException("password isn't proper password");
        }
        if (authenticationService.checkIfEmailExists(email)) {
            return authenticationService.logIn(email, password);
        }
        return null;


    }

    public boolean authUser(String id, String token) {
        if (token.length() != 18) {
            throw new IllegalArgumentException("the token is not valid");
        }
        return authenticationService.authUser(id, token);
    }

    public void createUser(String name, String email, String password) {
        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (!emailMatchFound) {
            throw new IllegalArgumentException("wrong email");
        }
        if (!passwordMatchFound) {
            throw new IllegalArgumentException("wrong password");
        }


        authenticationService.createUser(name, email, password);
    }


}
