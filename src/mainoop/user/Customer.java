package mainoop.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mainoop.product.Product;

// Customer kế thừa attribute và method của class User 
public class Customer extends User {
    private String customerName;    //Tên khách hàng
    private String customerAddress; //địa chỉ khách hàng
    private Map<Product, Integer> cartItems = new HashMap<>();  //danh sách giỏ hàng
    private long sumPriceProduct = 0;    //tổng tiền sản phẩm trong giỏ hàng
    private String bankName;    //tên ngân hàng
    private String bankId;  //mã số tài khoản ngân hàng

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
    @Override
    public String getAll() {
        return String.format("%-5s|%-20s|%-16s|%-24s|%-16s|%s", userId, customerName, userPassword, customerAddress, bankName, bankId);
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
    public void setCartItems(Map<Product, Integer> cartItem) {
        this.cartItems = cartItem;
    }
    public void setCartItemsEmpty() {
        this.cartItems = new HashMap<>();
        this.sumPriceProduct = 0;
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

    // xem tài khoản
    public void viewAccount() {
        System.out.println("Mã số: " + getUserId());
        System.out.println("Tên: " + getCustomerName());
        System.out.println("Mật khẩu: " + getUserPassword());
        System.out.println("Địa chỉ: " + getCustomerAddress());
        System.out.println("Tài khoản ngân hàng: " + getBankName() + " " + getBankId());
        Scanner sc = new Scanner(System.in);
        if(getBankId().equals("N/A")) {
            System.out.println("0. quay lại");
            System.out.println("1. Liên kết tài khoản ngân hàng");
            System.out.print("Nhập thao tác: ");
            int thaoTacCase3 = sc.nextInt();
            sc.nextLine();

            switch (thaoTacCase3) {
                case 1 -> {
                    System.out.print("Nhập tên ngân hàng: ");
                    String bankName = sc.nextLine();
                    String bankId;
                    while (true) {
                        System.out.print("Nhập số tài khoản ngân hàng: ");
                        bankId = sc.nextLine();
                        if (bankId.length() < 8) {
                            System.out.println("Số tài khoản ngân hàng phải hơn 8 ký tự.");
                        } else if (!bankId.matches("\\d+")) {
                            System.out.println("Số tài khoản ngân hàng chỉ được chứa các ký tự số.");
                        } else {
                            break; // Thoát vòng lặp nếu chuỗi hợp lệ
                        }
                    }
                    setBank(bankId, bankName);
                    System.out.println("đã thêm tài khoản ngân hàng!");
                }
                default -> {}
            }
        }

    }

    // kiểm tra giỏ hàng có trống không
    public boolean checkCartItem() {
        return cartItems.isEmpty();
    }

    // xem giỏ hàng
    public void viewCartItems() {
        System.out.printf("%-6s|%-20s|%-20s|%s", "MÃ SP", "TÊN SẢN PHẨM", "GIÁ", "SỐ LƯỢNG");
        for (Map.Entry<Product, Integer> en : cartItems.entrySet()) {
            Product product = en.getKey();
            int val = en.getValue();
            System.out.printf("\n%-6s|%-20s|%-20s|%s",product.getProductId(), product.getProductName(), product.getProductPrice(), val);
        }
        System.out.println("\nTổng tiền: " + sumPriceProduct);
    }

    
    // thêm sản phẩm mới vào giỏ hàng
    public void addCartItems(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + quantity);
            calcuaSumPriceProduct();
        } else if(product == null) {
            System.out.println("Không thể thêm!");
        } else {
            cartItems.put(product, quantity);
            calcuaSumPriceProduct();
        }
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
            System.out.println("Đã xóa!");
        } else {
            System.out.println("Không thể xóa!");
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