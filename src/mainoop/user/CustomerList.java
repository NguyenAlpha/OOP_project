package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListAbstract;

public class CustomerList extends ListAbstract{
    private int customerCount;
    private ArrayList<Customer> customerList = new ArrayList<>();

    public CustomerList() {}

    public CustomerList(String path) {
        addFromFile(path);
    }

    @Override
    public void addFromFile(String path) {
        try {
            File read = new File(path);
            Scanner reader = new Scanner(read);
            while(reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] sSplit = s.split("[ ]*[|][ ]*");
                Customer temp = new Customer(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], sSplit[3]);
                customerList.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    @Override
    public void writeToFile() {

    }


    public boolean login(String name, String password) {
        for(Customer customer : customerList) {
            
        }
        return true;
    }
}
