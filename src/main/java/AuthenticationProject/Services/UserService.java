package AuthenticationProject.Services;

import AuthenticationProject.UserRepository.UserRepository;

public class UserService {

    private static UserService userService;
    private static AuthenticationService authService;


    private UserService(
    ) {}

    public static synchronized UserService getInstance()
    {
        if (userService==null){
            userService = new UserService();
        }
        return userService;
    }

    public void updateEmail(int id, String email) {
        UserRepository.updateEmail(int id, String email);
    }

    public void updatePassword(int id, String password) {
        UserRepository.updatePassword(int id, String password);
    }

    public void updateUserName(int id, String username) {
        UserRepository.updateUserName(int id, String username);
    }
    public void deleteUser(int id ){
        UserRepository.deleteUser(int id);
    }
}