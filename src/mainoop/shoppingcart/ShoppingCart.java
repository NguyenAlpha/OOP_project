package mainoop.shoppingcart;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import mainoop.product.Product;


public class ShoppingCart {
    private int customerId;
    private Map<Product, Integer> cartItems = new HashMap<>();
    private int sumPriceProduct;
    public ShoppingCart() {}
    
    public ShoppingCart(int customerId, Map<Product, Integer> cartItems, int sumPriceProduct, double ProductPrice) {
        this.customerId = customerId;
        this.cartItems = cartItems;
        this.sumPriceProduct = sumPriceProduct;
    }

    public void addProduct(Product product, int quantity) {
        cartItems.merge(product, quantity, Integer::sum);
    }
   
    public void calcualateAll() {
        sumPriceProduct = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sumPriceProduct += product.getProductPrice() * quantity;
        }
    }

    public void removeProduct(Product product, int quantityToRemove) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            
            if (quantityToRemove >= currentQuantity) {
                // Xóa hoàn toàn sản phẩm
                cartItems.remove(product);
            } else {
                // Giảm số lượng
                cartItems.put(product, currentQuantity - quantityToRemove);
            }
            calcualateAll(); // Cập nhật lại tổng giá
        }
    }

    public void writeToFile(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ShoppingCartlist.txt"))) {
            writer.write("Customer Shoppingcart\n");
            writer.write("------------------------\n");
            writer.write("Customer ID:" + customerId + "\n");
            
            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                writer.write(String.format("Product: %s ProductID: %s Product Price: %.2f Product quantities: %d TotalPrice: %.2f\n",
                    product.getProductName(),
                    product.getProductId(),
                    product.getProductPrice(),
                    quantity,
                    product.getProductPrice() * quantity));
            }
            writer.write("----------------------------------------\n");
            writer.write("Total Price: " + sumPriceProduct + "\n");
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void setCustomerId(int id) {
        this.customerId = id;
    }

    public void setCartItems(Map<Product, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }
}