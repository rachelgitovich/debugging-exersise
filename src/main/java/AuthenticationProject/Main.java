package AuthenticationProject;

import AuthenticationProject.UserRepository.UserRepository;

public class Main {
    public static void main(String[] args) {

        User user=new User("Rivka","sddfdff","123");
        UserRepository.createUser(user);
    }
}