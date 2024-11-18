package test;

import java.util.ArrayList;
import mainoop.product.Product;

public class test3 {
    // private ArrayList<test2> AAA = new ArrayList<>();


    public static void main(String[] args) {
        ArrayList<test2> AAA = new ArrayList<>();
        test2 TTT = new test2();
        TTT.setId(10001);
        Product p1 = new Product(0, "Quan", 150, 20);
        TTT.putCartItems(p1, 1);
        Product p2 = new Product(1, "non", 50, 40);
        TTT.putCartItems(p2, 2);
        Product p3 = new Product(2, "ao", 100, 55);
        TTT.putCartItems(p3, 1);
        TTT.printDanhSachSanPham();
        
    }
}
