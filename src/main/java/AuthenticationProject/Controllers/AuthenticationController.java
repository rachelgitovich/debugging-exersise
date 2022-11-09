package AuthenticationProject.Controllers;

import AuthenticationProject.Debugger;
import AuthenticationProject.Services.AuthenticationService;
import AuthenticationProject.UserRepository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationController {
    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());
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
        Debugger.log("login");
        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (!emailMatchFound) {
            logger.error("incorrect email");
            throw new IllegalArgumentException("write email properly example@ex.com");
        }
        if (!passwordMatchFound) {
            logger.error("incorrect password");
            throw new IllegalArgumentException("password isn't proper password");
        }
        if (authenticationService.checkIfEmailExists(email)) {
            return authenticationService.logIn(email, password);
        }
        logger.fatal("user not registered");
        throw new IllegalArgumentException("user is not registered");


    }

    public boolean authUser(String id, String token) {
        Debugger.log("auth user");
        if (token.length() != 18) {
            logger.error("invalid token");
            throw new IllegalArgumentException("the token is not valid");
        }
        return authenticationService.authUser(id, token);
    }

    public void createUser(String name, String email, String password) {
        logger.info("create user");
        Matcher matchMail = emailPattern.matcher(email);
        Matcher matchPassword = PASSWORD_PATTERN.matcher(password);

        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();

        if (!emailMatchFound) {
            logger.error("wrong email");
            throw new IllegalArgumentException("wrong email");
        }
        if (!passwordMatchFound) {
            logger.error("wrong password");
            throw new IllegalArgumentException("wrong password");
        }


        authenticationService.createUser(name, email, password);
    }


}
