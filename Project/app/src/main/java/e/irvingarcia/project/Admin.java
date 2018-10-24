package e.irvingarcia.project;

public class Admin {
    String username;
    String password;
    public Admin(String user,String pass){
        username=user;
        password=pass;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
