package mainoop.Payment;

import java.util.HashMap;
import java.util.Map;
import mainoop.product.Product;
import mainoop.user.Customer;

public class payment {
    private int id; //mã đơn
    private Customer customer; // Tên khách hàng
    private Map<Product,Integer> danhSachSanPham = new HashMap<>(); //danh sách sản phẩm của đơn
    private long sumPriceProduct = 0; // tổng tiền của đơn
    private String status;   //trạng thái đơn

    // hàm tạo không tham số
    public payment() {}
    
    // hàm tạo có tham số
    public payment(int id, Customer customer, Map<Product,Integer> danhSachSanPham, long sumPriceProduct) {
        this.id = id;
        this.customer = customer;
        this.danhSachSanPham = danhSachSanPham;
        this.sumPriceProduct = sumPriceProduct;
        this.status = "dang chuan bi don hang";
    }

    // ================== Getter ======================
    public int getId() {
        return id;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    public Map<Product, Integer> getDanhSachSanPham() {
        return danhSachSanPham;
    }
    public String getStatus() {
        return status;
    }
    public long getSumPriceProduct() {
        return sumPriceProduct;
    }
    
    // ------------------ Setter ---------------------
    public void setId(int id) {
        this.id = id;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setDanhSachSanPham(Map<Product, Integer> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setSumPriceProduct(long sumPriceProduct) {
        this.sumPriceProduct = sumPriceProduct;
    }
    
    public void ThemDanhSachSanPham(Product product, int quantity) {
        danhSachSanPham.put(product, quantity);
    }
    
    // tính tổng tiền giỏ hàng
    public  void calcuaSumPriceProduct() {
        sumPriceProduct = 0;
        for(Map.Entry<Product, Integer> en : danhSachSanPham.entrySet()) {
            Product product = en.getKey();
            int quantity = en.getValue();
            sumPriceProduct += product.getProductPrice() * (quantity);
        }
    }
}
