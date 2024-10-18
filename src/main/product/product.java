package main.product;

public class product {
    private String name;    //tên sản phẩn
    private String type;    //loại sản phẩm
    private long price; //giá sản phẩm

    public product() {}

    public product(String name, String type, long price) {
        this.name = name;
        this.type = type; 
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public long getPrice() {
        return this.price;
    }

    public void getAll() {
        System.out.println(name + " | " + type + " | " + price);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(long price) {
        this.price = price;
    }


}
