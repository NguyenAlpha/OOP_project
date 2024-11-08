package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListInterface;

public class CustomerList implements ListInterface{
    // các thuộc tính của dánh sách khách hàng
    private int customerCount = 0;  //số lượng khách hàng
    private ArrayList<Customer> customerList = new ArrayList<>();   //danh sách khách hàng

    // Hàm tạo không tham số
    public CustomerList() {}

    // Hàm tạo có tham số
    public CustomerList(String path) {
        addFromFile(path);
    }

    //==================geter======================
    public int getCustomerCount() {
        return customerCount;
    }

    //Thêm 1 Object vào dánh sách
    public void addObject(Customer customer) {
        customerList.add(customer); //thêm vào danh sách khách hàng
        customerCount++;    //tăng tổng số khách hàng lên 1

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

    // Thêm danh sách khách hàng từ file
    @Override
    public void addFromFile(String path) {
        try {
            // đọc file
            File read = new File(path);
            Scanner reader = new Scanner(read);  
            while(reader.hasNextLine()) {   //nếu có dòng để đọc file
                String s = reader.nextLine();   //đọc dòng đó
                String[] sSplit = s.split("[ ]*[|][ ]*");   //tách các thuộc tính của dòng đó ra
                Customer temp = new Customer(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], sSplit[3]); //lưu vào 1 đối tượng
                customerList.add(temp); //lưu vào danh sách các đối tượng
                customerCount++;
            }
        } catch (FileNotFoundException e) { //nếu lỗi thì hiển thị lỗi
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(Object c) {

    }


    // kiểm tra tài khoản cần đăng nhập có tồn tại không
    public Customer login(String name, String password) {
        // duyệt qua danh sách khách hàng
        for(Customer customer : customerList) { 
            //nếu tồn tại thì trả vể tài khoản đó để khách hàng sử dụng
            if(customer.checkUserPassword(password) && customer.checkUserName(name)) {
                return customer;
            }
        }
        //nếu tài khoản không tồn tại thì trả về null
        return null;
    }

    // Xem danh sách sản phẩm
    public void viewProductList() {
        for(Customer customer : customerList) {
            System.out.println(customer);
        }
    } 

    public Customer getObject(int i) {
        return customerList.get(i);
    }
}
