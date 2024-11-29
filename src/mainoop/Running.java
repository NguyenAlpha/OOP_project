package mainoop;

import java.util.Scanner;
import mainoop.Payment.paymentlist;
import mainoop.product.Product;
import mainoop.product.ProductList;
import mainoop.user.Admin;
import mainoop.user.AdminList;
import mainoop.user.Customer;
import mainoop.user.CustomerList;

public class Running { 
    private static ProductList productList = new ProductList(FilePaths.PRODUCT_PATH);
    private static AdminList adminList = new AdminList(FilePaths.ADMIN_PATH);
    private static CustomerList customerList = new CustomerList(FilePaths.CUSTOMER_PATH);
    private static paymentlist paymentListArray = new paymentlist(FilePaths.BILL_PATH);

    public static ProductList getProductList() {
        return productList;
    }
    public static AdminList getAdminList() {
        return adminList;
    }
    public static CustomerList getCustomerList() {
        return customerList;
    }
    public static paymentlist getPaymentListArray() {
        return paymentListArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;

        while(check) {
            System.out.println("=======================MENU=======================");
            System.out.println("1. Khách hàng");
            System.out.println("2. Quản lý");
            System.out.println("3. Thoát");
            System.out.println("==============================================");
            System.out.print("Nhập thao tác: ");

            int thaoTac0 = sc.nextInt();
            sc.nextLine();

            switch(thaoTac0) {
                case 1 ->  {    // 1. Khách hàng
                    Customer currentCustomer = new Customer();
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
                            System.out.println("6. Xem giỏ Hàng");
                            System.out.println("7. Thêm sản phẩm vào giỏ hàng");
                            System.out.println("8. Xóa sản phẩm khỏi giỏ hàng");
                            System.out.println("9. Thanh toán");
                            System.out.println("10. Xem tình trạng các đơn hàng");
                            System.out.println("11. Xem các đơn đã được giao");
                            System.out.println("12. Đăng xuất");
                        }
                        System.out.println("==============================================");
                        
                        System.out.print("Nhập thao tác: ");
                        int thaoTac1 = sc.nextInt();
                        sc.nextLine();

                        if(loginCheck) {
                            while((thaoTac1 < 3) || (thaoTac1 > 12)) {
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
                            case 0 ->  {   // 0. Quay Lại
                                System.out.println("Đã quay lại");
                                check1 = false;
                            }
                            
                            case 1 ->  {   // 1. Đăng nhập
                                // nhập tên đăng nhập
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();

                                // Nhập mật khẩu
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();

                                // kiểm tra đúng tài khoản không
                                currentCustomer = customerList.login(name, password);
                                
                                if(currentCustomer == null) {
                                    System.out.println("Sai thông tin đăng nhập");
                                } else {
                                    loginCheck = true;
                                    System.out.println("Đã đăng nhập");
                                }
                            }
                      
                            case 2 ->  {   // 2. Đăng ký
                                // nhập tên đăng nhập
                                String name = "";
                                boolean exist = true;
                                while(exist) {
                                    while (true) {
                                        System.out.print("Nhập tên tài khoản (chỉ bao gồm số): ");
                                        name = sc.nextLine();
                                        // Kiểm tra nếu chuỗi chỉ bao gồm số
                                        if (name.matches("\\d+")) { // Sử dụng regex để kiểm tra chỉ chứa số
                                            break;
                                        } else {
                                            System.out.println("Tên tài khoản không hợp lệ! Vui lòng nhập lại.");
                                        }
                                    }
                                    if(customerList.checkAlreadyExists(name)) {
                                        System.out.println("tên tài khoản đã tồn tại.");
                                    } else {
                                        exist = false;
                                    }

                                }

                                // nhập mật khẩu
                                System.out.print("Nhập mật khẩu: ");
                                String password = sc.nextLine();

                                // nhập địa chỉ
                                System.out.print("Nhập Địa chỉ: ");
                                String address = sc.nextLine();
                                
                                // tạo đối tượng khách hàng mới
                                currentCustomer = new Customer(customerList.getCustomerCount()+1,name , password, address, "N/A", "N/A");

                                // thêm khách hàng đó vào danh sách khách hàng
                                customerList.addCustomerToList(currentCustomer);
                                loginCheck = true;
                                System.out.println("Đăng ký thành công!");
                            }
                            
                            case 3 ->  {   // 3. Xem chi tiết tài khoản
                                currentCustomer.viewAccount();
                                customerList.writeToFile();
                            }

                            case 4 ->  {   // 4. Xem Danh sách sản phẩm
                                productList.viewProductsList();
                            }
                            
                            case 5 ->  {   // 5. Tìm sản phẩm
                                productList.searchProduct();
                            }
                            
                            case 6 ->  {   // 6. Xem giỏ hàng
                                currentCustomer.viewCartItems();
                            }

                            case 7 ->  {   // 7. Thêm sản phẩm vào giỏ hàng
                                productList.viewProductsList();
                                System.out.print("Nhập mã sản phẩm muốn mua: ");
                                int id = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Nhập số lượng sản phẩm muốn mua: ");
                                int quantity = sc.nextInt();
                                sc.nextLine();

                                currentCustomer.addCartItems(productList.getProductById(id), quantity);
                                customerList.writeToFile();

                                System.out.println("Thêm sản phẩm thành công!");
                            }

                            case 8 ->  {   // 8. Xóa sản phẩm khỏi giỏ hàng
                                currentCustomer.viewCartItems();
                                System.out.print("Nhập mã sản phẩm muốn xóa: ");
                                int id = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Nhập số lượng sản phẩm muốn xóa: ");
                                int quantity = sc.nextInt();
                                sc.nextLine();

                                currentCustomer.removeCartItems(productList.getProductById(id), quantity);
                                customerList.writeToFile();
                            }

                            case 9 -> {     // 9. Thanh toán
                                if (currentCustomer.getCartItem().isEmpty()) {
                                    System.out.println("Giỏ hàng trống. Không thể thanh toán.");
                                    break;
                                }
                                currentCustomer.viewCartItems();
                                System.out.println("================Thanh Toán=============== ");
                                System.out.println("1. Xác nhận thanh toán bằng tiền mặt");
                                System.out.println("2. Xác nhận thanh toán qua tài khoản ngân hàng");
                                System.out.println("3. Thoát chương trình");
                                System.out.print("Chọn phương thức thanh toán: ");
                                int check2 = sc.nextInt(); // Sự lựa chọn của khách hàng 
                                sc.nextLine();
                                switch (check2) {
                                    case 1 -> { //1. Xác nhận thanh toán bằng tiền mặt
                                        paymentListArray.addBill(currentCustomer);
                                        currentCustomer.setCartItemsEmpty();
                                        customerList.writeToFile();
                                        System.out.println("Bạn đã thanh toán bằng tiền mặt !");

                                    }
                                    
                                    case 2 -> { //2. Xác nhận thanh toán qua tài khoản ngân hàng
                                        if(currentCustomer.getBankName().equals("N/A")) {   //chưa thêm tài khoản ngân hàng
                                            System.out.println("bạn chưa liên kết tài khoản ngân hàng");
                                        } else {    //đã thêm
                                            paymentListArray.addBill(currentCustomer);
                                            currentCustomer.setCartItemsEmpty();
                                            customerList.writeToFile();
                                            System.out.println("Bạn đã thanh toán bằng tài khoản ngân hàng!");
                                        }
                                    }
                                        
                                    default -> System.out.println("Thoát chương trình!");
                                }
                            }

                            case 10 -> {    //10. Xem tình trạng các đơn hàng
                                paymentListArray.viewCustomerOrderStatus(currentCustomer);
                            }
                        
                            case 11 -> {    //11. Xem các đơn đã được giao
                                paymentListArray.viewCustomerDeliveredOrders(currentCustomer);
                            }

                            case 12 ->  {   // 12. Đăng xuất
                                currentCustomer = null;
                                loginCheck = false;
                                System.out.println("Đã đăng Xuất");
                            }
                        }
                    }
                }

                case 2 ->  {    // 2. Quản lý
                    Admin admin;
                    boolean check2 = false;
                    System.out.print("Nhập tên tài khoản quản lý: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập mật khẩu tài khoản quản lý: ");
                    String password = sc.nextLine();
                    
                    admin = adminList.login(name, password);
                    if(admin == null) {
                        System.out.println("sai thông tin đăng nhập");
                    } else {
                        System.out.println("đăng nhập thành công!");
                        check2 = true;
                    }

                    while(check2) {
                        System.out.println("=======================MENU=======================");
                        System.out.println("1. Xem danh sách sản phẩm.");
                        System.out.println("2. Tìm sản phẩm.");
                        System.out.println("3. Thêm sản phẩm.");
                        System.out.println("4. Xóa sản phẩm.");
                        System.out.println("5. Sửa sản phẩm.");
                        System.out.println("6. Xem tình trạng đơn hàng.");
                        System.out.println("7. Xem lịch sử đơn hàng.");
                        System.out.println("8. Thoát.");
                        System.out.println("==============================================");
                        
                        System.out.print("Nhập thao tác: ");
                        int thaoTac2 = sc.nextInt();
                        sc.nextLine();

                        switch(thaoTac2) {
                            case 1 ->  {    //1. Xem danh sách sản phẩm
                                productList.viewProductsList();
                            }

                            case 2 ->  {    //2. Tìm sản phẩm
                                productList.searchProduct();
                            }

                            case 3 ->  {    //3. Thêm sản phẩm
                                System.out.print("Nhập tên sản phẩm: ");
                                String nameProduct = sc.nextLine();
                                nameProduct = nameProduct.toLowerCase();
                                System.out.println("Nhập giá sản phẩm: ");
                                long priceProduct = sc.nextLong();
                                sc.nextLine();
                                System.out.println("Nhập số lượng sản phẩm: ");
                                int quantityProduct = sc.nextInt();
                                sc.nextLine();
                                productList.addProduct(nameProduct, priceProduct, quantityProduct);
                            }

                            case 4 ->  {    //4. Xóa sản phẩm
                                productList.viewProductsList();  // Hiển thị danh sách sản phẩm
                                System.out.print("Nhập mã sản phẩm cần xóa: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                Product product = productList.getProductById(id);  // Lấy sản phẩm theo mã
                                if (product != null) {
                                    // Xóa sản phẩm khỏi danh sách sản phẩm của hệ thống
                                    productList.RemoveProduct(product);
                                    customerList.removeProductFromAllCustomers(product);  // Gọi phương thức xóa sản phẩm khỏi giỏ hàng của tất cả khách hàng
                            
                                    System.out.println("Sản phẩm đã được xóa khỏi hệ thống và giỏ hàng của tất cả khách hàng.");
                                } else {
                                    System.out.println("Sản phẩm không tồn tại.");
                                }
                            }

                            case 5 ->  {    //5. Sửa sản phẩm
                                productList.viewProductsList();
                                System.out.print("Nhập mã sản phẩm cần sửa: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Vui lòng nhập lại thông tin sản phẩm ");
                                System.out.println("Nhập tên sản phẩm: ");
                                String newName = sc.nextLine();
                                System.out.println("Nhập giá sản phẩm: ");
                                long newPrice = sc.nextLong();
                                sc.nextLine();
                                System.out.println("Nhập số lượng sản phẩm: ");
                                int newQuantity = sc.nextInt();
                                sc.nextLine();
                                productList.updateProduct(id, newName, newPrice, newQuantity);
                                customerList.writeToFile();
                            }

                            case 6 -> { //6. Xem tình trạng đơn hàng.
                                paymentListArray.viewOrderStatus();
                                System.out.println("Nhập mã đơn để xác nhận đang giao hàng");
                                System.out.println("Hoặc nhập -1 để xác nhận tất cả");
                                System.out.println("Hoặc nhập 0 để thoát");
                                System.out.print("Thao tác: ");
                                int check3 = sc.nextInt();
                                sc.nextLine();
                                if(check3 == 0) {
                                    System.out.println("Đã thoát!");
                                } else if(check3 == -1) {
                                    paymentListArray.confirmedShippingAll();
                                } else {
                                    paymentListArray.confirmedShipping(paymentListArray.getOrderById(check3));
                                }
                            }

                            case 7 -> { //7. Xem lịch sử đơn hàng.
                                paymentListArray.viewDeliveredOrders();
                            }

                            case 8 ->  {    //8. Thoát.
                                check2 = false;
                                System.out.println("Đã thoát!");
                            }
                        }
                    }
                }

                case 3 ->  {    //3. Thoát
                    check = false;
                    System.out.println("Đã thoát!");
                }
            }
        }
        sc.close();
    }
}
