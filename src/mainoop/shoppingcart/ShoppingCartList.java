package mainoop.shoppingcart;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mainoop.ListInterface;
import mainoop.product.Product;
import mainoop.product.ProductList;

public class ShoppingCartList implements ListInterface{
    private int count;
    private ArrayList<ShoppingCartList> shoppingCartList = new ArrayList<>();

    public ShoppingCartList() {}

    public ShoppingCartList(String path) {
        shoppingCartList = new ArrayList<>();
        
    }

    @Override
    public void addFromFile(String path) {
        ProductList productList = new ProductList("src/mainoop/data/product.txt");
        Map<Product, Integer> cartItems = new HashMap<>();
        try {
            File read = new File("src/mainoop/data/ShoppingCart.txt");
            Scanner reader = new Scanner(read);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setCustomerId(Integer.parseInt(reader.nextLine()));
            String s = reader.nextLine();
            String[] ss = s.split("[ ]*[|][ ]*");
            for(String temp : ss) {
                String[] temp2 = temp.split("[ ]*[,][ ]*");
                String name = temp2[0];
                int sl = Integer.parseInt(temp2[1]);
                Product product = productList.getProductByName(name);
                // shoppingCart
            }
       } catch (Exception e) {
       }
    }

    @Override
    public void writeToFile() {
        
    }

}
