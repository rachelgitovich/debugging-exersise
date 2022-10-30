package AuthenticationProject.UserRepository;

import AuthenticationProject.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class UserRepository {
    public void createUser(User user){
        Gson gson = new Gson();
        JsonReader reader = null;
        Type USER_TYPE = new TypeToken<List<User>>() {}.getType();
        try {
            reader = new JsonReader(new FileReader("users.json"));
        } catch (FileNotFoundException e) {
            try {
                reader = new JsonReader(new FileReader("users.json"));
            } catch (IOException ex) {
                throw new RuntimeException("repository file not exist, failed to create repository file");
            }
            List<User> data = gson.fromJson(reader, USER_TYPE); // contains the whole users list
            data.add(user);
        }
        gson.fromJson(reader, USER_TYPE);
    }
}
