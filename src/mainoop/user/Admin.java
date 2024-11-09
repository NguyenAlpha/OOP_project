package mainoop.user;

public class Admin extends User{
    private String adminName;
    private String adminEmail;

    // Hàm tạo không tham số
    public Admin() {
        super();
        adminName = "";
        adminEmail = "";
    }

    // Hàm tạo có tham số
    public Admin(int id, String adminName, String pass, String adminEmail) {
        super(id, pass);
        this.adminName = adminName;
        this.adminEmail = adminEmail;
    }

    //==================geter======================
    public String getAdminName() {
        return adminName;
    }
    public String getAdminEmail() {
        return adminEmail;
    }
    
    //==================seter======================
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    
    //viết lại hàm mặc định toString
    @Override public String toString() {
        return userId + " , " + adminName + " , " + userPassword + " , " + adminEmail;
    }

    // viết lại hàm trừu tượng của class User 
    @Override
    public String getAll() {
        return userId + " | " + adminName + " | " + userPassword + " | " + adminEmail;
    }
    @Override
    public boolean checkUserName(String userName) {
        return adminName.equals(userName);
    }
}
