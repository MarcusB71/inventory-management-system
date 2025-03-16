package ca.senecapolytechnic.inventorymanagementsystem.models;

public class Login {
    private String userName;
    private String password;
    private final String DEFAULT_USERNAME = "admin";
    private final String DEFAULT_PASSWORD = "password";

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean validate(){
        return this.userName.equals(DEFAULT_USERNAME) && this.password.equals(DEFAULT_PASSWORD);
    }
}
