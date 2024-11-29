package mainoop.Payment;

import java.util.HashMap;
import java.util.Map;
import mainoop.product.Product;
import mainoop.user.Admin;
import mainoop.user.Customer;

public class payment {
    private Customer customer; // Tên khách hàng
    private Admin admin;
    private int id;
    private int paymentID; // mã đơn hàng
    private Map<Product,Integer> productInBill = new HashMap<>();
    private long sumPriceProduct = 0; // mã đơn hàng
    private String status = "dang chuan bi don hang";
    private static int currentID = 100;//biến tĩnh để theo giõi đơn hàng

    public payment() {}

    public payment(int id, Customer customer, Map<Product,Integer> productInBill, long sumPriceProduct) {
        this.id = id;
        this.customer = customer;
        this.productInBill = productInBill;
        this.sumPriceProduct = sumPriceProduct;
    }

    // ================== Getter ======================
    public int getpaymentID() {
        return paymentID;
    }
    public  Customer getCustomer()
    {
        return customer;
    }
    public Admin getAdmin() {
        return this.admin;
    }


    // ------------------ Setter ---------------------
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public void setpaymentID(int paymentID) {
        this.paymentID =  currentID++;
    }

    // --------- Hóa Đơn ----------
    public void bill() {
        // if (customer == null) {
        //     System.out.println("Không có thông tin khách hàng!");
        //     return;
        // }

        
        System.out.println("Xin chào, " + customer.getCustomerName());
        System.out.println("========================= Hoa Don =====================");
        System.out.println("Mã Đơn hàng: " + getpaymentID());
        System.out.println("Tên khách hàng: " + customer.getCustomerName());
        System.out.println("Danh sách sản phẩm:");
        System.out.printf("%-20s | %-10s | %-15s | %-15s\n", "Tên hàng", "SL", "Đơn giá", "Thành tiền");

        for (Map.Entry<Product, Integer> entry : customer.getCartItem().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            long productTotal = product.getProductPrice() * quantity;

            // Hiển thị chi tiết sản phẩm
            System.out.printf("%-20s | %-10d | %-15d | %-15d\n",
                    product.getProductName(), quantity, product.getProductPrice(), productTotal);
        }

        System.out.println("------------------------------------------------------");
        System.out.println("Tong cong: " + customer.getSumPriceProduct() + " VND");
    }

    public Map<Product, Integer> getProductInBill() {
        return productInBill;
    }

    public void setProductInBill(Map<Product, Integer> productInBill) {
        this.productInBill = productInBill;
    }

    public void addProductInBill(Product product, int quantity) {
        productInBill.put(product, quantity);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSumPriceProduct() {
        return sumPriceProduct;
    }

    public void setSumPriceProduct(long sumPriceProduct) {
        this.sumPriceProduct = sumPriceProduct;
    }
    
    // tính tổng tiền giỏ hàng
    public  void calcuaSumPriceProduct() {
        sumPriceProduct = 0;
        for(Map.Entry<Product, Integer> en : productInBill.entrySet()) {
            Product product = en.getKey();
            int quantity = en.getValue();
            sumPriceProduct += product.getProductPrice() * (quantity);
        }
    }
}
