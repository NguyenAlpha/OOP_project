package mainoop.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListInterface;

public class AdminList implements ListInterface{
    private int adminCount;
    private ArrayList<Admin> adminList = new ArrayList<>();

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
                adminList.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public void writeToFile() {
        
    }

    // kiểm tra đăng nhập admin
    public Admin login(String name, String password) {
        for(Admin admin : adminList) {
            if(admin.checkUserName(name) && admin.checkUserPassword(password)) {
                return admin;
            }
        }
        return null;
    }

}
