package mainoop.user;

import java.io.FileWriter;
import java.io.IOException;

public class Customer extends User {
    private String customerName;
    private String address;
    
    //hàm tạo 
    public Customer() {
        super();
        customerName = "";
        address = "";
    }
    
    //hàm tạo có tham số
    public Customer(int id, String pass, String name, String address) {
        super(id, pass);
        this.customerName = name;
        this.address = address;
    }

    //==================geter======================
    public String getName() {
        return customerName;
    }

    public String getaddress() {
        return address;
    }

    



    @Override public String toString() {
        return super.getUserId() + " | " + super.getUserPassword() + " | " + customerName + " | " + address + " | ";
    }

    @Override public void menu() {

    }

    public void register(Customer customer) {
        try {
            FileWriter writer = new FileWriter("src/mainoop/user/Customer.txt");
            
            System.out.println("OK write");
        } catch(IOException e) {
            System.out.println("error write");
            e.printStackTrace();
        }
    }

    public void login(String name, String pass) {
        
    }

    
}
