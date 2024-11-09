package mainoop.shoppingcart;

import java.util.ArrayList;
import mainoop.ListInterface;

public class ShoppingCartList implements ListInterface{
    private int count;
    private ArrayList<ShoppingCart> shoppingCartList = new ArrayList<>();

    public ShoppingCartList() {}

    public ShoppingCartList(String path) {

    }

    @Override
    public void addFromFile(String path) {
        
    }

    @Override
    public void writeToFile(Object object) {
        
    }

}
