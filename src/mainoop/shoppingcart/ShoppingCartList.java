package mainoop.shoppingcart;
import java.util.ArrayList;
import mainoop.ListInterface;

public class ShoppingCartList implements ListInterface{
    private int count;
    private ArrayList<ShoppingCartList> shoppingCartList = new ArrayList<>();

    public ShoppingCartList() {}

    public ShoppingCartList(String path) {
        shoppingCartList = new ArrayList<>();
        

    }

    @Override
    public void addFromFile(String path) {
        
    }

    @Override
    public void writeToFile() {
        
    }

}
