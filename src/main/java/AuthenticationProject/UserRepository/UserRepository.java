package AuthenticationProject.UserRepository;

import AuthenticationProject.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private static String path = "src/main/java/AuthenticationProject/UserRepository/users.json";
    private static Gson gson = new Gson();

    private static List<User> fetchUsers() {
        JsonReader reader = null;
        Type USER_TYPE = new TypeToken<List<User>>() {
        }.getType();
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("users.json file not found");
        }
        List<User> data = gson.fromJson(reader, USER_TYPE); // contains the whole users list
        if (data == null) {
            data = new ArrayList<User>();
        }
        return data;
    }

    public static void createUser(User user) {
        List<User> data = fetchUsers();
        PrintWriter writer = null;
        data.add(user);
        String usersJson = gson.toJson(data);
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("users.json file not found");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("failed to save user");
        }
        writer.print(usersJson);
        writer.close();
    }

    public static boolean checkIfEmailExists(String email) {
        List<User> data = fetchUsers();
        return data.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public static boolean checkIfUserExists(String email, String password) {
        List<User> data = fetchUsers();
        return data.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }
}
