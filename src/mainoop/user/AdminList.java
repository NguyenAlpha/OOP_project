package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListInterface;

public class AdminList implements ListInterface{
    private int adminCount;
    private ArrayList<Admin> customerList = new ArrayList<>();

    public AdminList() {}

    public AdminList(String path) {
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
                Admin temp = new Admin(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], sSplit[3]);
                customerList.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public void writeToFile(Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
