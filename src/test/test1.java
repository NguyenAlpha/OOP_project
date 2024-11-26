package test;

import java.io.FileWriter;

public class test1 {
    public static void main(String[] args) {
        try {
            FileWriter r = new FileWriter("src/test/test.txt");


            r.write(String.format("%-20s|%-14s|%-6s\n", "giày", "500000","1000"));
            r.write(String.format("%-20s|%-14s|%-6s", "áo", "200000","500"));

            r.close();
        } catch (Exception e) {
        }
    }   
}
