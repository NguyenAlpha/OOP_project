package test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=======================MENU=======================");
        System.out.println("1. Khách hàng");
        System.out.println("2. Quản lý");
        System.out.println("==============================================");

        System.out.print("Nhập thao tác: ");
        boolean checkInPut = false;
        while(!checkInPut) {
            try {
                int temp = sc.nextInt();
                sc.nextLine(); // clear the buffer
    
                if (temp >= 1 && temp <= 2) {
                    checkInPut = true;
                } else {
                    System.out.println("Thao tác không đúng. Vui lòng nhập lại.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai định dạng. Vui lòng nhập số.");
                sc.nextLine(); // clear the buffer
            }
        }
    }
}
