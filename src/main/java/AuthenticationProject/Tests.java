package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;
import java.util.HashMap;

public class Tests {

    public static void testUpdatePassword() {
        System.out.println("\n\n");
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        HashMap<String, String> user1 = authenticationController.logIn("leon123@email.com", "leon1234");
        String testId = "19e18c0e-84af-4714-b72b-40e4ec5910b5";
        String testToken = user1.get(testId);

        try {
            userController.updatePassword(testId, testToken, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid password inserted");
        }

        try {
            userController.updatePassword(testId, testToken, "123");
        } catch (IllegalArgumentException e) {
            System.out.println("Cant update to the same password");
        }
        try {
            userController.updatePassword(testId, "Wrong token", "ab@c.com");
        } catch (IllegalStateException e) {
            System.out.println("Wrong token Inserted");
        }
        try {
            userController.updatePassword("Wrong Id", testToken, "ab@c.com");
        } catch (IllegalStateException e) {
            System.out.println("Wrong Id inserted");
        }

        System.out.println("Updated the password successfully");
        userController.updatePassword(testId, testToken, "leon5678");
    }

    public static void testUpdateEmail() {
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        HashMap<String, String> user1 = authenticationController.logIn("leon123@email.com", "leon1234");
        String testId = "19e18c0e-84af-4714-b72b-40e4ec5910b5";
        String testToken = user1.get(testId);

        try {
            userController.updateEmail(testId, testToken, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email inserted");
        }

        try {
            userController.updateEmail(testId, testToken, "ab@c.com");
        } catch (IllegalArgumentException e) {
            System.out.println("Cant update to the same email");
        }
        try {
            userController.updateEmail(testId, "Wrong token", "ab@c.com");
        } catch (IllegalStateException e) {
            System.out.println("Wrong token Inserted");
        }
        try {
            userController.updateEmail("Wrong Id", testToken, "ab@c.com");
        } catch (IllegalStateException e) {
            System.out.println("Wrong Id inserted");
        }

        System.out.println("Updated the email successfully");
        userController.updateEmail(testId, testToken, "11newEmail@email1.com");
    }

    public static void testDeleteUser() {
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        HashMap<String, String> user1 = authenticationController.logIn("leon123@email.com", "leon1234");
        String testId = "19e18c0e-84af-4714-b72b-40e4ec5910b5";
        String testToken = user1.get(testId);

        userController.deleteUser(testId, testToken);
        System.out.println("The user was deleted successfully");
    }
}
