package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mainoop.product.Product;

public class test3 {
    public static void main(String[] args) {
        // System.out.println(p1.getAll());
        // System.out.println(p2.getAll());
        // p2.setProductId(2);
        // System.out.println(p2.getAll());
        // System.out.println(p1.getAll());
        Product p1 = new Product(1, "helo", 1000, 10);
        Product p2 = new Product(2, "kaka", 99, 8);
        ArrayList<Product> ar = new ArrayList<>();
        Map<Product, Integer> MM = new HashMap<>();
        MM.put(p1, 66);
        MM.put(p2, 77);
        ar.add(p1);
        ar.add(p2);
        p2.setProductName("ahfkasnduas ldha sjd");
        
        for(Product p : ar) {
            System.out.println(p.getAll());
        }

        for (Map.Entry<Product, Integer> en : MM.entrySet()) {
            Product key = en.getKey();
            int val = en.getValue();
            System.out.println(key.getAll());
            System.out.println(val);
        }

        Product p3 = new Product();
        System.out.println(p3 == null);

    }
}
