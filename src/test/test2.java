package test;

import java.util.HashMap;
import java.util.Map;
import mainoop.product.Product;

public class test2 {
    private int customerId;
    private HashMap<Product, Integer> cartItems = new HashMap<>();
    private long sumPriceProduct = 0;

    public void setId(int id) {
        customerId = id;
    }

    public void setSumPriceProduct(int sum) {
        this.sumPriceProduct = sum;
    }

    public void putCartItems(HashMap<Product, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public void putCartItems(Product product, int quantity) {
        cartItems.put(product, quantity);
        sumPriceProduct += product.getProductPrice() * (long)quantity;
    }

    public void printDanhSachSanPham() {
        for (Map.Entry<Product, Integer> en : cartItems.entrySet()) {
            Product key = en.getKey();
            int val = en.getValue();
            System.out.println(key.getProductName() + " | " + key.getProductPrice() + " | " +  val);
        }
        System.out.println("Tổng tiền: " + sumPriceProduct);
    }

}

    
