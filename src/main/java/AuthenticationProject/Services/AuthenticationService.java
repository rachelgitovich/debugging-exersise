package AuthenticationProject.Services;

import AuthenticationProject.User;
import AuthenticationProject.UserRepository.UserRepository;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AuthenticationService {
    private static AuthenticationService authenticationService;

    private AuthenticationService() {
    }

    public static synchronized AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }
        return authenticationService;
    }

    static HashMap<String, String> userTokens = new HashMap<>();
    ;


    public static boolean authUser(String id, String token) {
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue().equals(token);
            }
        }
        return false;
    }

    public static String createToken() {
        return getSaltString(18);
    }

    public static HashMap<String, String> logIn(String email, String password) {
        //check if the user is not loged in.
        //check if it's user -> return id
        String id = UserRepository.checkIfUserExists(email, password);
        if (userTokens.containsKey(id)) {
            throw new IllegalArgumentException("the user is logged in ");
        }
        String token = createToken();
        userTokens.put(id, token);
        HashMap<String, String> res = new HashMap<>();

        res.put(id, token);

        return res;
    }

    public static void createUser(String name, String email, String password) {
        User user = new User(name, email, password);
        UserRepository.createUser(user);//if user is regestered make anything


    }

    public static void deleteUserFromMap(int id) {
        userTokens.remove(id);
    }

    public static String getSaltString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < stringLength) {
            int index = (int) (ThreadLocalRandom.current().nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
