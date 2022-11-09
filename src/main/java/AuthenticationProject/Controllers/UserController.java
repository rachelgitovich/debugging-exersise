package AuthenticationProject.Controllers;

import AuthenticationProject.Services.*;
import AuthenticationProject.UserRepository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.*;

public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class.getName());
    private static UserController userController;
    private static UserService userService;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{8,20}$");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
    private static AuthenticationService authenticationService;


    private UserController() {
        userService = UserService.getInstance();
        authenticationService = AuthenticationService.getInstance();
    }

    public static synchronized UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public void updateUserName(String id, String token, String userName) {
        logger.info("update user name");
        if (authenticateUser(id, token)) {
            userService.updateUserName(id, userName);
        }
        logger.error("the user was not authenticated");
        throw new IllegalStateException("The user was not authenticated");
    }

    public void updateEmail(String id, String token, String email) {
        logger.info("update email");
        if (authenticateUser(id, token)) {
            if (validateEmail(email)) {
                userService.updateEmail(id, email);
            } else {
                logger.error("invalid email");
                throw new IllegalArgumentException("Invalid email inserted");
            }
        } else {
            logger.error("the user was not authenticated");
            throw new IllegalStateException("The user was not authenticated");
        }

    }

    public void updatePassword(String id, String token, String password) {
        logger.info("update password");
        if (authenticateUser(id, token)) {
            if (validatePassword(password)) {
                userService.updatePassword(id, password);
            } else {
                logger.error("Invalid password");
                throw new IllegalArgumentException("Invalid password inserted");
            }
        } else {
            logger.error("The user was not authenticated");
            throw new IllegalStateException("The user was not authenticated");
        }

    }

    public boolean authenticateUser(String id, String token) {
        logger.info("authenticate user");
        return authenticationService.authUser(id, token);
    }

    public boolean validateEmail(String email) {
        logger.info("validate email");
        Matcher m = emailPattern.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound) {
            if (userService.checkIfEmailExists(email)) {
                logger.error("you cant change the email to the same one");
                throw new IllegalArgumentException("You cant change the email to the same one");
            }
            return true;
        }
        return false;
    }

    public boolean validatePassword(String password) {
        logger.info("validate password");
        Matcher m = PASSWORD_PATTERN.matcher(password);
        boolean matchFound = m.matches();
        if (matchFound) {
            logger.warn("password is not valid");
            return true;
        }
        return false;
    }

    public void deleteUser(String id, String token) {
        logger.info("delete user");
        if (authenticateUser(id, token)) {
            authenticationService.deleteUserFromMap(id);
            userService.deleteUser(id);
        } else {
            logger.error("the user was not authenticated");
            throw new IllegalStateException("The user was not authenticated");
        }
    }
}