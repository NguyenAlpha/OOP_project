package mainoop.user;

import java.util.ArrayList;
import mainoop.product.Product;

public class ShoppingCart {
    private int customerId;
    private ArrayList<Product> listProductShoppingCart = new ArrayList<>();
    private ArrayList listQuantityProductShoppingCart = new ArrayList<>();
    private int sumPriceProduct;

    public ShoppingCart() {}
    
    public ShoppingCart(int customerId, ArrayList<Product> listProductShoppingCart, ArrayList listQuantityProductShoppingCart, int sumPriceProduct) {
        this.customerId = customerId;
        this.listProductShoppingCart = listProductShoppingCart;
        this.listQuantityProductShoppingCart = listQuantityProductShoppingCart;
        this.sumPriceProduct = sumPriceProduct;
    }

}
