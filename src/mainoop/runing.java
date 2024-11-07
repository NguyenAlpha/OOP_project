package mainoop;

import java.util.Scanner;
import mainoop.product.ProductList;
import mainoop.user.AdminList;
import mainoop.user.CustomerList;

//mỗi lần thêm sản phẩm, xóa sản phẩm, sửa sản phẩm sẽ có cùng method viết lại product.txt
//vào sẽ tự động có sẵn danh sách sản phẩm, bỏ thao tác 1
//làm class user. 2 class kế thừa user là: Customer, Admin 
//method user + verifyLogin() : bool
//Customer + register()
//



public class runing {
    private static ProductList productList = new ProductList("src/mainoop/data/product.txt");
    private static CustomerList customerList = new CustomerList("src/mainoop/data/Customer.txt");
    private static AdminList adminList= new AdminList("src/mainoop/data/Admin.txt");

    public runing() {
        menu();
    }
    
    public static void menu() {
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
                                String passworc = sc.nextLine();
                                
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


    // public runing() {
    //     Scanner sc = new Scanner(System.in);
    //     boolean kt = true;  //điều kiện để vòng while được thực hiện
    //     boolean kt1 = false;

    //     while(kt) {
    //         System.out.println("=======================MENU=======================");
    //         System.out.println("0: thoát");
    //         // System.out.println("1: Thêm danh sách sản phẩm từ file");
    //         System.out.println("2: Thêm 1 sản phẩm");
    //         System.out.println("3: xem danh sách sản phẩm");
    //         System.out.println("==============================================");
            
    //         System.out.printf("chọn thao tác: ");
    //         int tt = sc.nextInt();
    //         sc.nextLine();
    //         System.out.println("==============================================");
            
    //         switch(tt) {
    //             case 0: {   //thao thác 0.thoát menu
    //                 kt = false;
    //                 break;
    //             }
                
    //             // case 1: {
    //             //     productList.addFileProductList("src/main/input/product.txt");
    //             //     System.out.println("Đã thêm danh sách sản phẩm!");
    //             //     kt1 = true;
    //             //     break;
    //             // }

    //             case 2: {
                    
    //                 break;
    //             }

    //             case 3: {
    //                 if(!kt1) {
    //                     System.out.println("chưa thêm sản phẩm");
    //                     break;
    //                 }
    //                 productList.printProducts();
    //                 break;
    //             }
    //             case 4: {
                    
    //                 break;
    //             }
    //         }
    //     }
    //     sc.close();
    // }

    // public static void main(String[] args) {
    //     manager menu = new manager();
    // }
}
