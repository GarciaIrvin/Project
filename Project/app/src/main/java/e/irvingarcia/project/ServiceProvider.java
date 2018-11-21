package e.irvingarcia.project;

public class ServiceProvider {
    private Users user;
    private String company;
    private String phone;
    private String description;
    private boolean liscence;

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

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLiscence(boolean liscence) {
        this.liscence = liscence;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLiscence() {
        return liscence;
    }
}
