package AuthenticationProject.UserRepository;

import AuthenticationProject.Debugger;
import AuthenticationProject.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserRepository {
    private static Logger logger = LogManager.getLogger(UserRepository.class.getName());
    private static final String path = "../AuthenticationProject/src/main/java/AuthenticationProject/UserRepository/users/";
    private static final Gson gson = new Gson();
    private static UserRepository userRepository;

    private UserRepository() {
    }

    public static synchronized UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    private User fetchUser(String filePath) {
        logger.info("fetching user");
        JsonReader reader = null;
        User data = null;
        Type USER_TYPE = User.class;
        try {
            reader = new JsonReader(new FileReader(filePath));
            data = gson.fromJson(reader, USER_TYPE); // contains the whole users list
            reader.close();
            if (data == null) {
                logger.error("user not found");
                throw new RuntimeException("user not found");
            }
        } catch (IOException e) {
            logger.error("user's file not found'");
            throw new RuntimeException("user's file not found");
        }
        return data;
    }


    public void createUser(User user) {
        logger.info("creating user");
        PrintWriter writer = null;
        String usersJson = gson.toJson(user);
        System.out.println(usersJson);
        try {
            File file = new File(path);
            writer = new PrintWriter(path + user.getId() + ".json", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.fatal("failed to create user");
            throw new RuntimeException("failed to create user");
        }
        writer.print(usersJson);
        writer.close();
    }


    public boolean checkIfEmailExists(String email) {
        logger.info("checking if email exist");
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (fetchUser(file.getPath()).getEmail().equals(email)) {
                logger.warn("email exists");
                return true;
            }
        }
        logger.warn("email does not exist");
        return false;
    }

    public boolean checkIfUserExists(String email, String password) {
        logger.info("checking if user exists");
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                User user=fetchUser(file.getPath());
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    logger.warn("user exists");
                    return true;
                }
            }
        }
        logger.warn("user does not exist");
        return false;
    }

    public String getIdByEmail(String email) {
        logger.info("getting id by email");
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file :
                listOfFiles) {
            User user = fetchUser(file.getPath());
            if (user.getEmail().equals(email)) {
                return user.getId();
            }
        }
        logger.error("user not found");
        throw new IllegalArgumentException("user not found");
    }

    public void updateEmail(String id, String email) {
        logger.info("updating email");
        User user = fetchUser(path + id + ".json");
        user.setEmail(email);
        PrintWriter writer = null;
        String usersJson = gson.toJson(user);
        try {
            writer = new PrintWriter(path + id + ".json", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.fatal("failed to update user");
            throw new RuntimeException("failed to update user");
        }
        writer.print(usersJson);
        writer.close();
    }

    public void updateName(String id, String name) {
        logger.info("updating name");
        User user = fetchUser(path + id + ".json");
        user.setName(name);
        PrintWriter writer = null;
        String usersJson = gson.toJson(user);
        try {
            writer = new PrintWriter(path + id + ".json", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.error("failed to update user");
            throw new RuntimeException("failed to update user");
        }
        writer.print(usersJson);
        writer.close();
    }

    public void updatePassword(String id, String password) {
       logger.info("updating password");
        User user = fetchUser(path + id + ".json");
        user.setPassword(password);
        PrintWriter writer = null;
        String usersJson = gson.toJson(user);
        try {
            writer = new PrintWriter(path + id + ".json", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.error("failed to update user");
            throw new RuntimeException("failed to update user");
        }
        writer.print(usersJson);
        writer.close();
    }

    public void deleteUser(String id) {
        logger.info("deleting user");
        try {
            Files.delete(Paths.get(path + id + ".json"));
        } catch (IOException e) {
            logger.error("failed to delete user");
            throw new RuntimeException("failed to delete user");
        }

    }


}
