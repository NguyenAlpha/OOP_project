package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import mainoop.FilePaths;
import mainoop.ListInterface;
import mainoop.Running;
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
            Scanner reader = new Scanner(new File(path));
            reader.nextLine();
            reader.nextLine();
            while(reader.hasNextLine()) {   //nếu có dòng để đọc file
                String s = reader.nextLine();   //đọc dòng đó
                String[] sSplit = s.split("[ ]*[|][ ]*");   //tách các thuộc tính của dòng đó ra
                Customer customer = new Customer(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], sSplit[3], sSplit[4], sSplit[5]); //lưu vào 1 đối tượng
                customerList.add(customer); //lưu vào danh sách các đối tượng
                customerCount++;
            }
            reader.close();
        } catch (FileNotFoundException e) { //nếu lỗi thì hiển thị lỗi
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        ProductList productList = Running.getProductList();
        try {
            Scanner reader = new Scanner(new File(FilePaths.SHOPPING_CART_PATH));
            while(reader.hasNextLine()) {
                int id = Integer.parseInt(reader.nextLine());
                Customer customer = getCustomerById(id);
                String productLine = reader.nextLine();
                String[] splitProducts = productLine.split("[ ]*[|][ ]*");
                for (String productEntry : splitProducts) {
                    String[] splitDetails = productEntry.split("[ ]*[,][ ]*");
                    customer.addCartItems(productList.getProductByName(splitDetails[0]), Integer.parseInt(splitDetails[1]));
                }
                reader.nextLine();
            }
            reader.close();
        } catch (Exception e) {}
    }

    @Override
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(FilePaths.CUSTOMER_PATH);
            writer.write("======================= DANH SACH KHACH HANG =======================");
            writer.write(String.format("\n%-5s|%-20s|%-16s|%-24s|%-16s|%s", "MA SO", "TEN", "MAT KHAU", "DIA CHI", "BANK NAME", "BANK ID"));

            for(Customer customer : customerList) { 
                writer.write("\n" + customer.getAll());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter writerr = new FileWriter(FilePaths.SHOPPING_CART_PATH);
            for( Customer customer : customerList) {
                if(!customer.checkCartItem()) {
                    boolean check = false;
                    writerr.write(String.valueOf(customer.getUserId()) + "\n");
                    for(Map.Entry<Product, Integer> en : customer.getCartItem().entrySet()) {
                        Product product = en.getKey();
                        int quantity = en.getValue();
                        if (product == null) {
                            continue;
                        }
                        if(check) {
                            writerr.write("    |    ");
                        }
                        writerr.write(product.getProductName() + " , " + quantity);
                        check = true;
                    }
                    writerr.write("\n" + customer.getSumPriceProduct() + "\n");
                }
            }
            writerr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        writeToFile();
    }

    // Xem danh sách khách hàng
    public void viewCustomerList() {
        for(Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    // kiểm tra đã tồn tại tên tài khoản này chưa
    public boolean checkAlreadyExists(String name) {
        for(Customer customer : customerList) {
            if(customer.getCustomerName().equals(name)) 
            return true;
        }
        return false;
    }

    // trả về khác hàng từ id
    public Customer getCustomerById(int i) {
        return customerList.get(i - 1);
    }

    // trả về khách hàng từ tên
    public Customer getCustomerByName(String name) {
        for(Customer customer : customerList) {
            if(customer.getCustomerName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public void removeProductFromAllCustomers(Product product) {
        for (Customer customer : customerList) {
            // Xóa sản phẩm khỏi giỏ hàng của khách hàng
            customer.removeCartItems(product, customer.getCartItem().getOrDefault(product, 0));
        }
        // Cập nhật lại file ShoppingCart.txt
        writeToFile();
    }

}
