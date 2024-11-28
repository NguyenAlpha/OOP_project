package mainoop.Payment;

import java.util.Map;
import mainoop.product.Product;
import mainoop.user.Admin;
import mainoop.user.Customer;

public class payment {
    private long total; // Tổng tiền
    private Customer customer; // Tên khách hàng
    private Admin admin;
    private int paymentID; // mã đơn hàng
    private static int currentID = 100;//biến tĩnh để theo giõi đơn hàng

    // ================== Getter ======================
    public int getpaymentID() {
        return paymentID;
    }
    public  Customer getcustomer()
    {
        return customer;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public long getTotal() {
        return this.total;
    }

    // ------------------ Setter ---------------------
    public void setcustomer(Customer customer) {
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

        // Tính tổng tiền
        total = 0;
        for (Map.Entry<Product, Integer> entry : customer.getCartItem().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            long productTotal = product.getProductPrice() * quantity;
            total += productTotal;

            // Hiển thị chi tiết sản phẩm
            System.out.printf("%-20s | %-10d | %-15d | %-15d\n",
                    product.getProductName(), quantity, product.getProductPrice(), productTotal);
        }

        System.out.println("------------------------------------------------------");
        System.out.println("Tong cong: " + total + " VND");
    }
    
}
