public class User {
    private String name;
    private String id;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id= String.valueOf(java.util.UUID.randomUUID());
    }
}
