package test;

import java.io.File;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        int i = 1;
        try {
            Scanner sc = new Scanner(new File("src/test/test.txt"));
            while(sc.hasNextLine()) {
                sc.nextLine();
                System.out.println(i);
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
