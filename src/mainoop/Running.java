package mainoop;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import mainoop.Payment.payment;
import mainoop.product.Product;
import mainoop.product.ProductList;
import mainoop.user.Admin;
import mainoop.user.AdminList;
import mainoop.user.Customer;
import mainoop.user.CustomerList;
import mainoop.user.Ordermanager;
import mainoop.user.PayedBill;

public class Running { 
    private static ProductList productList = new ProductList(FilePaths.PRODUCT_PATH);
    private static AdminList adminList = new AdminList(FilePaths.ADMIN_PATH);
    private static CustomerList customerList = new CustomerList(FilePaths.CUSTOMER_PATH);

    public static ProductList getProductList() {
        return productList;
    }
    public static AdminList getAdminList() {
        return adminList;
    }
    public static CustomerList getCustomerList() {
        return customerList;
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
                        }
                        System.out.println("==============================================");
                        
                        System.out.print("Nhập thao tác: ");
                        int thaoTac1 = sc.nextInt();
                        sc.nextLine();

                        if(loginCheck) {
                            while((thaoTac1 < 3) || (thaoTac1 > 10)) {
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
                                System.out.println("Mã số: " + currentCustomer.getUserId());
                                System.out.println("Tên: " + currentCustomer.getCustomerName());
                                System.out.println("Mật khẩu: " + currentCustomer.getUserPassword());
                                System.out.println("Địa chỉ: " + currentCustomer.getCustomerAddress());
                                System.out.println("Tài khoản ngân hàng: " + currentCustomer.getBankName() + " " + currentCustomer.getBankId());
                                if(currentCustomer.getBankId().equals("N/A")) {
                                    System.out.println("0. quay lại");
                                    System.out.println("1. Liên kết tài khoản ngân hàng");
                                    System.out.print("Nhập thao tác: ");
                                    int thaoTacCase3 = sc.nextInt();
                                    sc.nextLine();

                                    switch (thaoTacCase3) {
                                        case 1 -> {
                                            System.out.print("Nhập tên ngân hàng: ");
                                            String bankName = sc.nextLine();
                                            String bankId;
                                            while (true) {
                                                System.out.print("Nhập số tài khoản ngân hàng: ");
                                                bankId = sc.nextLine();
                                                if (bankId.length() < 8) {
                                                    System.out.println("Số tài khoản ngân hàng phải hơn 8 ký tự.");
                                                } else if (!bankId.matches("\\d+")) {
                                                    System.out.println("Số tài khoản ngân hàng chỉ được chứa các ký tự số.");
                                                } else {
                                                    break; // Thoát vòng lặp nếu chuỗi hợp lệ
                                                }
                                            }
                                            currentCustomer.setBank(bankId, bankName);
                                            customerList.set(currentCustomer.getUserId() - 1, currentCustomer);
                                        }
                                        default -> {}
                                    }
                                }
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
                                System.out.println("Thêm sản phẩm thành công!");
                                customerList.set(currentCustomer.getUserId() - 1,currentCustomer);
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
                                customerList.set(currentCustomer.getUserId() - 1,currentCustomer);
                            }

                            case 9 -> {     // 9. Thanh toán
                                payment payment = new payment();
                                payment.bill();
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Thanh Toán=============== ");
                                System.out.println("1.Xác nhận thanh toán bằng tiền mặt");
                                System.out.println("2.Chuyển khoản");
                                System.out.println("3.Thoát chương trình");
                               System.out.println("Chọn phương thanh toán: ");
                               int check2 = scanner.nextInt(); // Sự lựa chọn của khách hàng 
                              switch (check2) {
                                  case 1    : System.out.println("Bạn đã thanh toán bằng tiền mặt !");
                                      
                                      break;
                                  case 2  : System.out.println("Bạn đã thanh toán bằng chuyển khoản !");
                                  break;
                                  default: System.out.println("Thoát chương trình!");
                                      return;
                              }
                              sc.close();
                            }
                            // xem trạng thái đơn hàng
                            // if(đơn đang vận chuyển) nhập sô 1 để xác nhận đã nhận hàng
                            case 10 ->  {   // 10. Đăng xuất
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
                        System.out.println("3. Thên sản phẩm.");
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
                            case 1 ->  {
                                productList.viewProductsList();
                            }
                            case 2 ->  {   
                                productList.searchProduct();
                            }
                            case 3 ->  {
                                System.out.print("Nhập tên sản phẩm: ");
                                String nameProduct = sc.nextLine();
                                System.out.println("Nhập giá sản phẩm: ");
                                long priceProduct = sc.nextLong();
                                sc.nextLine();
                                System.out.println("Nhập số lượng sản phẩm: ");
                                int quantityProduct = sc.nextInt();
                                sc.nextLine();
                                productList.addProduct(nameProduct, priceProduct, quantityProduct);
                            }
                            case 4 ->  {
                                productList.viewProductsList();
                                System.out.print("Nhập mã sản phẩm cần xóa: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                Product product = productList.getProductById(id);
                                productList.RemoveProduct(product);
                            }
                            case 5 ->  {
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
                                Product product = productList.getProductById(id);
                                productList.updateProductById(id, newName, newPrice, newQuantity);
                            }
                            case 6 ->{
                                Ordermanager orderManager = new Ordermanager(); // Đảm bảo tên lớp chính xác

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
                        }
                    }
                }
                case 3 ->  {
                    check = false;
                    System.out.println("Đã thoát!");
                }
            }
            // 1. Khách hàng
            // 2. Quản lý
            // 3. Thoát
                    }

        sc.close();
    }
}
