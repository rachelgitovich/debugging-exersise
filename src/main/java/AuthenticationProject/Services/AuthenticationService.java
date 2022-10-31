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

    public static HashMap<String, String> userTokens = new HashMap<>();


    public void createUser(String name, String email, String password) {
        if (UserRepository.checkIfUserExists(email, password)) {
            throw new IllegalArgumentException("the user has already registered");
        }
        User user = new User(name, email, password);
        UserRepository.createUser(user);
    }

    public HashMap<String, String> logIn(String email, String password) {

        String id;
        if (UserRepository.checkIfUserExists(email, password)) {
            id = UserRepository.getIdByEmail(email);
        } else {
            throw new IllegalArgumentException("the user is not valid");
        }
        if (userTokens.containsKey(id)) {
            throw new IllegalArgumentException("the user is logged in ");
        }

        String token = createToken();
        userTokens.put(id, token);
        HashMap<String, String> res = new HashMap<>();
        res.put(id, token);

        return res;
    }

    public boolean authUser(String id, String token) {
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue().equals(token);
            }
        }
        return false;
    }


    public void deleteUserFromMap(String id) {
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

    public static String createToken() {
        return getSaltString(18);
    }

}
