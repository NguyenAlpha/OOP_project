package mainoop.user;

import java.util.ArrayList;
import mainoop.ListInterface;

public class ShopingCartList implements ListInterface{
    private int count;
    private ArrayList<ShoppingCart> shoppingCartList = new ArrayList<>();

    public ShopingCartList() {}

    public ShopingCartList(String path) {

    }

    @Override
    public void addFromFile(String path) {

    }

    @Override
    public void writeToFile(Object object) {
        
    }

}
