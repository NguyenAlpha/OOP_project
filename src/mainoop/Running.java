package mainoop;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import mainoop.Payment.payment;
import mainoop.Payment.paymentlist;
import mainoop.huytoan.Ordermanager;
import mainoop.huytoan.PayedBill;
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

            int temp = 0;
            boolean checkInPut = false;
            while(!checkInPut) {
                try {
                    temp = sc.nextInt();
                    sc.nextLine();

                    if (temp >= 1 && temp <= 3) {
                        checkInPut = true;
                    } else {
                        System.out.print("Thao tác không đúng. Nhập lại: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Thao tác không đúng. Nhập lại: ");
                    sc.nextLine();
                }
            }

            switch(temp) {
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
                            System.out.println("10. Đăng xuất");
                            System.out.println("15. Thanh toán(code Nhat Nguyen)");
                            System.out.println("16. Xem tình trạng các đơn hàng(code Nhat Nguyen)");
                            System.out.println("17. Xem các đơn đã được giao(code Nhat Nguyen)");
                        }
                        System.out.println("==============================================");
                        
                        System.out.print("Nhập thao tác: ");
                        int thaoTac1 = sc.nextInt();
                        sc.nextLine();

                        if(loginCheck) {
                            while((thaoTac1 < 3) || (thaoTac1 > 17)) {
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
                                System.out.print("Nhập tên tài khoản: ");
                                String name = sc.nextLine();

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
                                System.out.println("================Thanh Toán=============== ");
                                if (currentCustomer.getCartItem().isEmpty())
                                 {
                                    System.out.println("Giỏ hàng trống. Không thể thanh toán.");
                                }
                                else{
                                    payment payment = new payment();
                                    paymentlist paymentlist = new paymentlist();
                                    String filePath ="src/mainoop/data/Bill.txt"; // đường dẫn đến file 
                                    payment.setCustomer(currentCustomer); // Gán khách hàng hiện tại cho payment
                                    payment.setpaymentID(temp);
                                    payment.bill();
                                    Scanner scanner = new Scanner(System.in);
                                    boolean isOrderConfirmed = false; 
                                    String outputFilePath = FilePaths.BILL_PATH; // Gọi đường dẫn đến file bill.txt
                                System.out.println("1.Xác nhận thanh toán bằng tiền mặt");
                                System.out.println("2.Chuyển khoản");
                                System.out.println("3.Thoát chương trình");
                                System.out.print("Chọn phương thức thanh toán: ");
                                int check2 = scanner.nextInt(); // Sự lựa chọn của khách hàng 
                                switch (check2) {
                                    case 1 -> { //Thanh toán tiền mặt
                                        

                                        System.out.println("Bạn đã thanh toán bằng tiền mặt !");
                                        paymentlist.writeToFile(payment, filePath, isOrderConfirmed);
                                         currentCustomer.setCartItemsEmpty();
                                        customerList.set(currentCustomer.getUserId() - 1, currentCustomer);
                                    }
                                    
                                    case 2 -> { // Chuyển khoản
                                        
                                        paymentlist.writeToFile(payment, filePath, isOrderConfirmed);
                                        currentCustomer.setCartItemsEmpty();
                                        customerList.set(currentCustomer.getUserId() - 1, currentCustomer);
                                        System.out.println("Bạn đã thanh toán bằng chuyển khoản !");
                                    }
                                        
                                    default -> System.out.println("Thoát chương trình!");
                                }
                                
                            }
                            }

                            case 10 ->  {   // 10. Đăng xuất
                                currentCustomer = null;
                                loginCheck = false;
                                System.out.println("Đã đăng Xuất");
                            }

                            case 15 -> {
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

                            case 16 -> {
                                paymentListArray.viewCustomerOrderStatus(currentCustomer);;
                            }

                            case 17 -> {
                                paymentListArray.viewCustomerDeliveredOrders(currentCustomer);
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
                        System.out.println("15. Xem tình trạng đơn hàng.(code Nhật Nguyên)");
                        System.out.println("16. Xem lịch sử đơn hàng.(code Nhật Nguyên)");
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

                            case 6 ->{
                                Ordermanager orderManager = new Ordermanager(); // Đảm bảo tên lớp chính xác
                                System.out.println(" === TÌNH TRẠNG ĐƠN HÀNG ===");
                                System.out.println("-----------------------------");
                                 // Đường dẫn file đầu vào và đầu ra
                                String inputFilePath = FilePaths.BILL_PATH; // Đường dẫn đến file ShoppingCart.txt
                                String outputFilePath = FilePaths.BILL_PATH;        // Đường dẫn đến file Bill.txt

                                 // Gọi phương thức quản lý đơn hàng từ file ShoppingCart.txt
                                 orderManager.manageOrdersFromFile(inputFilePath);
                                
                            }
                            
                            case 7 ->{
                                System.out.println("=== XEM LỊCH SỬ ĐƠN HÀNG ===");
    
                                String payedBillFilePath = FilePaths.PAYEDBILL_PATH; // Đường dẫn đến file payedbill.txt

                                // Gọi hàm xử lý hóa đơn
                                try {
                                    PayedBill.viewOrderHistory(payedBillFilePath);; // Gọi phương thức từ lớp PayedBill
                                } catch (IOException e) {
                                    System.out.println("Lỗi: Không thể đọc file. Chi tiết: " + e.getMessage());
                                }
                            }
                            case 8 ->  {
                                check2 = false;
                                System.out.println("Đã thoát!");
                            }

                            case 15 -> {
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

                            case 16 -> {
                                paymentListArray.viewDeliveredOrders();
                            }
                        }
                    }
                }
                case 3 ->  {
                    check = false;
                    System.out.println("Đã thoát!");
                }
            }
        }

        sc.close();
    }
}
