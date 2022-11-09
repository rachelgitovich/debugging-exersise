package AuthenticationProject.Services;

import AuthenticationProject.UserRepository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class.getName());
    private UserService() {
        userRepository = UserRepository.getInstance();
    }


    public static synchronized UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    private static UserService userService;
    private static UserRepository userRepository;



    public void updateEmail(String id, String email) {
        logger.info("update email");
        userRepository.updateEmail(id, email);
    }

    public void updatePassword(String id, String password) {
        logger.info("update password");
        userRepository.updatePassword(id, password);
    }

    public void updateUserName(String id, String username) {
logger.info("update user name");
        userRepository.updateName(id, username);
    }

    public void deleteUser(String id) {
        logger.info("delete user");
        userRepository.deleteUser(id);
    }

    public boolean checkIfEmailExists(String email) {
        logger.info("check if email exists");
        return userRepository.checkIfEmailExists(email);
    }
}