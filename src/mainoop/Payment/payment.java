package mainoop.Payment;

import java.util.Map;
import mainoop.product.Product;
import mainoop.user.Admin;
import mainoop.user.Customer;

public class payment {
    private long total; // Tổng tiền
    private Customer customer; // Tên khách hàng
    private Admin admin;

    // ================== Getter ======================
    public Customer getcustomer() {
        return this.customer;
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

    // --------- Hóa Đơn ----------
    public void bill() {
        if (customer == null) {
            System.out.println("Không có thông tin khách hàng!");
            return;
        }

        System.out.println("Xin chào, " + customer.getCustomerName());
        System.out.println("========================= HÓA ĐƠN =====================");
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
        System.out.println("Tổng cộng: " + total + " VND");
    }
}
