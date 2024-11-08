package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListInterface;

public class CustomerList implements ListInterface{
    private int customerCount;
    private ArrayList<Customer> customerList = new ArrayList<>();
    //int customerList[100];
    //2 3 5 7 9
    // return customerList[2];
    public CustomerList() {}

    public CustomerList(String path) {
        addFromFile(path);
    }

    //==================geter======================
    public int getCustomerCount() {
        return customerCount;
    }

    public void addObject(Customer customer) {
        customerList.add(customer);
        try {
            FileWriter writer = new FileWriter("src/mainoop/data/Customer.txt");
            writer.append(customer.getAll());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(Object c) {
        // try {
        //     FileWriter writer = new FileWriter("src/mainoop/user/Customer.txt");
        //     for(Customer temp : customerList) {
        //         writer.
        //     }
        // } catch(IOException e) {
        //     System.out.println("error write");
        //     e.printStackTrace();
        // }
    }



    public boolean login(String name, String password) {
        for(Customer customer : customerList) {
            if(customer.checkUserPassword(password) && customer.checkUserName(name)) {
                return true;
            }
        }
        return false;
    }


    public Customer getObject(int i) {
        return customerList.get(i);
    }
}
