package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import mainoop.ListInterface;
import mainoop.product.Product;
import mainoop.product.ProductList;

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

    // Thêm danh sách khách hàng từ file
    @Override
    public final void addFromFile(String path) {
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
            reader.close();
        } catch (FileNotFoundException e) { //nếu lỗi thì hiển thị lỗi
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ProductList productListClass = new ProductList("src/mainoop/data/product.txt");
        try {
            Scanner reader = new Scanner(new File("src/mainoop/data/ShoppingCart.txt"));
            while(reader.hasNextLine()) {
                int id = Integer.parseInt(reader.nextLine());
                Customer customer = getCustomerById(id);
                String line2 = reader.nextLine();
                String[] split1 = line2.split("[ ]*[|][ ]*");
                for(String temp : split1) {
                    String[] split2 = temp.split("[ ]*[,][ ]*");
                    customer.addCartItems(productListClass.getProductByName(split2[0]), Integer.parseInt(split2[1]));
                }
                set(id-1,customer);
                reader.nextLine();
            }
            reader.close();
        } catch (Exception e) {
            
        }
    }

    @Override
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter("src/mainoop/data/ShoppingCart.txt");
            for( Customer customer : customerList) {
                if(customer != null) {
                    writer.write(String.valueOf(customer.getUserId()) + "\n");
                    for(Map.Entry<Product, Integer> en : customer.getCartItem().entrySet()) {
                        Product product = en.getKey();
                        int quantity = en.getValue();
                        writer.write(product.getProductName() + " , " + quantity + "    |   ");
                    }
                    writer.write("\n" + customer.getSumPriceProduct() + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public void set(int index, Customer customer) {
        customerList.set(index, customer);
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

    //Thêm khách hàng mới vào dánh sách
    public void addCustomerToList(Customer customer) {
        customerList.add(customer); //thêm vào danh sách khách hàng
        customerCount++;    //tăng tổng số khách hàng lên 1
        customer.addCustomer(customer); //thêm 1 khách hàng mới vào file lưu trữ
    }

    // Xem danh sách khách hàng
    public void viewCustomerList() {
        for(Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    // trả về khác hàng từ id
    public Customer getCustomerById(int i) {
        return customerList.get(i - 1);
    }
}
