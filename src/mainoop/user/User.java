package mainoop.user;

// class User là class Abstract của class Customer và class Admin
// là class Customer và class Admin sẽ kế thừa Các attribute và method của class User
public abstract class User {
    //Attributes của User
    int userId;  //ID của người dùng
    String userPassword; //mật khẩu của người dùng

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

    // kiểm tra password
    public boolean checkUserPassword(String password) {
        //trả về xem password truyền vào để kiểm tra có đúng hay không
        return (this.userPassword.equals(password));
    }

    // Các class abstract
    public abstract String getAll();
    public abstract boolean checkUserName(String userName);
}
