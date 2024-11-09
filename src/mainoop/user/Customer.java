package mainoop.user;

import java.io.FileWriter;
import java.io.IOException;

// Customer kế thừa attribute và method của class User 
public class Customer extends User {
    private String customerName;    //Tên khách hàng
    private String customerAddress; //địa chỉ khách hàng
    
    //hàm tạo không tham số
    public Customer() {
        super();
        customerName = "";
        customerAddress = "";
    }
    
    //hàm tạo có tham số
    public Customer(int id, String name, String pass, String address) {
        super(id, pass);    // phương thức này sẽ gọi đến hàm tạo 2 tham số của class cha(class User)
        this.customerName = name;
        this.customerAddress = address;
    }

    //==================geter======================
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }

    //==================seter======================
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setAddress(String address) {
        this.customerAddress = address;
    }

    //viết lại hàm mặc định toString
    @Override public String toString() {
        return userId + " , " + customerName + " , " + userPassword + " , " + customerAddress;
    }

    // viết lại hàm trừu tượng của class User 
    @Override
    public boolean checkUserName(String userName) {
        return customerName.equals(userName);
    }
    @Override
    public String getAll() {
        return userId + " | " + customerName + " | " + userPassword + " | " + customerAddress;
    }



    // thêm 1 khách hàng mới vào file lưu trữ
    public void addCustomer(Customer customer) {
        // viết vào file
        try {
            FileWriter writer = new FileWriter("src/mainoop/data/Customer.txt", true);
            writer.write("\n" + customer.getAll());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
