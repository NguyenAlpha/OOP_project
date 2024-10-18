package main;

import java.util.Scanner;

import main.input.read_list_product;
import main.product.list_product;

public class menu {
    
    public menu() {}

    public void menu_manager() {
        Scanner sc = new Scanner(System.in);
        list_product list_product = new list_product();

        boolean kt = true;  //điều kiện để vòng while được thực hiện

        while(kt) {
            System.out.println("=======================MENU=======================");
            System.out.println("0: thoát");
            System.out.println("1: Thêm sản phẩm");
            System.out.println("2: xem danh sách sản phẩm");
            System.out.println("==============================================");
            
            System.out.printf("chọn thao tác: ");
            int tt = sc.nextInt();
            sc.nextLine();
            System.out.println("==============================================");
            
            switch(tt) {
                case 0: {   //thao thác 0.thoát menu
                    kt = false;
                    break;
                }
                
                case 1: {
                    read_list_product rlp = new read_list_product();
                    list_product = rlp.read_list_product_method();
                    break;
                }
                
                case 2: {
                    list_product.xem_list_product();
                    break;
                }
            }
        }
        sc.close();
    }
}
