package test;

import java.util.Scanner;

public class test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("i: " + i);
        sc.nextLine();
        String s = sc.nextLine();
        System.out.println("s: " + s);
        System.out.println("s Low: " + s.toLowerCase());

    }
}
