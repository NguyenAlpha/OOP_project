package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test6 {
    public static void main(String[] args) {
        int i = 1;
        try {
            File read = new File("src/test/test.txt");
            Scanner reader = new Scanner(read);
            while(reader.hasNextLine()) {
                String s = reader.nextLine();
                System.out.println(s);
                System.out.println("Dòng " + i);
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // System.out.println("asjkdakd akdnajks dm");

    }
}
