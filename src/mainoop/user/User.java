package mainoop.user;

public abstract class User {
    static int userId;
    static String userPassword;

    //hàm tạo
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

    //
    public boolean verifyLogin(String password) {
        return userPassword.equals(password);
    }

    //
    public abstract void menu();
}
