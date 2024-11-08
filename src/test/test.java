package test;

import java.util.Scanner;
import mainoop.product.ProductList;
import mainoop.user.AdminList;
import mainoop.user.CustomerList;

public class test {
    public static void main(String[] args) {
        ProductList productList = new ProductList("src/mainoop/data/product.txt");
        CustomerList customerList = new CustomerList("src/mainoop/data/Customer.txt");
        AdminList adminList = new AdminList("src/mainoop/data/Admin.txt");
        Scanner sc = new Scanner(System.in);
        boolean check = true;

        while(check) {
            System.out.println("=======================MENU=======================");
            System.out.println("1. Khách hàng");
            System.out.println("2. Quản lý");
            System.out.println("==============================================");

            int temp = sc.nextInt();
            sc.nextLine();
            switch(temp) {
                case 1: {
                    System.out.println("==================MENU KHÁCH HÀNG=======================");
                    System.out.println("1. Đăng nhập");
                    System.out.println("2. Đăng ký");
                    System.out.println("==============================================");

                    boolean check1 = true;
                    int temp1 = sc.nextInt();
                    sc.nextLine();
                    
                    while(check1) {
                        switch(temp1) {
                            case 1: {
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();
                                
                                break;
                            }   
                        }

                    }

                    break;
                }

                case 2: {

                    break;
                }
            }
        }
    }
}
