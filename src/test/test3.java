package test;

public class test3 {
    private String name;    //tên
    private int price;  //giá

    public test3() {}   //sản phẩm

    public test3(int price, String name) {
        this.price = price; 
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override public String toString() {
        return name + " , " + price;
    }
}
