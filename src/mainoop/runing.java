package mainoop;

import java.util.Scanner;
import mainoop.product.ProductList;
import mainoop.user.AdminList;
import mainoop.user.Customer;
import mainoop.user.CustomerList;

//mỗi lần thêm sản phẩm, xóa sản phẩm, sửa sản phẩm sẽ có cùng method viết lại product.txt
//vào sẽ tự động có sẵn danh sách sản phẩm, bỏ thao tác 1
//làm class user. 2 class kế thừa user là: Customer, Admin 
//method user + verifyLogin() : bool
//Customer + register()
//



public class runing {
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
                    boolean check1 = true;
                    boolean loginCheck = false;
                    while(check1) {
                        System.out.println("==================MENU KHÁCH HÀNG=======================");
                        if(!loginCheck) {
                            System.out.println("1. Đăng nhập");
                            System.out.println("2. Đăng ký");
                        } else {
                            System.out.println("3. Xem Danh sách sản phẩm");
                            System.out.println("4. Tìm sản phẩm");
                            System.out.println("5. Thêm sản phẩm vào giỏ hàng");
                            System.out.println("5. Xóa sản phẩm khỏi giỏ hàng");
                            System.out.println("6. Đăng xuất");
                        }
                        System.out.println("==============================================");
                        
                        int temp1 = sc.nextInt();
                        sc.nextLine();
                        switch(temp1) {
                            case 1: {
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();
                                if(customerList.login(name, password)) {
                                    loginCheck = true;
                                } else {
                                    System.out.println("Sai thông tin đăng nhập");
                                }
                                break;
                            }

                            case 2: {
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();
                                System.out.print("Nhập Địa chỉ: ");
                                String address = sc.nextLine();
                                Customer cs = new Customer(customerList.getCustomerCount()+1, password, name, address);
                                
                            }
                            case 6: {
                                System.out.println("Đã đăng Xuất");
                                check1 = false;
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
