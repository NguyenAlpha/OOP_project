package mainoop.product;

public class Product{
    private int productId; //ID sản phẩm
    private String productName;    //tên sản phẩn
    private long productPrice; //giá sản phẩm
    private int productQuantity;    //số lượng sản phẩm

    // Hàm tạo không tham số
    public Product() {
        productId = 0;
        productName = "";
        productPrice = 0;
        productQuantity = 0;
    }

    // hàm tạo có tham số
    public Product(int productId, String productName, long productPrice, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    //==================geter======================
    public int getProductId() {
        return this.productId;
    }
    public String getProductName() {
        return this.productName;
    }
    public long getProductPrice() {
        return this.productPrice;
    }
    public int getProductQuantity() {
        return this.productQuantity;
    }
    public String getAll() {
        return String.format("%-6s|%-25s|%-16s|%s",productId,productName,productPrice,productQuantity);
    }

    //==================seter======================
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    // viết lại hàm toString mặc định
    @Override
    public String toString() {
        return productId + "   |   " + productName + "   |   " + productPrice + "   |   " + productQuantity;
    }
}
