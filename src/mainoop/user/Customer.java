package mainoop.user;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import mainoop.FilePaths;
import mainoop.product.Product;

// Customer kế thừa attribute và method của class User 
public class Customer extends User {
    private String customerName;    //Tên khách hàng
    private String customerAddress; //địa chỉ khách hàng
    private Map<Product, Integer> cartItems = new HashMap<>();  //danh sách giỏ hàng
    private long sumPriceProduct = 0;    //tổng tiền sản phẩm trong giỏ hàng
    private String bankName;    //tên ngân hàng
    private String bankId;  //mã số tài khoản ngân hàng
    private String orderStatus; //trạng thái đơn hàng
    //hàm tạo không tham số
    public Customer() {
        super();
        customerName = "";
        customerAddress = "";
    }
    
    //hàm tạo có tham số
    public Customer(int id, String name, String pass, String address, String bankName, String bankId) {
        super(id, pass);    // phương thức này sẽ gọi đến hàm tạo 2 tham số của class cha(class User)
        this.customerName = name;
        this.customerAddress = address;
        this.bankName = bankName;
        this.bankId = bankId;
        // this.orderStatus = "Chưa xử lý";
    }

    //==================geter======================
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public Map<Product, Integer> getCartItem() {
        return this.cartItems;
    }
    public long getSumPriceProduct() {
        return sumPriceProduct;
    }
    public String getBankId() {
        return this.bankId;
    }
    public String getBankName() {
        return this.bankName;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    @Override
    public String getAll() {
        return userId + " | " + customerName + " | " + userPassword + " | " + customerAddress;
    }
    //==================seter======================
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setAddress(String address) {
        this.customerAddress = address;
    }
    public void setSumPriceProduct(long sum) {
        this.sumPriceProduct = sum;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setBank(String bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    //viết lại hàm mặc định toString
    @Override public String toString() {
        return userId + " , " + customerName + " , " + userPassword + " , " + customerAddress + " , " + orderStatus;
    }

    // viết lại hàm trừu tượng của class User 
    @Override
    public boolean checkUserName(String userName) {
        return customerName.equals(userName);
    }

    // thêm 1 khách hàng mới vào file lưu trữ
    public void addCustomer(Customer customer) {
        try {
            FileWriter writer = new FileWriter(FilePaths.CUSTOMER_PATH, true);
            writer.write("\n" + customer.getAll());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // kiểm tra giỏ hàng có trống không
    public boolean checkCartItem() {
        return cartItems.isEmpty();
    }

    // xem giỏ hàng
    public void viewCartItems() {
        System.out.println("  mã  |       tên sản phẩm      |    giá sản phẩm    |   sô lượng");
        for (Map.Entry<Product, Integer> en : cartItems.entrySet()) {
            Product key = en.getKey();
            int val = en.getValue();
            System.out.print(key.getProductId());
            int temp = 6 - String.valueOf(key.getProductId()).length();
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.print(key.getProductName());
            temp = 25 - key.getProductName().length();
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.print(key.getProductPrice());
            temp = 20 - String.valueOf(key.getProductPrice()).length();
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.println(val);
        }
        System.out.println("Tổng tiền: " + sumPriceProduct);
        System.out.println("Trạng thái đơn hàng: " + orderStatus);
    }

    
    // thêm sản phẩm mới vào giỏ hàng
    public void addCartItems(Product product, int quantity) {
        for(Map.Entry<Product, Integer> en : cartItems.entrySet()) {
            if(en.getKey().getProductName() == product.getProductName()) {
                cartItems.put(product, en.getValue() + quantity);
                calcuaSumPriceProduct();
                return;
            }
        }
        cartItems.put(product, quantity);
        calcuaSumPriceProduct();
    }

    // xóa sản phẩm khỏi giỏ hàng
    public void removeCartItems(Product product, int quantityToRemove) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            
            if (quantityToRemove >= currentQuantity) {
                // Xóa hoàn toàn sản phẩm
                cartItems.remove(product);

            } else {
                // Giảm số lượng
                cartItems.put(product, currentQuantity - quantityToRemove);
            }
            calcuaSumPriceProduct();
        }
        
    }

    // tính tổng tiền giỏ hàng
    public  void calcuaSumPriceProduct() {
        sumPriceProduct = 0;
        for(Map.Entry<Product, Integer> en : cartItems.entrySet()) {
            Product product = en.getKey();
            int quantity = en.getValue();
            sumPriceProduct += product.getProductPrice() * (quantity);
        }
    }
}