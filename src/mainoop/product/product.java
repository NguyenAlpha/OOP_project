package mainoop.product;

import mainoop.AddAndRemove;

public class Product extends AddAndRemove{
    private int productID; //ID sản phẩm
    private String productName;    //tên sản phẩn
    private String productType;    //loại sản phẩm
    private long productPrice; //giá sản phẩm

    public Product() {}

    public Product(int productID, String productName, String productType, long productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
    }

    public int getproductID() {
        return this.productID;
    }
    
    public String getproductName() {
        return this.productName;
    }

    public String getproductType() {
        return this.productType;
    }

    public long getproductPrice() {
        return this.productPrice;
    }

    public void getAll() {
        System.out.println(this.productID + " | " + this.productName + " | " + this.productType + " | " + this.productPrice);
    }

    public void setproductID(int productID) {
        this.productID = productID;
    }
    public void setproductName(String productName) {
        this.productName = productName;
    }

    public void setproductType(String productType) {
        this.productType = productType;
    }

    public void setproductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    @Override public String toString() {
        return productID + " , " + productName + " , " + productType + " ,  " + productPrice;
    }

    @Override
    public void addProduct(int productID, String productName, String productType, long productPrice) {

    }

    @Override
    public void removeProduct() {

    }


}
