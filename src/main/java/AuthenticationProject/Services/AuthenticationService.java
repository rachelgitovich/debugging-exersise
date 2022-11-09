package AuthenticationProject.Services;

import AuthenticationProject.User;
import AuthenticationProject.UserRepository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AuthenticationService {
    private static Logger logger = LogManager.getLogger(AuthenticationService.class.getName());
    private static AuthenticationService authenticationService;
    private static UserRepository userRepository;

    private AuthenticationService() {
        userRepository = UserRepository.getInstance();
    }

    public static synchronized AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }
        return authenticationService;
    }

    public static HashMap<String, String> userTokens = new HashMap<>();


    public void createUser(String name, String email, String password) {
        logger.info("create user");
        if (userRepository.checkIfEmailExists(email)) {
            logger.error("the user has already registered");
            throw new IllegalArgumentException("the user has already registered");
        }
        User user = new User(name, email, password);
        userRepository.createUser(user);
    }

    public HashMap<String, String> logIn(String email, String password) {
        logger.info("login");
        String id;
        if (userRepository.checkIfUserExists(email, password)) {
            id = userRepository.getIdByEmail(email);
        } else {
            logger.error("the user is not valid");
            throw new IllegalArgumentException("the user is not valid");
        }
        if (userTokens.containsKey(id)) {
            logger.error("the user is already logged in");
            throw new IllegalArgumentException("the user is already logged in ");
        }
        String token = createToken();
        userTokens.put(id, token);
        HashMap<String, String> res = new HashMap<>();
        res.put(id, token);
        return res;
    }

    public boolean authUser(String id, String token) {
        logger.info("auth user");
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue().equals(token);
            }
        }
        logger.info("user is not authenticated");
        return false;
    }


    public void deleteUserFromMap(String id) {
        logger.info("delete user from map");
        userTokens.remove(id);
    }

    private static String getSaltString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < stringLength) {
            int index = (int) (ThreadLocalRandom.current().nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();

    }

    public boolean checkIfEmailExists(String email) {
        logger.info("check if email exists");
        return userRepository.checkIfEmailExists(email);
    }

    public static String createToken() {
        logger.info("create token");
        return getSaltString(18);
    }

}
