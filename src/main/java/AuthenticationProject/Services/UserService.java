package AuthenticationProject.Services;

public class UserService {

    private static UserService userService;

    private UserService(
    ) {}

    public static synchronized UserService getInstance()
    {
        if (userService==null){
            userService = new UserService();
        }
        return userService;
    }

    public void updateEmail(String email) {
    }

    public void updatePassword(String password) {
    }

    public void updateUserName(String username) {

    }
}