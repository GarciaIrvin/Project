package e.irvingarcia.project;

public class ServiceProvider {
    private Users user;
    private boolean completed=false;

    public  ServiceProvider(Users user){
        this.user=user;
    }
    public ServiceProvider(){}

    public Users getUser() {
        return user;
    }
    public void setCompleted(boolean b){
        completed=b;
    }
}
