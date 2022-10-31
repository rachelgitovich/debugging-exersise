package AuthenticationProject.Controllers;

import AuthenticationProject.Services.AuthenticationService;
import AuthenticationProject.UserRepository.UserRepository;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationController {
    private static AuthenticationController authenticationController;
    private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{8,20}$");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");

    private AuthenticationController() {
        authenticationService = AuthenticationService.getInstance();
        userRepository=UserRepository.getInstance();
    }

    public static synchronized AuthenticationController getInstance() {
        if (authenticationController == null) {
            authenticationController = new AuthenticationController();
        }
        return authenticationController;
    }

    public  HashMap<String, String> logIn(String email, String password) {

        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (emailMatchFound && passwordMatchFound) {
            if (userRepository.checkIfUserExists(email, password)) {
                return authenticationService.logIn(email, password);
            }
            throw new IllegalArgumentException("You cant change the email to the same one");
        }
        throw new IllegalArgumentException("write email properly example@ex.com");

    }

    public  boolean authUser(String id, String token) {
        if (token.length() != 18) {
            throw new IllegalArgumentException("the token is not valid");
        }
        return authenticationService.authUser(id, token);
    }

    public  void createUser(String name, String email, String password) {
        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (!(emailMatchFound && passwordMatchFound)) {
            throw new IllegalArgumentException("wrong email and password");
        }
        authenticationService.createUser(name, email, password);
    }


}

///in the repo should be map<Integer, User> users;
// if we update user, he updates the map in the repo, then we wirte it into file.
//
