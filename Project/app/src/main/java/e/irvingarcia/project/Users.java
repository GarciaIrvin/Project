package e.irvingarcia.project;

public class Users {
    private String user;
    private String pass;
    private String first;
    private String last;
    private String city;
    private String street;
    private String postal;

    public  Users(String user,String pass,String first,String last, String street, String city,String postal){
        this.user=user;
        this.pass=pass;
        this.first=first;
        this.last=last;
        this.street=street;
        this.postal=postal;
        this.city=city;
    }
    public Users(){}

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getPass() {
        return pass;
    }

    public String getPostal() {
        return postal;
    }

    public String getStreet() {
        return street;
    }

    public String getUser() {
        return user;
    }
    public String getCity(){
        return city;
    }
}
