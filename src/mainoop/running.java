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



public class running {
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

            System.out.print("Nhập thao tác: ");
            int temp = sc.nextInt();
            sc.nextLine();

            while((temp < 1) || (temp > 2)) {
                System.out.print("Thao tác không đúng.\nNhập thao tác: ");
                temp = sc.nextInt();
                sc.nextLine();
            }

            switch(temp) {
                case 1: {
                    Customer customerUer = null;
                    boolean check1 = true;
                    boolean loginCheck = false;
                    while(check1) {
                        System.out.println("==================MENU KHÁCH HÀNG=======================");
                        if(!loginCheck) {

                            System.out.println("0. Quay lại");
                            System.out.println("1. Đăng nhập");
                            System.out.println("2. Đăng ký");

                        } else {
                            System.out.println("3. Xem chi tiết tài khoản");
                            System.out.println("4. Xem Danh sách sản phẩm");
                            System.out.println("5. Tìm sản phẩm");
                            System.out.println("6. Thêm sản phẩm vào giỏ hàng");
                            System.out.println("7. Xóa sản phẩm khỏi giỏ hàng");
                            System.out.println("8. Đăng xuất");
                        }
                        System.out.println("==============================================");
                        
                        System.out.print("Nhập thao tác: ");
                        int thaoTac1 = sc.nextInt();
                        sc.nextLine();

                        if(loginCheck) {
                            while((thaoTac1 < 3) || (thaoTac1 > 8)) {
                                System.out.print("Thao tác không đúng.\nNhập thao tác: ");
                                thaoTac1 = sc.nextInt();
                                sc.nextLine();
                            }
                        } else {
                            while((thaoTac1 < 0) || (thaoTac1 > 2)) {
                                System.out.print("Thao tác không đúng.\nNhập thao tác: ");
                                thaoTac1 = sc.nextInt();
                                sc.nextLine();
                            }
                        }
                        

                        switch(thaoTac1) {
                            // 0. Quay Lại
                            case 0: {
                                System.out.println("Đã quay lại");
                                check1 = false;
                                break;
                            }

                            // 1. Đăng nhập
                            case 1: {
                                // nhập tên đăng nhập
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();

                                // Nhập mật khẩu
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();

                                // kiểm tra đúng tài khoản không
                                customerUer = customerList.login(name, password);
                                
                                if(customerUer == null) {
                                    System.out.println("Sai thông tin đăng nhập");
                                    
                                } else {
                                    System.out.println("Đã đăng nhập");
                                    loginCheck = true;
                                }
                                break;
                            }

                            // 2. Đăng ký
                            case 2: {
                                // nhập tên đăng nhập
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();

                                // nhập mật khẩu
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();

                                // nhập địa chỉ
                                System.out.print("Nhập Địa chỉ: ");
                                String address = sc.nextLine();
                                
                                // tạo đối tượng khách hàng mới
                                customerUer = new Customer(customerList.getCustomerCount()+1, password, name, address);

                                // thêm khách hàng đó vào danh sách khách hàng
                                customerList.addObject(customerUer);
                                loginCheck = true;

                                break;
                            }

                            // 3. Xem chi tiết tài khoản
                            case 3: {
                                System.out.println(customerUer);
                                break;
                            }

                            case 8: {
                                System.out.println("Đã đăng Xuất");
                                customerUer = null;
                                loginCheck = false;
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
