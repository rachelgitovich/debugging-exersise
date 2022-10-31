package AuthenticationProject.Services;

import AuthenticationProject.UserRepository.UserRepository;

public class UserService {

    private static UserService userService;
    private static UserRepository userRepository;

    private UserService() {
        userRepository = UserRepository.getInstance();
    }


    public static synchronized UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void updateEmail(String id, String email) {
        userRepository.updateEmail(id, email);
    }

    public void updatePassword(String id, String password) {
        userRepository.updatePassword(id, password);
    }

    public void updateUserName(String id, String username) {

        userRepository.updateName(id, username);
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }
}