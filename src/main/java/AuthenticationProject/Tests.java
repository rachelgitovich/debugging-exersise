package AuthenticationProject;

import AuthenticationProject.Controllers.AuthenticationController;
import AuthenticationProject.Controllers.UserController;
import AuthenticationProject.Services.AuthenticationService;

public class Tests {

    public static void testUpdatePassword(){
        System.out.println("\n\n");
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        String id = "db9c4062-937e-45bf-abee-1b33f0408501";
        String token = AuthenticationService.userTokens.get(id);

        try{
            userController.updatePassword(id,token, "");
        }catch(IllegalArgumentException e){
            System.out.println("Invalid password inserted");
        }

        try{
            userController.updatePassword(id,token, "123");
        }catch(IllegalArgumentException e){
            System.out.println("Cant update to the same password");
        }
        try{
            userController.updatePassword(id,"Wrong token", "ab@c.com");
        }catch(IllegalStateException e){
            System.out.println("Wrong token Inserted");
        }
        try{
            userController.updatePassword("Wrong Id",token, "ab@c.com");
        }catch(IllegalStateException e){
            System.out.println("Wrong Id inserted");
        }

        System.out.println("Updated the password successfully");
        userController.updatePassword(id,token, "leon5678");
    }

    public static void testUpdateEmail(){
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        authenticationController.logIn("7newEmail@email1.com","123");
        String id = "db9c4062-937e-45bf-abee-1b33f0408501";
        String token = AuthenticationService.userTokens.get(id);

        try{
            userController.updateEmail(id,token, "");
        }catch(IllegalArgumentException e){
            System.out.println("Invalid email inserted");
        }

        try{
            userController.updateEmail(id,token, "ab@c.com");
        }catch(IllegalArgumentException e){
            System.out.println("Cant update to the same email");
        }
        try{
            userController.updateEmail(id,"Wrong token", "ab@c.com");
        }catch(IllegalStateException e){
            System.out.println("Wrong token Inserted");
        }
        try{
            userController.updateEmail("Wrong Id",token, "ab@c.com");
        }catch(IllegalStateException e){
            System.out.println("Wrong Id inserted");
        }

        System.out.println("Updated the email successfully");
        userController.updateEmail(id,token, "8newEmail@email1.com");
    }

    public static void testDeleteUser() {
        UserController userController = UserController.getInstance();
        AuthenticationController authenticationController = AuthenticationController.getInstance();
        authenticationController.logIn("8newEmail@email1.com","leon5678");
        String id = "db9c4062-937e-45bf-abee-1b33f0408501";
        String token = AuthenticationService.userTokens.get(id);

        userController.deleteUser(id,token);
    }
}
