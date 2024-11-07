package mainoop.user;

public class Admin extends User{
    private String admindName;
    private String adminEmail;

    public Admin() {
        admindName = "";
        adminEmail = "";
    }

    public Admin(int id, String pass, String name, String email) {
        super(id, pass);
        admindName = name;
        adminEmail = email;
    }

    @Override
    public void menu() {

    }

    
}
