package mainoop.user;



// class User là class Abstract của class Customer và class Admin
public abstract class User {
    //Attributes của User
    static int userId;  //ID của người dùng
    static String userPassword; //mật khẩu của người dùng

    //hàm tạo không tham số
    public User() {
        userId = 0;
        userPassword = "";
    }   

    //hàm tạo có tham số
    public User(int id, String pass) {
        this.userId = id;
        this.userPassword = pass;
    }

    //==================geter======================
    public int getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }

    //==================seter======================
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    //kiểm tra pass
    public boolean checkUserPassword(String password) {
        return (this.userPassword.equals(password));
    }

    public abstract boolean checkUserName(String userName);
    public abstract String getAll();
    // public abstract void menu();
}
