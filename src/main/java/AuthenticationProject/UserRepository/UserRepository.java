package AuthenticationProject.UserRepository;
import AuthenticationProject.Controllers.UserController;
import AuthenticationProject.Services.UserService;
import AuthenticationProject.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserRepository {
    private static final String path = "src/main/java/AuthenticationProject/UserRepository/users.json";
    private static final Gson gson = new Gson();
    private static UserRepository userRepository;

    private UserRepository() {}

    public static synchronized UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

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

    public void createUser(User user) {
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

    public boolean checkIfEmailExists(String email) {
        List<User> data = fetchUsers();
        return data.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean checkIfUserExists(String email, String password) {
        List<User> data = fetchUsers();
        return data.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public String getIdByEmail(String email) {
        List<User> data = fetchUsers();
        return data.stream().filter(u -> u.getEmail().equals(email)).findFirst().get().getId();
    }

    public void updateEmail(String id, String email) {
        List<User> data = fetchUsers();
        User user = null;
        try {
            user = data.stream().filter(u -> u.getId().equals(id)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User not found");
        }
        data.remove(user);
        user.setEmail(email);
        data.add(user);
        PrintWriter writer = null;
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

    public void updateName(String id, String name) {
        List<User> data = fetchUsers();
        User user = null;
        try {
            user = data.stream().filter(u -> u.getId().equals(id)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User not found");
        }
        data.remove(user);
        user.setName(name);
        data.add(user);
        PrintWriter writer = null;
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

    public void updatePassword(String id, String password) {
        List<User> data = fetchUsers();
        User user = null;
        try {
            user = data.stream().filter(u -> u.getId().equals(id)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User not found");
        }
        data.remove(user);
        user.setPassword(password);
        data.add(user);
        PrintWriter writer = null;
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

    public void deleteUser(String id) {
        List<User> data = fetchUsers();
        User user = null;
        try {
            user = data.stream().filter(u -> u.getId().equals(id)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User not found");
        }
        data.remove(user);
        PrintWriter writer = null;
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
}
